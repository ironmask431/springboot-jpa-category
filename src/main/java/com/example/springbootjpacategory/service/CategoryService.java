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
     * branch
     * name
     * parentCategoryId
     */

    //카테고리 저장
    public Long saveCategory (CategoryDto categoryDto) {

        Category category = categoryDto.toEntity();

        if (categoryDto.getParentCategoryId() == null || categoryDto.getParentCategoryId().equals("")) { //대분류 등록
            //JPA 사용하여 DB에서 branch와 name의 중복값을 검사. (대분류에서만 가능)
            if (categoryRepository.existsByBranchAndName(categoryDto.getBranch(), categoryDto.getName())) {
                throw new RuntimeException("branch와 name이 같을 수 없습니다. ");
            }
            //orElse로 refactor
            Category rootCategory = categoryRepository.findByBranchAndName(categoryDto.getBranch(),"ROOT")
                    .orElseGet( () ->
                            Category.builder()
                                    .name("ROOT")
                                    .branch(categoryDto.getBranch())
                                    .level(0)
                                    .build()
                    );
            if (!categoryRepository.existsByBranchAndName(categoryDto.getBranch(), "ROOT")) {
                categoryRepository.save(rootCategory);
            }
            category.setLevel(1);
            category.setParentCategory(rootCategory);

        } else { //중, 소분류 등록
            //부모카테고리 id
            Long parentCategoryId = categoryDto.getParentCategoryId();

            //부모카테고리 조회
            Category parentCategory = categoryRepository.findByBranchAndId(categoryDto.getBranch(), parentCategoryId)
                    .orElseThrow(() -> new IllegalArgumentException("부모 카테고리 없음 예외"));

            category.setLevel(parentCategory.getLevel() + 1);
            category.setParentCategory(parentCategory);

            //부모카테고리 하위에 신규카테고리 생성
            parentCategory.getSubCategory().add(category);
        }

        //category.setLive(true);
        return categoryRepository.save(category).getId();
    }

    //카테고리 조회
    public Map<String, CategoryDto> getCategoryByBranch (String branch) {
        Category category = categoryRepository.findByBranchAndName(branch, "ROOT")
                .orElseThrow(() -> new IllegalArgumentException("찾는 대분류가 없습니다"));

        CategoryDto categoryDTO = new CategoryDto(category);

        Map <String, CategoryDto> data = new HashMap<>();
        data.put(categoryDTO.getName(), categoryDTO);

        return data;
    }

    //카테고리 삭제
    public void deleteCategory (Long categoryId) {
        Category category = findCategory(categoryId);
        if (category.getSubCategory().size() == 0) { //하위 카테고리 없을 경우
            Category parentCategory = findCategory(category.getParentCategory().getId());
            if (!parentCategory.getName().equals("ROOT")) { // ROOT가 아닌 다른 부모가 있을 경우
                //부모카테고리의 자식카테고리 리스트에서 해당 카테고리 삭제.
                parentCategory.getSubCategory().remove(category);
            }
            //해당 카테고리 삭제
            categoryRepository.deleteById(category.getId());
        } else {    //하위 카테고리 있을 경우
            Category parentCategory = findCategory(category.getParentCategory().getId());

            if (!parentCategory.getName().equals("ROOT")) { //ROOT아닌 부모가 있을 경우
                //부모카테고리의 자식카테고리 리스트에서 해당 카테고리 삭제.
                parentCategory.getSubCategory().remove(category);
            }
            //카테고리명 변경
            category.setName("Deleted category");
        }
    }

    //카테고리 수정
    public Long updateCategory (Long categoryId, CategoryDto categoryDTO) {
        Category category = findCategory(categoryId);
        //카테고리명 수정
        category.setName(categoryDTO.getName());
        return category.getId();
    }

    //id로 카테고리 entity 조회
    public Category findCategory(Long categoryId){
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new IllegalArgumentException("categoryId가 없습니다. categoryId="+categoryId));
        return category;
    }
}
