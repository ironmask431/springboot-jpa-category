package com.example.springbootjpacategory.controller;

import com.example.springbootjpacategory.dto.CategoryDto;
import com.example.springbootjpacategory.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/category")
public class CategoryController {

    private final CategoryService categoryService;

    @GetMapping("/hello")
    public String hello(){
        return "hello";
    }

    //카테고리 저장
    @PostMapping("/save")
    public Long saveCategory (@RequestBody CategoryDto categoryDto) {
        return categoryService.saveCategory(categoryDto);
    }

    //전체카테고리 조회
    @GetMapping ("/get")
    public CategoryDto getCategoryAll () {
        return categoryService.getCategoryAll();
    }

    //id로 카테고리 조회
    @GetMapping ("/get/{id}")
    public CategoryDto getCategoryById (@PathVariable Long id) {
        return categoryService.getCategoryById(id);
    }

    //카테고리 수정
    @PutMapping("/update")
    public Long updateCategory (@RequestBody CategoryDto categoryDto) {
        return categoryService.updateCategory(categoryDto);
    }

    //카테고리 삭제
    @DeleteMapping("/delete")
    public void deleteCategory (@RequestBody CategoryDto categoryDto) {
        categoryService.deleteCategory(categoryDto);
    }
}
