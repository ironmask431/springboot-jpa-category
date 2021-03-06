package com.leesh.springbootjpacategory.controller;

import com.leesh.springbootjpacategory.dto.CategoryDeleteDto;
import com.leesh.springbootjpacategory.dto.CategoryDto;
import com.leesh.springbootjpacategory.dto.CategorySaveDto;
import com.leesh.springbootjpacategory.dto.CategoryUpdateDto;
import com.leesh.springbootjpacategory.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

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
    public Long saveCategory (@Valid @RequestBody CategorySaveDto categorySaveDto) {
        return categoryService.saveCategory(categorySaveDto);
    }

    //전체카테고리 조회
    @GetMapping ("/get")
    public CategoryDto getCategoryAll () {
        return categoryService.getCategoryAll();
    }

    //id로 특정 카테고리 조회
    @GetMapping ("/get/{id}")
    public CategoryDto getCategoryById (@PathVariable Long id) {
        return categoryService.getCategoryById(id);
    }

    //카테고리 수정
    @PutMapping("/update")
    public Long updateCategory (@Valid @RequestBody CategoryUpdateDto categoryUpdateDto) {
        return categoryService.updateCategory(categoryUpdateDto);
    }

    //카테고리 삭제
    @DeleteMapping("/delete")
    public void deleteCategory (@Valid @RequestBody CategoryDeleteDto categoryDeleteDto) {
        categoryService.deleteCategory(categoryDeleteDto);
    }
}
