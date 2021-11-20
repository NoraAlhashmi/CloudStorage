package com.udacity.jwdnd.course1.cloudstorage.controllers;

import com.udacity.jwdnd.course1.cloudstorage.models.Note;
import com.udacity.jwdnd.course1.cloudstorage.models.NoteForm;
import com.udacity.jwdnd.course1.cloudstorage.models.User;
import com.udacity.jwdnd.course1.cloudstorage.services.NoteService;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("/note")
public class NoteController {

    private final NoteService noteService;
    private final UserService userService;

    public NoteController(NoteService noteService, UserService userService) {
        this.noteService = noteService;
        this.userService = userService;
    }

    @GetMapping(value = "/get/{noteId}")
    public Note getNote(@PathVariable Integer noteId) {
        return noteService.getNote(noteId);
    }


    @PostMapping("/post")
    public String addNote(
            Authentication authentication, @ModelAttribute("newNote") NoteForm newNote, Model model) {
        System.out.println("inside note controller post");

        Integer userId = getUserId(authentication);
        Integer noteId = newNote.getNoteId();
        System.out.println("inside note controller post: note id = " + noteId);
        if (noteId == null) {
            noteService.addNote(new Note(null,newNote.getNoteTitle(),newNote.getNoteDescription(),userId));
        } else {
            noteService.updateNote(new Note(noteId,newNote.getNoteTitle(),newNote.getNoteDescription(),userId));
        }
        model.addAttribute("notes", noteService.getNotes(userId));
        model.addAttribute("result", "success");

        return "result";
    }


    @GetMapping(value = "/delete/{noteId}")
    public String deleteNote( @PathVariable Integer noteId,Model model) {
        noteService.deleteNote(noteId);
        model.addAttribute("result", "success");

        return "result";
    }

    private Integer getUserId(Authentication authentication) {
        String userName = authentication.getName();
        User user = userService.getUser(userName);
        System.out.println("The user id"+user.toString());
        return user.getUserId();
    }

}

