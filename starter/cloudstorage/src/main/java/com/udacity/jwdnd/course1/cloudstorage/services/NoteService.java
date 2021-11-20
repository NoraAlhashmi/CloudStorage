package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.mappers.NoteMapper;
import com.udacity.jwdnd.course1.cloudstorage.models.Note;
import org.springframework.stereotype.Service;


@Service
public class NoteService {

    private final NoteMapper noteMapper;

    public NoteService(NoteMapper noteMapper) {
        this.noteMapper = noteMapper;
    }

    public Integer addNote(Note note) {
        return noteMapper.addNote(note);
    }

    public Integer updateNote(Note note) {
        return noteMapper.updateNote(note);
    }

    public Note getNote(Integer noteId) {
        return noteMapper.getNote(noteId);
    }

    public Note[] getNotes(Integer userId) {
        return noteMapper.getNotes(userId);
    }

    public void deleteNote(Integer noteId) {
         noteMapper.deleteNote(noteId);
    }

}