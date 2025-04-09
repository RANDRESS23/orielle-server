package com.orielle.orielle_server.configuration;

import com.orielle.orielle_server.adapters.driven.jpa.mysql.adapter.*;
import com.orielle.orielle_server.adapters.driven.jpa.mysql.mapper.*;
import com.orielle.orielle_server.adapters.driven.jpa.mysql.repository.*;
import com.orielle.orielle_server.configuration.security_config.jwt_configuration.JwtService;
import com.orielle.orielle_server.domain.api.*;
import com.orielle.orielle_server.domain.api.use_case.*;
import com.orielle.orielle_server.domain.spi.*;
import com.orielle.orielle_server.domain.util.CartProductValidation;
import com.orielle.orielle_server.domain.util.CategoryValidation;
import com.orielle.orielle_server.domain.util.ProductValidation;
import com.orielle.orielle_server.domain.util.UserValidation;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@RequiredArgsConstructor
public class BeanConfiguration {
    private final ICategoryRepository categoryRepository;
    private final ICategoryEntityMapper categoryEntityMapper;
    private final IProductRepository productRepository;
    private final IProductEntityMapper productEntityMapper;
    private final ICartRepository cartRepository;
    private final ICartProductRepository cartProductRepository;
    private final ICartEntityMapper cartEntityMapper;
    private final ICartProductEntityMapper cartProductEntityMapper;
    private final IUserRepository userRepository;
    private final IUserEntityMapper userEntityMapper;
    private final IRoleRepository roleRepository;
    private final IRoleEntityMapper roleEntityMapper;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;
    private final IOrderEntityMapper orderEntityMapper;
    private final IOrderRepository orderRepository;
    private final IOrderItemRepository orderItemRepository;
    private final IOrderItemEntityMapper orderItemEntityMapper;

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

    @Bean
    public CartProductValidation cartProductValidation() {
        return new CartProductValidation();
    }

    @Bean
    public ICartPersistencePort cartPersistencePort() {
        return new CartAdapter(cartRepository, cartEntityMapper);
    }

    @Bean
    public ICartServicePort cartServicePort() {
        return new CartUseCase(cartPersistencePort(), cartProductPersistencePort(), authPersistencePort(), productPersistencePort(), orderPersistencePort());
    }

    @Bean
    public ICartProductPersistencePort cartProductPersistencePort() {
        return new CartProductAdapter(cartProductRepository, cartProductEntityMapper);
    }

    @Bean
    public ICartProductServicePort cartProductServicePort() {
        return new CartProductUseCase(cartProductPersistencePort(), cartPersistencePort(), productPersistencePort(), cartServicePort(), authPersistencePort(), cartProductValidation());
    }

    @Bean
    public IEncryptionPersistencePort encryptionPersistencePort() {
        return new EncryptionAdapter(passwordEncoder);
    }

    @Bean
    public UserValidation userValidation() {
        return new UserValidation();
    }

    @Bean
    public IUserPersistencePort userPersistencePort() {
        return new UserAdapter(userRepository, userEntityMapper, roleEntityMapper, roleServicePort());
    }

    @Bean
    public IUserServicePort userServicePort() {
        return new UserUseCase(userPersistencePort(), roleServicePort(), encryptionPersistencePort(), userValidation());
    }

    @Bean
    public IRolePersistencePort rolePersistencePort() {
        return new RoleAdapter(roleRepository, roleEntityMapper);
    }

    @Bean
    public IRoleServicePort roleServicePort() {
        return new RoleUseCase(rolePersistencePort());
    }

    @Bean
    public IAuthPersistencePort authPersistencePort() {
        return new AuthAdapter(userRepository, roleRepository, passwordEncoder, authenticationManager, roleEntityMapper, jwtService);
    }

    @Bean
    public IAuthServicePort authServicePort() {
        return new AuthUseCase(authPersistencePort());
    }

    @Bean
    public IOrderPersistencePort orderPersistencePort() {
        return new OrderAdapter(orderRepository, orderEntityMapper);
    }

    @Bean
    public IOrderItemPersistencePort productSoldPersistencePort() {
        return new OrderItemAdapter(orderItemRepository, orderItemEntityMapper);
    }

    @Bean
    public IOrderServicePort orderServicePort() {
        return new OrderUseCase(orderPersistencePort(), productSoldPersistencePort());
    }
}
