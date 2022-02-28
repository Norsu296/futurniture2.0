package pl.futurniture.futurniture.dto.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;
import pl.futurniture.futurniture.dto.model.ProductDTO;
import pl.futurniture.futurniture.model.Product;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class ProductMapper {

    private final CategoryMapper categoryMapper;

    public ProductDTO toProductDTO(Product product) {
        return ProductDTO.builder()
                .id(product.getId())
                .name(product.getName())
                .description(product.getDescription())
                .category(categoryMapper.toCategoryDTO(product.getCategory()))
                .build();
    }

    public Product toProduct(ProductDTO productDTO) {
        return Product.builder()
                .id(productDTO.getId())
                .name(productDTO.getName())
                .description(productDTO.getDescription())
                .category(categoryMapper.toCategory(productDTO.getCategory()))
                .build();
    }

    public List<ProductDTO> productDTOs(List<Product> products) {
        List<ProductDTO> productDTOs = new ArrayList<>();
        for (Product product : products) {
            productDTOs.add(
                    ProductDTO.builder()
                            .id(product.getId())
                            .name(product.getName())
                            .description(product.getDescription())
                            .category(categoryMapper.toCategoryDTO(product.getCategory()))
                            .build());
        }
        return productDTOs;
    }

    public List<Product> toProducts(List<ProductDTO> productDTOs) {
        List<Product> products = new ArrayList<>();
        for (ProductDTO productDTO : productDTOs) {
            products.add(
                    Product.builder()
                            .id(productDTO.getId())
                            .name(productDTO.getName())
                            .description(productDTO.getDescription())
                            .category(categoryMapper.toCategory(productDTO.getCategory()))
                            .build());
        }
        return products;
    }

}
