package pl.futurniture.futurniture.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import pl.futurniture.futurniture.dto.mapper.CategoryMapper;
import pl.futurniture.futurniture.dto.model.CategoryDTO;
import pl.futurniture.futurniture.service.CategoryService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/categories")
public class CategoryController {

    private final CategoryService categoryService;
    private final CategoryMapper categoryMapper;

    @GetMapping
    public List<CategoryDTO> findAll() {
        return categoryMapper.toCategoryDTOs(categoryService.findAll());
    }

    @GetMapping("/{id}")
    public CategoryDTO findById(@PathVariable Long id) {
        return categoryMapper.toCategoryDTO(categoryService.findById(id));
    }

    @GetMapping("/search")
    public CategoryDTO findByName(@RequestParam String name) {
        return categoryMapper.toCategoryDTO(categoryService.findByName(name));
    }

    @PutMapping("/{id}")
    public CategoryDTO edit(@PathVariable Long id, @RequestBody String name) {
        return categoryMapper.toCategoryDTO(categoryService.edit(id, name));
    }

    @PostMapping
    public CategoryDTO create(@RequestBody CategoryDTO categoryDTO) {
        return categoryMapper.toCategoryDTO(categoryMapper.toCategory(categoryDTO));
    }


}
