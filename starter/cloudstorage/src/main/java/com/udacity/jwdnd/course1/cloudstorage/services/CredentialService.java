package com.udacity.jwdnd.course1.cloudstorage.services;
import com.udacity.jwdnd.course1.cloudstorage.mappers.CredentialMapper;
import com.udacity.jwdnd.course1.cloudstorage.models.Credential;
import org.springframework.stereotype.Service;

@Service
public class CredentialService {


    private final CredentialMapper credentialMapper;
    private final EncryptionService encryptionService;


    public CredentialService(CredentialMapper credentialMapper, EncryptionService encryptionService) {
        this.credentialMapper = credentialMapper;
        this.encryptionService = encryptionService;
    }

    public Integer addCredential(Credential credential) {
        return credentialMapper.addCredential(credential);
    }

    public Integer updateCredential(Credential credential) {
        return credentialMapper.updateCredential(credential);
    }

    public Credential getCredential(Integer credentialId) {
        Credential credential = credentialMapper.getCredential(credentialId);

        String encryptedPassword = credential.getPassword();
        String key = credential.getKey();
        String plainTextPassword = encryptionService.decryptValue(encryptedPassword, key);
        credential.setPassword(plainTextPassword);
        return credential;
    }

    public Credential[] getCredentials(Integer userId) {
        return credentialMapper.getCredentials(userId);
    }

    public void deleteCredential(Integer credentialId) {
        credentialMapper.deleteCredential(credentialId);
    }

}
