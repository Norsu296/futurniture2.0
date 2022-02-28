package pl.futurniture.futurniture.service.serviceImpl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.futurniture.futurniture.dto.mapper.ProductMapper;
import pl.futurniture.futurniture.exception.ErrorMessage;
import pl.futurniture.futurniture.exception.notFound.NotFoundException;
import pl.futurniture.futurniture.model.Product;
import pl.futurniture.futurniture.repository.ProductRepository;
import pl.futurniture.futurniture.service.ProductService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    @Override
    public List<Product> findAll() {
        return productRepository.findAll();
    }

    @Override
    public Product findById(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(ErrorMessage.NOT_FOUND));
    }

    @Override
    public Product findByName(String name) {
        return productRepository.findByName(name)
                .orElseThrow(() -> new NotFoundException(ErrorMessage.NOT_FOUND));
    }

    @Override
    public Product edit(Long id, Product product) {
        return productRepository.findById(id)
                .map(productFromDb -> {
                    productFromDb.setName(product.getName());
                    productFromDb.setDescription(product.getDescription());
                    productFromDb.setCategory(product.getCategory());
                    return productRepository.save(productFromDb);
                }).orElseThrow(() -> new NotFoundException(ErrorMessage.NOT_FOUND));
    }

    @Override
    public Product create(Product product) {
        return productRepository.save(product);
    }
}
