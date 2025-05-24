package com.noteapp.service;

import com.noteapp.model.Note;
import com.noteapp.model.User;
import com.noteapp.repository.NoteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class NoteService {

    private final NoteRepository noteRepository;

    public List<Note> getAllNotes(User user) {
        return noteRepository.findByUserOrderByCreatedAtDesc(user);
    }

    public Note getNoteById(Long id, User user) {
        return noteRepository.findById(id)
                .filter(note -> note.getUser().equals(user))
                .orElseThrow(() -> new RuntimeException("Заметка не найдена"));
    }

    @Transactional
    public Note createNote(Note note, User user) {
        note.setUser(user);
        return noteRepository.save(note);
    }

    @Transactional
    public Note updateNote(Long id, Note updatedNote, User user) {
        Note existingNote = getNoteById(id, user);
        existingNote.setTitle(updatedNote.getTitle());
        existingNote.setContent(updatedNote.getContent());
        existingNote.setCategory(updatedNote.getCategory());
        return noteRepository.save(existingNote);
    }

    @Transactional
    public void deleteNote(Long id, User user) {
        Note note = getNoteById(id, user);
        noteRepository.delete(note);
    }

    public List<Note> searchNotes(String query, User user) {
        return noteRepository.findByUserAndTitleContainingIgnoreCase(user, query);
    }

    public List<Note> getNotesByCategory(Long categoryId, User user) {
        return noteRepository.findByUserAndCategoryIdOrderByCreatedAtDesc(user, categoryId);
    }
} 