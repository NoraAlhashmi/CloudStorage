package com.udacity.jwdnd.course1.cloudstorage.controllers;
import com.udacity.jwdnd.course1.cloudstorage.models.*;
import com.udacity.jwdnd.course1.cloudstorage.services.CredentialService;
import com.udacity.jwdnd.course1.cloudstorage.services.EncryptionService;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.SecureRandom;
import java.util.Base64;

@Controller
@RequestMapping("/credential")
public class CredentialController {

    private final CredentialService credentialService;
    private final UserService userService;
    private final EncryptionService encryptionService;


    public CredentialController(CredentialService credentialService, UserService userService, EncryptionService encryptionService) {
        this.credentialService = credentialService;
        this.userService = userService;
        this.encryptionService = encryptionService;
    }



    @GetMapping(value = "/get/{credentialId}")
    public Credential getCredential(@PathVariable Integer credentialId) {
        System.out.println("inside get credential="+credentialId);
        return credentialService.getCredential(credentialId);
    }


    @PostMapping("/post")
    public String addCredential(
            Authentication authentication, @ModelAttribute("newCredential") CredentialForm newCredential, Model model) {

        Integer userId = getUserId(authentication);
        Integer credentialId = newCredential.getCredentialId();

        SecureRandom random = new SecureRandom();
        byte[] key = new byte[16];
        random.nextBytes(key);
        String encodedKey = Base64.getEncoder().encodeToString(key);
        String encryptedPassword = encryptionService.encryptValue(newCredential.getPassword(), encodedKey);

        if (credentialId == null) {
            credentialService.addCredential(new Credential(null,newCredential.getUrl(),newCredential.getUsername(),encodedKey,encryptedPassword,userId));
        } else {
            credentialService.updateCredential(new Credential(credentialId,newCredential.getUrl(),newCredential.getUsername(),encodedKey,encryptedPassword,userId));
        }
        model.addAttribute("result", "success");

        return "result";
    }


    @GetMapping(value = "/delete/{credentialId}")
    public String deleteNote( @PathVariable Integer credentialId,Model model) {
        credentialService.deleteCredential(credentialId);
        model.addAttribute("result", "success");
        return "result";
    }

    private Integer getUserId(Authentication authentication) {
        String userName = authentication.getName();
        User user = userService.getUser(userName);
        return user.getUserId();
    }



}
