package pl.futurniture.futurniture.service;

import pl.futurniture.futurniture.model.Product;

import java.util.List;

public interface ProductService {

    List<Product> findAll();

    Product findById(Long id);

    Product findByName(String name);

    Product edit(Long id, Product product);



}
