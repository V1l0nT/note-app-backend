package com.noteapp.service;

import com.noteapp.model.Category;
import com.noteapp.model.User;
import com.noteapp.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public List<Category> getAllCategories(User user) {
        return categoryRepository.findByUserOrderByNameAsc(user);
    }

    public Category getCategoryById(Long id, User user) {
        return categoryRepository.findById(id)
                .filter(category -> category.getUser().equals(user))
                .orElseThrow(() -> new RuntimeException("Категория не найдена"));
    }

    @Transactional
    public Category createCategory(Category category, User user) {
        category.setUser(user);
        return categoryRepository.save(category);
    }

    @Transactional
    public Category updateCategory(Long id, Category updatedCategory, User user) {
        Category existingCategory = getCategoryById(id, user);
        existingCategory.setName(updatedCategory.getName());
        existingCategory.setDescription(updatedCategory.getDescription());
        return categoryRepository.save(existingCategory);
    }

    @Transactional
    public void deleteCategory(Long id, User user) {
        Category category = getCategoryById(id, user);
        categoryRepository.delete(category);
    }
} 