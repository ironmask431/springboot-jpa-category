package com.leesh.springbootjpacategory.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
public class CategoryDeleteDto {
    @NotNull(message = "카테고리 id 가 입력되지 않았습니다.")
    private Long id;
}
