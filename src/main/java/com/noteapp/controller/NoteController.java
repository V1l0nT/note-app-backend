package com.noteapp.controller;

import com.noteapp.model.Note;
import com.noteapp.model.User;
import com.noteapp.service.NoteService;
import com.noteapp.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/notes")
@RequiredArgsConstructor
public class NoteController {

    private final NoteService noteService;
    private final UserService userService;

    @GetMapping
    public ResponseEntity<List<Note>> getAllNotes(Authentication authentication) {
        User user = userService.findByUsername(authentication.getName());
        return ResponseEntity.ok(noteService.getAllNotes(user));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Note> getNoteById(@PathVariable Long id, Authentication authentication) {
        User user = userService.findByUsername(authentication.getName());
        return ResponseEntity.ok(noteService.getNoteById(id, user));
    }

    @PostMapping
    public ResponseEntity<Note> createNote(@RequestBody Note note, Authentication authentication) {
        User user = userService.findByUsername(authentication.getName());
        return ResponseEntity.ok(noteService.createNote(note, user));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Note> updateNote(@PathVariable Long id, @RequestBody Note note, Authentication authentication) {
        User user = userService.findByUsername(authentication.getName());
        return ResponseEntity.ok(noteService.updateNote(id, note, user));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteNote(@PathVariable Long id, Authentication authentication) {
        User user = userService.findByUsername(authentication.getName());
        noteService.deleteNote(id, user);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/search")
    public ResponseEntity<List<Note>> searchNotes(@RequestParam String query, Authentication authentication) {
        User user = userService.findByUsername(authentication.getName());
        return ResponseEntity.ok(noteService.searchNotes(query, user));
    }

    @GetMapping("/category/{categoryId}")
    public ResponseEntity<List<Note>> getNotesByCategory(@PathVariable Long categoryId, Authentication authentication) {
        User user = userService.findByUsername(authentication.getName());
        return ResponseEntity.ok(noteService.getNotesByCategory(categoryId, user));
    }
} 