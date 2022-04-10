package com.leesh.springbootjpacategory.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Category {

    //카테고리 ID
    @Id @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;

    //카테고리 명
    @Column (nullable = false)
    private String name;

    //카테고리 레벨
    private Integer level;

    //상위카테고리
    @ManyToOne (fetch = FetchType.LAZY)
    @JoinColumn (name ="parent_category_id")
    private Category parentCategory;

    //하위카테고리 리스트
    @OneToMany (mappedBy = "parentCategory", cascade = CascadeType.ALL)
    private List<Category> subCategory = new ArrayList<>();

    @Builder
    public Category(String name, Integer level,Category parentCategory) {
        this.name = name;
        this.level = level;
        this.parentCategory = parentCategory;
    }

}
