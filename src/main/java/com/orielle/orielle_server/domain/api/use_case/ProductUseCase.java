package com.orielle.orielle_server.domain.api.use_case;

import com.orielle.orielle_server.domain.api.IProductServicePort;
import com.orielle.orielle_server.domain.exception.AlreadyExistsFieldException;
import com.orielle.orielle_server.domain.exception.InvalidSortByParamException;
import com.orielle.orielle_server.domain.exception.NegativeNotAllowedException;
import com.orielle.orielle_server.domain.exception.NotFoundException;
import com.orielle.orielle_server.domain.model.Category;
import com.orielle.orielle_server.domain.model.CustomPage;
import com.orielle.orielle_server.domain.model.Product;
import com.orielle.orielle_server.domain.spi.ICategoryPersistencePort;
import com.orielle.orielle_server.domain.spi.IProductPersistencePort;
import com.orielle.orielle_server.domain.util.Constants;
import com.orielle.orielle_server.domain.util.ProductValidation;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

public class ProductUseCase implements IProductServicePort {
    private final IProductPersistencePort productPersistencePort;
    private final ICategoryPersistencePort categoryPersistencePort;
    private final ProductValidation productValidation;

    public ProductUseCase(IProductPersistencePort productPersistencePort, ICategoryPersistencePort categoryPersistencePort, ProductValidation productValidation) {
        this.productPersistencePort = productPersistencePort;
        this.categoryPersistencePort = categoryPersistencePort;
        this.productValidation = productValidation;
    }

    @Override
    public Product saveProduct(Product product) {
        if (productPersistencePort.getProductByName(product.getName()).isPresent()) {
            throw new AlreadyExistsFieldException(Constants.PRODUCT_ALREADY_EXISTS_MESSAGE);
        }

        Category category = categoryPersistencePort.getCategoryById(product.getCategory().getCategoryId()).orElse(null);

        if (category == null) throw new NotFoundException(Constants.CATEGORY_NOT_FOUND);

        productValidation.validateProduct(product);

        product.setCategory(category);
        product.setCreatedAt(LocalDateTime.now());
        product.setUpdatedAt(LocalDateTime.now());

        return productPersistencePort.saveProduct(product);
    }

    @Override
    public void deleteProduct(String name) {
        Product product = productPersistencePort.getProductByName(name)
            .orElseThrow(() -> new NotFoundException(Constants.PRODUCT_NOT_FOUND));
        productPersistencePort.deleteProduct(product.getName());
    }

    @Override
    public Product getProduct(String name) {
        return productPersistencePort.getProductByName(name)
            .orElseThrow(() -> new NotFoundException(Constants.PRODUCT_NOT_FOUND));
    }

    @Override
    public Product getProductById(Long productId) {
        return productPersistencePort.getProductById(productId)
            .orElseThrow(() -> new NotFoundException(Constants.PRODUCT_NOT_FOUND));
    }

    @Override
    public CustomPage<Product> getAllProducts(Integer page, Integer size, Boolean ascending, String sortBy) {
        String[] sortByParams = {Constants.FIELD_NAME, Constants.FIELD_CATEGORY};

        if (Arrays.stream(sortByParams)
                .noneMatch(param -> param.equalsIgnoreCase(sortBy))) {
            throw new InvalidSortByParamException(Constants.INVALID_PARAM_MESSAGE);
        }

        return productPersistencePort.getAllProducts(page, size, ascending, sortBy);
    }

    @Override
    public Product updateProductQuantity(Long productId, Long extraQuantity, Boolean isAddProductQuantity) {
        if (productPersistencePort.getProductById(productId).isEmpty()) {
            throw new NotFoundException(Constants.PRODUCT_NOT_FOUND);
        }

        if (extraQuantity < Constants.ZERO_CONSTANT) {
            throw new NegativeNotAllowedException(Constants.NEGATIVE_NOT_ALLOWED_EXCEPTION_MESSAGE);
        }

        if (getProductById(productId).getQuantity() < extraQuantity && Boolean.TRUE.equals(!isAddProductQuantity)) {
            throw new NegativeNotAllowedException(Constants.NEGATIVE_NOT_ALLOWED_EXCEPTION_MESSAGE);
        }

        return productPersistencePort.updateProductQuantity(productId, extraQuantity, isAddProductQuantity);
    }

    @Override
    public List<Product> getTotalProducts() {
        return productPersistencePort.getTotalProducts();
    }
}
