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

    private Long id;
    private Integer level;
    private String name;
    private Long parentCategoryId;
    private String parentCategoryName;
    private Map<Long, CategoryDto> children;

    public CategoryDto(Category entity) {
        this.id = entity.getId();
        this.level = entity.getLevel();
        this.name = entity.getName();

        if(entity.getParentCategory() == null) {
            this.parentCategoryId = null;
            this.parentCategoryName = "ROOT";
        } else {
            this.parentCategoryId = entity.getParentCategory().getId();
            this.parentCategoryName = entity.getParentCategory().getName();
        }

        this.children = entity.getSubCategory() == null ? null :
                entity.getSubCategory().stream().collect(Collectors.toMap(Category::getId, CategoryDto::new));
    }

    public Category toEntity () {
        return Category.builder()
                .level(level)
                .name(name)
                .build();
    }
}
