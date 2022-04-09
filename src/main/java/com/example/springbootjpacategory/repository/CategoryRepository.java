package com.example.springbootjpacategory.repository;

import com.example.springbootjpacategory.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    Optional<Category> findById (Long id);
    Optional<Category> findByName (String name);
    Boolean existsByNameAndParentCategory (String name, Category parentCategory);
    Boolean existsByName(String name);
}
