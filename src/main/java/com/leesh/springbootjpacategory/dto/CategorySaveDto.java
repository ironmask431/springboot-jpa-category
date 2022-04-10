package com.leesh.springbootjpacategory.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
public class CategorySaveDto {
    @NotNull
    private String name;
    private Long parentCategoryId;
}
