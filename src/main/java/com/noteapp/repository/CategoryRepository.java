package com.noteapp.repository;

import com.noteapp.model.Category;
import com.noteapp.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    List<Category> findByUser(User user);
    List<Category> findByUserOrderByNameAsc(User user);
} 