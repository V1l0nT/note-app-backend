package com.noteapp.controller;

import com.noteapp.model.Category;
import com.noteapp.model.User;
import com.noteapp.service.CategoryService;
import com.noteapp.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;
    private final UserService userService;

    @GetMapping
    public ResponseEntity<List<Category>> getAllCategories(Authentication authentication) {
        User user = userService.findByUsername(authentication.getName());
        return ResponseEntity.ok(categoryService.getAllCategories(user));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Category> getCategoryById(@PathVariable Long id, Authentication authentication) {
        User user = userService.findByUsername(authentication.getName());
        return ResponseEntity.ok(categoryService.getCategoryById(id, user));
    }

    @PostMapping
    public ResponseEntity<Category> createCategory(@RequestBody Category category, Authentication authentication) {
        User user = userService.findByUsername(authentication.getName());
        return ResponseEntity.ok(categoryService.createCategory(category, user));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Category> updateCategory(@PathVariable Long id, @RequestBody Category category, Authentication authentication) {
        User user = userService.findByUsername(authentication.getName());
        return ResponseEntity.ok(categoryService.updateCategory(id, category, user));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategory(@PathVariable Long id, Authentication authentication) {
        User user = userService.findByUsername(authentication.getName());
        categoryService.deleteCategory(id, user);
        return ResponseEntity.ok().build();
    }
} 