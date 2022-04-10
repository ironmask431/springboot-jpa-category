package com.leesh.springbootjpacategory.repository;

import com.leesh.springbootjpacategory.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    Optional<Category> findByName (String name);
    Boolean existsByNameAndParentCategory (String name, Category parentCategory);
    Boolean existsByName(String name);
}
