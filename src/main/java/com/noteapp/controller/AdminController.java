package com.noteapp.controller;

import com.noteapp.model.Note;
import com.noteapp.model.User;
import com.noteapp.service.NoteService;
import com.noteapp.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin")
@PreAuthorize("hasRole('ROLE_ADMIN')")
@RequiredArgsConstructor
public class AdminController {

    private final UserService userService;
    private final NoteService noteService;

    @GetMapping("/users")
    public ResponseEntity<List<User>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        return ResponseEntity.ok(userService.getUserById(id));
    }

    @PutMapping("/users/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody User user) {
        return ResponseEntity.ok(userService.updateUser(id, user));
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/users/{userId}/notes")
    public ResponseEntity<List<Note>> getUserNotes(@PathVariable Long userId) {
        User user = userService.getUserById(userId);
        return ResponseEntity.ok(noteService.getAllNotes(user));
    }

    @GetMapping("/users/{userId}/notes/{noteId}")
    public ResponseEntity<Note> getUserNote(@PathVariable Long userId, @PathVariable Long noteId) {
        User user = userService.getUserById(userId);
        return ResponseEntity.ok(noteService.getNoteById(noteId, user));
    }

    @PutMapping("/users/{userId}/notes/{noteId}")
    public ResponseEntity<Note> updateUserNote(
            @PathVariable Long userId,
            @PathVariable Long noteId,
            @RequestBody Note note) {
        User user = userService.getUserById(userId);
        return ResponseEntity.ok(noteService.updateNote(noteId, note, user));
    }

    @DeleteMapping("/users/{userId}/notes/{noteId}")
    public ResponseEntity<Void> deleteUserNote(@PathVariable Long userId, @PathVariable Long noteId) {
        User user = userService.getUserById(userId);
        noteService.deleteNote(noteId, user);
        return ResponseEntity.ok().build();
    }
} 