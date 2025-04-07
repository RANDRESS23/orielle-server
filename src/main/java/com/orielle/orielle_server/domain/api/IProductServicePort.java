package com.orielle.orielle_server.domain.api;

import com.orielle.orielle_server.domain.model.CustomPage;
import com.orielle.orielle_server.domain.model.Product;

import java.util.List;

public interface IProductServicePort {
    Product saveProduct(Product product);
    void deleteProduct(String name);
    Product getProduct(String name);
    Product getProductById(Long productId);
    CustomPage<Product> getAllProducts(Integer page, Integer size, Boolean ascending, String sortBy);
    Product updateProductQuantity(Long productId, Long extraQuantity, Boolean isAddProductQuantity);
    List<Product> getTotalProducts();
}
