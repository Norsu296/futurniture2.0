package pl.futurniture.futurniture.service.serviceImpl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.futurniture.futurniture.exception.ErrorMessage;
import pl.futurniture.futurniture.exception.notFound.NotFoundException;
import pl.futurniture.futurniture.model.Category;
import pl.futurniture.futurniture.repository.CategoryRepository;
import pl.futurniture.futurniture.service.CategoryService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    @Override
    public List<Category> findAll() {
        return categoryRepository.findAll();
    }

    @Override
    public Category findById(Long id) {
        return categoryRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(ErrorMessage.NOT_FOUND));
    }

    @Override
    public Category findByName(String name) {
        return categoryRepository.findByName(name)
                .orElseThrow(() -> new NotFoundException(ErrorMessage.NOT_FOUND));
    }

    @Override
    public Category edit(Long id, String name) {
        return categoryRepository.findById(id)
                .map(categoryFromDb -> {
                    categoryFromDb.setName(name);
                    return categoryRepository.save(categoryFromDb);
                }).orElseThrow(() -> new NotFoundException(ErrorMessage.NOT_FOUND));
    }

    @Override
    public Category create(Category category) {
        return categoryRepository.save(category);
    }
}
