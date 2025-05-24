package com.noteapp.repository;

import com.noteapp.model.Note;
import com.noteapp.model.User;
import com.noteapp.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface NoteRepository extends JpaRepository<Note, Long> {
    List<Note> findByUser(User user);
    List<Note> findByUserOrderByCreatedAtDesc(User user);
    List<Note> findByUserAndCategory(User user, Category category);
    List<Note> findByUserAndTitleContainingIgnoreCase(User user, String title);
    List<Note> findByUserAndCategoryIdOrderByCreatedAtDesc(User user, Long categoryId);
} 