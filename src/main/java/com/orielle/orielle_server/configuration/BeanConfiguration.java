package com.orielle.orielle_server.configuration;

import com.orielle.orielle_server.adapters.driven.jpa.mysql.adapter.CategoryAdapter;
import com.orielle.orielle_server.adapters.driven.jpa.mysql.adapter.ProductAdapter;
import com.orielle.orielle_server.adapters.driven.jpa.mysql.mapper.ICategoryEntityMapper;
import com.orielle.orielle_server.adapters.driven.jpa.mysql.mapper.IProductEntityMapper;
import com.orielle.orielle_server.adapters.driven.jpa.mysql.repository.ICategoryRepository;
import com.orielle.orielle_server.adapters.driven.jpa.mysql.repository.IProductRepository;
import com.orielle.orielle_server.domain.api.ICategoryServicePort;
import com.orielle.orielle_server.domain.api.IProductServicePort;
import com.orielle.orielle_server.domain.api.use_case.CategoryUseCase;
import com.orielle.orielle_server.domain.api.use_case.ProductUseCase;
import com.orielle.orielle_server.domain.spi.ICategoryPersistencePort;
import com.orielle.orielle_server.domain.spi.IProductPersistencePort;
import com.orielle.orielle_server.domain.util.CategoryValidation;
import com.orielle.orielle_server.domain.util.ProductValidation;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class BeanConfiguration {
    private final ICategoryRepository categoryRepository;
    private final ICategoryEntityMapper categoryEntityMapper;
    private final IProductRepository productRepository;
    private final IProductEntityMapper productEntityMapper;

    @Bean
    public CategoryValidation categoryValidation() {
        return new CategoryValidation();
    }

    @Bean
    public ProductValidation productValidation() {
        return new ProductValidation();
    }

    @Bean
    public ICategoryPersistencePort categoryPersistencePort() {
        return new CategoryAdapter(categoryRepository, categoryEntityMapper);
    }

    @Bean
    public ICategoryServicePort categoryServicePort() {
        return new CategoryUseCase(categoryPersistencePort(), categoryValidation());
    }

    @Bean
    public IProductPersistencePort productPersistencePort() {
        return new ProductAdapter(productRepository, productEntityMapper, categoryEntityMapper);
    }

    @Bean
    public IProductServicePort productServicePort() {
        return new ProductUseCase(productPersistencePort(), categoryPersistencePort(), productValidation());
    }
}
