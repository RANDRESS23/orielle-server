package com.orielle.orielle_server.adapters.driven.jpa.mysql.adapter;

import com.orielle.orielle_server.adapters.driven.jpa.mysql.entity.ProductEntity;
import com.orielle.orielle_server.adapters.driven.jpa.mysql.mapper.ICategoryEntityMapper;
import com.orielle.orielle_server.adapters.driven.jpa.mysql.mapper.IProductEntityMapper;
import com.orielle.orielle_server.adapters.driven.jpa.mysql.repository.IProductRepository;
import com.orielle.orielle_server.adapters.driven.jpa.mysql.util.DrivenConstants;
import com.orielle.orielle_server.domain.model.CustomPage;
import com.orielle.orielle_server.domain.model.Product;
import com.orielle.orielle_server.domain.spi.IProductPersistencePort;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public class ProductAdapter implements IProductPersistencePort {
    private final IProductRepository productRepository;
    private final IProductEntityMapper productEntityMapper;
    private final ICategoryEntityMapper categoryEntityMapper;

    @Override
    public Product saveProduct(Product product) {
        ProductEntity productEntity = productRepository.save(productEntityMapper.toEntity(product));
        return IProductEntityMapper.toDomainModel(productEntity, categoryEntityMapper);
    }

    @Override
    public void deleteProduct(String name) {
        productRepository.findByName(name).ifPresent(productRepository::delete);
    }

    @Override
    public Product updateProductQuantity(Long productId, Long extraQuantity, Boolean isAddProductQuantity) {
        ProductEntity productEntity = getProductById(productId).map(productEntityMapper::toEntity).orElseThrow();

        Long currentQuantity = productEntity.getQuantity();
        Long updatedQuantity = Boolean.TRUE.equals(isAddProductQuantity)
                ? currentQuantity + extraQuantity
                : currentQuantity - extraQuantity;

        productEntity.setQuantity(updatedQuantity);

        ProductEntity productEntityUpdated = productRepository.save(productEntity);
        return IProductEntityMapper.toDomainModel(productEntityUpdated, categoryEntityMapper);
    }

    @Override
    public Optional<Product> getProductByName(String name) {
        return productRepository.findByName(name)
            .map(productEntity -> IProductEntityMapper.toDomainModel(productEntity, categoryEntityMapper));
    }

    @Override
    public Optional<Product> getProductById(Long productId) {
        return productRepository.findByProductId(productId)
            .map(productEntity -> IProductEntityMapper.toDomainModel(productEntity, categoryEntityMapper));
    }

    @Override
    public CustomPage<Product> getAllProducts(Integer page, Integer size, Boolean ascending, String sortBy) {
        String sortByFinal;
        Page<ProductEntity> pageProductsEntity;

        if (sortBy.equals(DrivenConstants.FIELD_CATEGORY)) sortByFinal = DrivenConstants.SORT_BY_CATEGORY_NAME;
        else sortByFinal = DrivenConstants.SORT_BY_PRODUCT_NAME;

        if (sortBy.equals(DrivenConstants.FIELD_CATEGORY)) {
            Pageable pageable = PageRequest.of(page, size);

            if (Boolean.TRUE.equals(ascending)) {
                pageProductsEntity = productRepository.findAllOrderByCategoryNameAsc(pageable);
            } else pageProductsEntity = productRepository.findAllOrderByCategoryNameDesc(pageable);
        } else {
            Sort sort = Boolean.TRUE.equals(ascending) ? Sort.by(sortByFinal).ascending() : Sort.by(sortByFinal).descending();
            Pageable pageable = PageRequest.of(page, size, sort);

            pageProductsEntity = productRepository.findAll(pageable);
        }

        Page<Product> pageProducts = productEntityMapper.toPageOfProducts(pageProductsEntity, categoryEntityMapper);

        CustomPage<Product> customPage = new CustomPage<>();
        customPage.setPageNumber(pageProducts.getNumber());
        customPage.setPageSize(pageProducts.getSize());
        customPage.setTotalElements(pageProducts.getTotalElements());
        customPage.setTotalPages(pageProducts.getTotalPages());
        customPage.setContent(pageProducts.getContent());

        return customPage;
    }

    @Override
    public List<Product> getTotalProducts() {
        return productRepository.findAll().stream()
            .map(productEntity -> IProductEntityMapper.toDomainModel(productEntity, categoryEntityMapper))
            .toList();
    }
}
