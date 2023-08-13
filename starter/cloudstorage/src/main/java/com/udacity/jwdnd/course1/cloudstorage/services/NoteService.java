package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.entity.Note;
import com.udacity.jwdnd.course1.cloudstorage.mapper.NoteMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NoteService {

    private final NoteMapper noteMapper;
    private final UserService userService;

    public NoteService(
            NoteMapper noteMapper,
            UserService userService
    ) {
        this.noteMapper = noteMapper;
        this.userService = userService;
    }

    public List<Note> getNoteListByUserId() {
        return noteMapper.getAllByUserId(userService.getUserId());
    }

    public void createNote(Note note) {
        note.setUserId(userService.getUserId());
        noteMapper.insert(note);
    }

    public void updateNote(Note note) {
        note.setUserId(userService.getUserId());
        noteMapper.update(note);
    }

    public boolean deleteNote(Integer noteId) {
        return noteMapper.delete(noteId);
    }

}
