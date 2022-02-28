package pl.futurniture.futurniture.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import pl.futurniture.futurniture.dto.mapper.ProductMapper;
import pl.futurniture.futurniture.dto.model.ProductDTO;
import pl.futurniture.futurniture.service.ProductService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;
    private final ProductMapper productMapper;

    @GetMapping
    public List<ProductDTO> findAll() {
        return productMapper.productDTOs(productService.findAll());
    }

    @GetMapping("/{id}")
    public ProductDTO findById(@PathVariable Long id) {
        return productMapper.toProductDTO(productService.findById(id));
    }

    @GetMapping("/search")
    public ProductDTO findByName(@RequestParam String name) {
        return productMapper.toProductDTO(productService.findByName(name));
    }

    @PutMapping("/{id}")
    public ProductDTO edit(@PathVariable Long id, @RequestBody ProductDTO productDTO) {
        return productMapper.toProductDTO(productService.edit(id, productMapper.toProduct(productDTO)));
    }

    @PostMapping
    public ProductDTO create(@RequestBody ProductDTO productDTO) {
        return productMapper.toProductDTO(productService.create(productMapper.toProduct(productDTO)));
    }

}
