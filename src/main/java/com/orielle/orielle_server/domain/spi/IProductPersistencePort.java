package com.orielle.orielle_server.domain.spi;

import com.orielle.orielle_server.domain.model.CustomPage;
import com.orielle.orielle_server.domain.model.Product;

import java.util.List;
import java.util.Optional;

public interface IProductPersistencePort {
    Product saveProduct(Product product);
    void deleteProduct(String name);
    Product updateProductQuantity(Long productId, Long extraQuantity, Boolean isAddProductQuantity);
    Optional<Product> getProductByName(String name);
    Optional<Product> getProductById(Long productId);
    CustomPage<Product> getAllProducts(Integer page, Integer size, Boolean ascending, String sortBy);
    List<Product> getTotalProducts();
}
