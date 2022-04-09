package com.example.springbootjpacategory.dto;

import com.example.springbootjpacategory.entity.Category;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.util.Map;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
public class CategoryDto {

    private Long categoryId;
    private String branch;
    private String name;
    private Integer level;
    private Long parentCategoryId;
    private String parentCategoryName;
    private Map<Long, CategoryDto> children;

    public CategoryDto(Category entity) {
        this.categoryId = entity.getId();
        this.branch = entity.getBranch();
        this.level = entity.getLevel();
        this.name = entity.getName();

        if(entity.getParentCategory() == null) {
            this.parentCategoryId = null;
            this.parentCategoryName = "대분류";
        } else {
            this.parentCategoryId = entity.getParentCategory().getId();
            this.parentCategoryName = entity.getParentCategory().getName();
        }

        this.children = entity.getSubCategory() == null ? null :
                entity.getSubCategory().stream().collect(Collectors.toMap(Category::getId, CategoryDto::new));
    }

    public Category toEntity () {
        return Category.builder()
                .branch(branch)
                .level(level)
                .name(name)
                .build();
    }
}
