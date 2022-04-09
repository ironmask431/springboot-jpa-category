package com.example.springbootjpacategory.service;

import com.example.springbootjpacategory.dto.CategoryDto;
import com.example.springbootjpacategory.entity.Category;
import com.example.springbootjpacategory.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
@Transactional
public class CategoryService {
    private final CategoryRepository categoryRepository;

    /**
     * 입력
     * name
     * parentCategoryId
     */
    //카테고리 저장
    public Long saveCategory (CategoryDto categoryDto) {

        if(categoryDto.getName().toUpperCase().equals("ROOT")){
            throw new RuntimeException("카테고리명을 'ROOT' 로 설정할 수 없습니다.");
        }

        Category category;

        if (categoryDto.getParentCategoryId() == null || categoryDto.getParentCategoryId().equals("")) { //부모카테고리 입력 없을 시 > root 카테고리조회
            //최상위 root 조회, 없으면 신규생성
            Category rootCategory = categoryRepository.findByName("ROOT")
                    .orElseGet( () ->
                            Category.builder()
                                    .name("ROOT")
                                    .level(0)
                                    .build()
                    );
            //"ROOT" 카테고리 없을 시 root 카테고리 저장
            if (!categoryRepository.existsByName("ROOT")) {
                categoryRepository.save(rootCategory);
            }

            if(categoryRepository.existsByNameAndParentCategory(categoryDto.getName(), rootCategory)){
                throw new RuntimeException("상위카테고리 하위에 동일한 이름의 카테고리가 있습니다.");
            }

            category = categoryDto.toEntity();
            //신규카테고리 레벨1 설정
            category.setLevel(1);
            //신규카테고리 부모카테고리 > root 카테고리로 설정
            category.setParentCategory(rootCategory);

        } else { //중, 소분류 등록
            //부모카테고리 id 찾음.
            Long parentCategoryId = categoryDto.getParentCategoryId();

            //부모카테고리 조회
            Category parentCategory = categoryRepository.findById(parentCategoryId)
                    .orElseThrow(() -> new IllegalArgumentException("부모 카테고리가 없습니다. id="+parentCategoryId));

            if(categoryRepository.existsByNameAndParentCategory(categoryDto.getName(), parentCategory)){
                throw new RuntimeException("상위카테고리 하위에 동일한 이름의 카테고리가 있습니다.");
            }

            //카테고리 entity 생성
            category = categoryDto.toEntity();
            //부모카테고리 레벨+1로 신규카테고리 레벨 설정
            category.setLevel(parentCategory.getLevel() + 1);
            //신규카테고리의 부모카테고리 설정
            category.setParentCategory(parentCategory);
            //부모카테고리 하위에 신규카테고리 생성
            //parentCategory.getSubCategory().add(category);
        }
        //category.setLive(true);
        //카테고리 저장
        return categoryRepository.save(category).getId();
    }

    //전체 카테고리 조회 (ROOT)
    public CategoryDto getCategoryAll () {
        Category category = categoryRepository.findByName("ROOT")
                .orElseThrow(() -> new IllegalArgumentException("찾는 카테고리가 없습니다. name=ROOT"));
        CategoryDto categoryDTO = new CategoryDto(category);
        return categoryDTO;
    }

    //id로 카테고리 조회
    public CategoryDto getCategoryById (Long id) {
        Category category = findCategory(id);
        CategoryDto categoryDTO = new CategoryDto(category);
        return categoryDTO;
    }

    //카테고리 수정
    public Long updateCategory (CategoryDto categoryDto) {
        Category category = findCategory(categoryDto.getId());
        //카테고리명 수정

        if(categoryRepository.existsByNameAndParentCategory(categoryDto.getName(), category.getParentCategory())){
            throw new RuntimeException("상위카테고리 하위에 동일한 이름의 카테고리가 있습니다.");
        }
        category.setName(categoryDto.getName());
        return category.getId();
    }

    //카테고리 삭제
    public void deleteCategory (CategoryDto categoryDto) {
        Category category = findCategory(categoryDto.getId());
        if (category.getSubCategory().size() == 0) { //하위 카테고리 없을 경우
            categoryRepository.deleteById(category.getId());
        } else {    //하위 카테고리 있을 경우
            throw new RuntimeException("하위 카테고리가 있어서 삭제할 수 없습니다.");
        }
    }

    //id로 카테고리 entity 조회
    public Category findCategory(Long id){
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("카테고리 id가 없습니다. id="+id));
        return category;
    }
}
