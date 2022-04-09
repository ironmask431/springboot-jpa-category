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

    //save
    @PostMapping("/save")
    public Long saveCategory (@RequestBody CategoryDto categoryDto) {
        return categoryService.saveCategory(categoryDto);
    }

    //get
    @GetMapping ("/get/{branch}")
    public Map<String, CategoryDto> getCategoryByBranch (@PathVariable String branch) {
        return categoryService.getCategoryByBranch(branch);
    }
    //delete


}
