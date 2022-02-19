package pl.futurniture.futurniture.dto.mapper;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import pl.futurniture.futurniture.dto.model.CategoryDTO;
import pl.futurniture.futurniture.model.Category;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CategoryMapperTest {

    @Autowired
    private CategoryMapper categoryMapper;

    @Test
    void shouldReturnInstanceOfCategory() {
        //given
        CategoryDTO categoryDTO = CategoryDTO.builder()
                .id(1L)
                .name("Fotele")
                .build();
        Category categoryToCheck = Category.builder()
                .id(1L)
                .name("Fotele")
                .build();
        //when
        Category category = categoryMapper.toCategory(categoryDTO);
        //then
        assertNotNull(category);
        assertInstanceOf(Category.class, category);
        assertEquals(categoryToCheck, category);
    }

    @Test
    void shouldReturnInstanceOfCategoryDTO() {
        //given
        Category category = Category.builder()
                .id(1L)
                .name("Fotele")
                .build();
        CategoryDTO categoryDTOToCheck = CategoryDTO.builder()
                .id(1L)
                .name("Fotele")
                .build();
        //when
        CategoryDTO categoryDTO = categoryMapper.toCategoryDTO(category);
        //then
        assertNotNull(categoryDTO);
        assertInstanceOf(CategoryDTO.class, categoryDTO);
        assertEquals(categoryDTOToCheck.getName(), categoryDTO.getName());
    }

    @Test
    void shouldReturnListOfCategories() {
        //given
        List<CategoryDTO> categoryDTOs = new ArrayList<>(
                Arrays.asList(
                        CategoryDTO.builder()
                                .id(1L)
                                .name("Fotele")
                                .build(),
                        CategoryDTO.builder()
                                .id(2L)
                                .name("Stoły")
                                .build()));
        //when
        List<Category> categories = categoryMapper.toCategories(categoryDTOs);
        //then
        assertEquals(2, categories.size());
        assertEquals("Fotele", categories.get(0).getName());
        assertNotNull(categories);
    }

    @Test
    void shouldReturnListOfCategoryDTOs(){
        //given
        List<Category> categories = new ArrayList<>(
                Arrays.asList(
                        Category.builder()
                                .id(1L)
                                .name("Fotele")
                                .build(),
                        Category.builder()
                                .id(2L)
                                .name("Stoły")
                                .build()));
        //when
        List<CategoryDTO> categoryDTOs = categoryMapper.toCategoryDTOs(categories);
        //then
        assertEquals(2, categoryDTOs.size());
        assertEquals("Fotele", categoryDTOs.get(0).getName());
        assertNotNull(categoryDTOs);
    }

}