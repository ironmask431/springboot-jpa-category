package com.leesh.springbootjpacategory.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@NoArgsConstructor
public class CategorySaveDto {
    @NotBlank(message = "카테고리 name 이 입력되지 않았습니다.")
    private String name;
    private Long parentCategoryId;
}
