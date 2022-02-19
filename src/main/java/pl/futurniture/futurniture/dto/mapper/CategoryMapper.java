package pl.futurniture.futurniture.dto.mapper;

import org.springframework.stereotype.Component;
import pl.futurniture.futurniture.dto.model.CategoryDTO;
import pl.futurniture.futurniture.model.Category;

import java.util.ArrayList;
import java.util.List;

@Component
public class CategoryMapper {

    public Category toCategory(CategoryDTO categoryDTO) {
        return Category.builder()
                .id(categoryDTO.getId())
                .name(categoryDTO.getName())
                .build();
    }

    public CategoryDTO toCategoryDTO(Category category) {
        return CategoryDTO.builder()
                .id(category.getId())
                .name(category.getName())
                .build();
    }

    public List<Category> toCategories(List<CategoryDTO> categoryDTOs) {
        List<Category> categories = new ArrayList<>();
        for (CategoryDTO categoryDTO : categoryDTOs) {
            categories.add(
                    Category.builder()
                            .id(categoryDTO.getId())
                            .name(categoryDTO.getName())
                            .build());
        }
        return categories;
    }

    public List<CategoryDTO> toCategoryDTOs(List<Category> categories){
        List<CategoryDTO> categoryDTOs = new ArrayList<>();
        for(Category category : categories){
            categoryDTOs.add(
                    CategoryDTO.builder()
                    .id(category.getId())
                    .name(category.getName())
                    .build());
        }
        return categoryDTOs;
    }

}
