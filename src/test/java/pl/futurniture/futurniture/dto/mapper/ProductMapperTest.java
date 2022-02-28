package pl.futurniture.futurniture.dto.mapper;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import pl.futurniture.futurniture.dto.model.CategoryDTO;
import pl.futurniture.futurniture.dto.model.ProductDTO;
import pl.futurniture.futurniture.model.Category;
import pl.futurniture.futurniture.model.Product;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ProductMapperTest {

    @Autowired
    private ProductMapper productMapper;


    private static final CategoryDTO categoryDTO = CategoryDTO.builder()
            .name("Fotele")
            .build();


    private static final Category category = Category.builder()
            .name("Fotele")
            .build();

    @Test
    void shouldReturnInstanceOfProduct() {
        //given
        ProductDTO productDTO = ProductDTO.builder()
                .id(1L)
                .name("Uszak")
                .description("Opis produktu")
                .category(categoryDTO)
                .build();

        Product productToCheck = Product.builder()
                .id(1L)
                .name("Uszak")
                .description("Opis produktu")
                .category(category)
                .build();
        //when
        Product product = productMapper.toProduct(productDTO);
        //then
        assertNotNull(product);
        assertEquals(product, productToCheck);
        assertInstanceOf(Product.class, product);
    }

    @Test
    void shouldReturnInstanceOfAddressDTO() {
        //given
        Product product = Product.builder()
                .id(1L)
                .name("Uszak")
                .description("Opis produktu")
                .category(category)
                .build();

        ProductDTO productDTOToCheck = ProductDTO.builder()
                .id(1L)
                .name("Uszak")
                .description("Opis produktu")
                .category(categoryDTO)
                .build();
        //when
        ProductDTO productDTO = productMapper.toProductDTO(product);
        //then
        assertNotNull(productDTO);
        assertEquals(productDTO.getName(), productDTOToCheck.getName());
        assertInstanceOf(ProductDTO.class, productDTO);
    }

    @Test
    void shouldReturnListInstanceOfProducts() {
        //given
        Product product = Product.builder()
                .id(1L)
                .name("Uszak")
                .description("Opis produktu")
                .category(category)
                .build();

        List<ProductDTO> productDTOS = new ArrayList<>(
                Arrays.asList(
                        ProductDTO.builder()
                                .id(1L)
                                .name("Uszak")
                                .description("Opis produktu")
                                .category(categoryDTO)
                                .build(),
                        ProductDTO.builder()
                                .id(2L)
                                .name("King")
                                .description("Opis produktu drugiego")
                                .category(categoryDTO)
                                .build()
                )
        );
        //when
        List<Product> products = productMapper.toProducts(productDTOS);
        //then
        assertNotNull(products);
        assertEquals(2, products.size());
        assertEquals(1L, products.get(0).getId());
        assertEquals(product.getName(), products.get(0).getName());
        assertInstanceOf(Product.class, products.get(0));
    }

    @Test
    void shouldReturnListInstanceOfProductDTO() {
        //given
        ProductDTO productDTO = ProductDTO.builder()
                .id(1L)
                .name("Uszak")
                .description("Opis produktu")
                .category(categoryDTO)
                .build();
        List<Product> products = new ArrayList<>(
                Arrays.asList(
                        Product.builder()
                                .id(1L)
                                .name("Uszak")
                                .description("Opis produktu")
                                .category(category)
                                .build(),
                        Product.builder()
                                .id(2L)
                                .name("King")
                                .description("Opis produktu drugiego")
                                .category(category)
                                .build()
                )
        );
        //when
        List<ProductDTO> productDTOS = productMapper.productDTOs(products);
        //then
        assertNotNull(productDTOS);
        assertEquals(2, productDTOS.size());
        assertEquals(1L, productDTOS.get(0).getId());
        assertEquals(productDTO.getName(), productDTOS.get(0).getName());
        assertInstanceOf(ProductDTO.class, productDTOS.get(0));

    }

}