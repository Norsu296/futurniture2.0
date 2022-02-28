package pl.futurniture.futurniture.service;

import pl.futurniture.futurniture.model.Category;

import java.util.List;

public interface CategoryService {

    List<Category> findAll();

    Category findById(Long id);

    Category findByName(String name);

    Category edit(Long id, String name);

    Category create(Category category);

}
