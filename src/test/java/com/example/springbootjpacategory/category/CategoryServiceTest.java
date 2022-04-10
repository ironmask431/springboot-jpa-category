package com.example.springbootjpacategory.category;

import com.leesh.springbootjpacategory.SpringbootJpaCategoryApplication;
import com.leesh.springbootjpacategory.dto.CategoryDeleteDto;
import com.leesh.springbootjpacategory.dto.CategoryDto;
import com.leesh.springbootjpacategory.dto.CategorySaveDto;
import com.leesh.springbootjpacategory.dto.CategoryUpdateDto;
import com.leesh.springbootjpacategory.entity.Category;
import com.leesh.springbootjpacategory.service.CategoryService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest(classes = SpringbootJpaCategoryApplication.class)
@Transactional
public class CategoryServiceTest {
    @Autowired
    CategoryService categoryService;

    @Test
    public void 최상위_카테고리_저장_테스트(){
        //given
        String name = "테스트";

        CategorySaveDto categorySaveDto = new CategorySaveDto();
        categorySaveDto.setName(name);

        //when
        Long id = categoryService.saveCategory(categorySaveDto);
        Category category = categoryService.findCategory(id);

        //then
        assertThat(category.getName()).isEqualTo(name);
        assertThat(category.getLevel()).isEqualTo(1);
        assertThat(category.getParentCategory().getName()).isEqualTo("ROOT");
    }

    @Test
    public void 하위_카테고리_저장_테스트(){
        //given
        String parent_name = "상위카테고리";

        CategorySaveDto categorySaveDto = new CategorySaveDto();
        categorySaveDto.setName(parent_name);

        Long parent_id = categoryService.saveCategory(categorySaveDto);
        Category parent_category = categoryService.findCategory(parent_id);

        //when
        String name = "하위카테고리";
        categorySaveDto.setName(name);
        categorySaveDto.setParentCategoryId(parent_id);

        Long id = categoryService.saveCategory(categorySaveDto);
        Category category = categoryService.findCategory(id);

        //then
        assertThat(category.getName()).isEqualTo(name);
        assertThat(category.getLevel()).isEqualTo(parent_category.getLevel()+1);
        assertThat(category.getParentCategory().getName()).isEqualTo(parent_category.getName());
    }

    @Test
    public void 카테고리_전체조회_테스트(){
        //given
        String name = "테스트";

        CategorySaveDto categorySaveDto = new CategorySaveDto();
        categorySaveDto.setName(name);

        Long id = categoryService.saveCategory(categorySaveDto);

        //when
        CategoryDto categoryDto = categoryService.getCategoryAll();

        //then
        assertThat(categoryDto.getLevel()).isEqualTo(0);
        assertThat(categoryDto.getName()).isEqualTo("ROOT");
    }

    @Test
    public void 카테고리_id로조회_테스트(){
        //given
        String name = "테스트";

        CategorySaveDto categorySaveDto = new CategorySaveDto();
        categorySaveDto.setName(name);

        Long id = categoryService.saveCategory(categorySaveDto);

        //when
        CategoryDto categoryDto = categoryService.getCategoryById(id);

        //then
        assertThat(categoryDto.getName()).isEqualTo(name);
    }

    @Test
    public void 카테고리_수정_테스트(){
        //given
        String name = "테스트";

        CategorySaveDto categorySaveDto = new CategorySaveDto();
        categorySaveDto.setName(name);

        Long id = categoryService.saveCategory(categorySaveDto);

        //when
        String change_name = "테스트_수정";
        CategoryUpdateDto categoryUpdateDto = new CategoryUpdateDto();
        categoryUpdateDto.setId(id);
        categoryUpdateDto.setName(change_name);
        id = categoryService.updateCategory(categoryUpdateDto);

        //then
        Category category = categoryService.findCategory(id);
        assertThat(category.getName()).isEqualTo(change_name);
    }

    @Test
    public void 카테고리_삭제_테스트(){

        //given
        String name = "테스트";

        CategorySaveDto categorySaveDto = new CategorySaveDto();
        categorySaveDto.setName(name);

        Long id = categoryService.saveCategory(categorySaveDto);

        //when
        CategoryDeleteDto categoryDeleteDto = new CategoryDeleteDto();
        categoryDeleteDto.setId(id);
        categoryService.deleteCategory(categoryDeleteDto);

        //then
        IllegalArgumentException e =
                assertThrows(IllegalArgumentException.class,() -> categoryService.findCategory(id));

        assertThat(e.getMessage()).isEqualTo("찾는 카테고리가 없습니다. id="+id);
    }
}
