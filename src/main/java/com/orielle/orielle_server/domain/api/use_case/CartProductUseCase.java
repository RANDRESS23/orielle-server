package com.orielle.orielle_server.domain.api.use_case;

import com.orielle.orielle_server.domain.api.ICartProductServicePort;
import com.orielle.orielle_server.domain.api.ICartServicePort;
import com.orielle.orielle_server.domain.exception.InvalidProductQuantityException;
import com.orielle.orielle_server.domain.exception.NotFoundException;
import com.orielle.orielle_server.domain.model.Cart;
import com.orielle.orielle_server.domain.model.CartProduct;
import com.orielle.orielle_server.domain.model.CustomPage;
import com.orielle.orielle_server.domain.model.Product;
import com.orielle.orielle_server.domain.spi.*;
import com.orielle.orielle_server.domain.util.CartProductValidation;
import com.orielle.orielle_server.domain.util.Constants;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

public class CartProductUseCase implements ICartProductServicePort {
    private final ICartProductPersistencePort cartProductPersistencePort;
    private final ICartPersistencePort cartPersistencePort;
    private final IProductPersistencePort productPersistencePort;
    private final ICartServicePort cartServicePort;
    private final IAuthPersistencePort authPersistencePort;
    private final CartProductValidation cartProductValidation;

    public CartProductUseCase(ICartProductPersistencePort cartProductPersistencePort, ICartPersistencePort cartPersistencePort, IProductPersistencePort productPersistencePort, ICartServicePort cartServicePort, IAuthPersistencePort authPersistencePort, CartProductValidation cartProductValidation) {
        this.cartProductPersistencePort = cartProductPersistencePort;
        this.cartPersistencePort = cartPersistencePort;
        this.productPersistencePort = productPersistencePort;
        this.cartServicePort = cartServicePort;
        this.authPersistencePort = authPersistencePort;
        this.cartProductValidation = cartProductValidation;
    }

    @Override
    public Cart saveCartProduct(CartProduct cartProduct) {
        Long clientId = authPersistencePort.getAuthenticatedUserId();
        Cart clientCart = cartServicePort.getCartByClientId();
        cartProductValidation.validateCartProduct(cartProduct);

        Product productResponse = productPersistencePort.getProductById(cartProduct.getProductId())
            .orElseThrow(() -> new NotFoundException(Constants.PRODUCT_NOT_FOUND));

        if (cartProduct.getQuantity() <= productResponse.getQuantity()) {
            clientCart = createOrUpdateCart(clientCart, clientId, cartProduct, productResponse);
        } else {
            throw new InvalidProductQuantityException(Constants.INVALID_BUY_PRODUCT_QUANTITY_MESSAGE);
        }

        return clientCart;
    }

    @Override
    public Cart removeCartProduct(CartProduct cartProduct) {
        Cart clientCart = cartServicePort.getCartByClientId();
        Long quantityProduct = cartProduct.getQuantity();

        if (clientCart == null) {
            throw new NotFoundException(Constants.CART_NOT_FOUND);
        }

        cartProductValidation.validateCartProduct(cartProduct);
        Product productResponse = productPersistencePort.getProductById(cartProduct.getProductId())
            .orElseThrow(() -> new NotFoundException(Constants.PRODUCT_NOT_FOUND));
        
        AtomicBoolean isTotalProductQuantity = new AtomicBoolean(false);
        List<CartProduct> newCartProducts = new ArrayList<>();
        List<CartProduct> cartProducts = getCartProducts(cartProduct, clientCart, productResponse, isTotalProductQuantity, newCartProducts);

        if (isTotalProductQuantity.get()) {
            cartProductPersistencePort.removeCartProduct(cartProduct);
            setClientCartValues(clientCart, productResponse, newCartProducts, quantityProduct);

            return cartServicePort.updateCart(clientCart);
        }

        cartProductPersistencePort.saveCartProduct(cartProduct);
        setClientCartValues(clientCart, productResponse, cartProducts, quantityProduct);

        return cartServicePort.updateCart(clientCart);
    }

    @Override
    public CustomPage<CartProduct> getAllCartProducts(Integer page, Integer size, Boolean ascending, String category) {
        Cart clientCart = cartServicePort.getCartByClientId();

        if (clientCart == null) throw new NotFoundException(Constants.CART_NOT_FOUND);

        CustomPage<CartProduct> cartProducts = cartProductPersistencePort.getAllCartProducts(page, size, ascending, clientCart.getCartId());
        List<CartProduct> listOfProductPage = cartProducts.getContent();

        CustomPage<CartProduct> customCartProduct = new CustomPage<>();
        setCustomPageValues(customCartProduct, cartProducts, listOfProductPage);

        if (!category.equals(Constants.DEFAULT_CATEGORY_PARAM)) {
            filterProductsByCategory(customCartProduct, category);
        }

        return customCartProduct;
    }

    @Override
    public Cart getAllCartProductsInTheCart() {
        return cartServicePort.getCartByClientId();
    }

    public void filterProductsByCategory(CustomPage<CartProduct> customCartProduct, String category) {
        List<CartProduct> productsFilteredByCategory = customCartProduct
            .getContent()
            .stream()
            .filter(cartProductItem -> {
                Product product = productPersistencePort.getProductById(cartProductItem.getProductId())
                        .orElseThrow(() -> new NotFoundException(Constants.PRODUCT_NOT_FOUND));

                return category.equalsIgnoreCase(product.getCategory().getName());
            })
            .toList();

        customCartProduct.setContent(productsFilteredByCategory);
        customCartProduct.setTotalElements(productsFilteredByCategory.size());
    }

    public void setCustomPageValues(CustomPage<CartProduct> customPage, CustomPage<CartProduct> cartProducts, List<CartProduct> listOfProductPage) {
        customPage.setPageNumber(cartProducts.getPageNumber());
        customPage.setPageSize(cartProducts.getPageSize());
        customPage.setTotalElements(cartProducts.getTotalElements());
        customPage.setTotalPages(cartProducts.getTotalPages());
        customPage.setContent(listOfProductPage);
    }

    public static List<CartProduct> getCartProducts(CartProduct cartProduct, Cart clientCart, Product productResponse, AtomicBoolean isTotalProductQuantity, List<CartProduct> newCartProducts) {
        List<CartProduct> cartProducts = clientCart.getProducts();
        AtomicBoolean isExistsCartProductInTheCart = new AtomicBoolean(false);

        cartProducts.forEach(focusedCartProduct -> {
            if (focusedCartProduct.getProductId().equals(cartProduct.getProductId())) {
                validateEqualsProduct(cartProduct, focusedCartProduct, isExistsCartProductInTheCart, isTotalProductQuantity, productResponse);
            } else newCartProducts.add(focusedCartProduct);
        });

        if (!isExistsCartProductInTheCart.get()) {
            throw new NotFoundException(Constants.CART_PRODUCT_NOT_FOUND);
        }

        return cartProducts;
    }

    public static void setCartProductValues(CartProduct cartProduct, CartProduct focusedCartProduct) {
        cartProduct.setCartProductId(focusedCartProduct.getCartProductId());
        cartProduct.setCartId(focusedCartProduct.getCartId());
        cartProduct.setTotalPrice(focusedCartProduct.getTotalPrice());
        cartProduct.setQuantity(focusedCartProduct.getQuantity());
    }

    public void setClientCartValues(Cart clientCart, Product productResponse, List<CartProduct> newCartProducts, Long quantityProduct) {
        clientCart.setTotalQuantity(clientCart.getTotalQuantity() - quantityProduct);
        clientCart.setTotalPrice(clientCart.getTotalPrice().subtract(productResponse.getPrice().multiply(BigDecimal.valueOf(quantityProduct))));
        clientCart.setProducts(newCartProducts);
    }

    public static void validateEqualsProduct(CartProduct cartProduct, CartProduct focusedCartProduct, AtomicBoolean isExistsCartProductInTheCart, AtomicBoolean isTotalProductQuantity, Product productResponse) {
        if (cartProduct.getQuantity() > focusedCartProduct.getQuantity()) {
            throw new InvalidProductQuantityException(Constants.INVALID_REMOVE_PRODUCT_QUANTITY_FROM_CART_MESSAGE);
        }

        isExistsCartProductInTheCart.set(true);

        if (cartProduct.getQuantity().equals(focusedCartProduct.getQuantity())) {
            setCartProductValues(cartProduct, focusedCartProduct);

            isTotalProductQuantity.set(true);
        } else {
            focusedCartProduct.setQuantity(focusedCartProduct.getQuantity() - cartProduct.getQuantity());
            focusedCartProduct.setTotalPrice(focusedCartProduct.getTotalPrice().subtract(productResponse.getPrice().multiply(BigDecimal.valueOf(cartProduct.getQuantity()))));

            setCartProductValues(cartProduct, focusedCartProduct);
        }
    }

    public Cart createOrUpdateCart(Cart clientCart, Long clientId, CartProduct cartProduct, Product productResponse) {
        if (clientCart == null) {
            clientCart = createCart(clientId);

            CartProduct cartProductSaved = saveCartProductInBD(clientCart, cartProduct, productResponse);
            List<CartProduct> cartProducts = clientCart.getProducts();
            cartProducts.add(cartProductSaved);

            return saveCartInBD(clientCart, cartProduct, productResponse, cartProducts);
        } else {
            clientCart.setTotalQuantity(clientCart.getTotalQuantity() + cartProduct.getQuantity());
            clientCart.setTotalPrice(clientCart.getTotalPrice().add(productResponse.getPrice().multiply(BigDecimal.valueOf(cartProduct.getQuantity()))));

            return cartServicePort.updateCart(clientCart);
        }
    }

    public CartProduct saveCartProductInBD(Cart clientCart, CartProduct cartProduct, Product productResponse) {
        cartProduct.setCartId(clientCart.getCartId());
        cartProduct.setTotalPrice(
                productResponse.getPrice().multiply(BigDecimal.valueOf(cartProduct.getQuantity()))
        );

        return cartProductPersistencePort.saveCartProduct(cartProduct);
    }

    public Cart saveCartInBD(Cart clientCart, CartProduct cartProduct, Product productResponse, List<CartProduct> cartProducts) {
        clientCart.setTotalQuantity(cartProduct.getQuantity());
        clientCart.setTotalPrice(productResponse.getPrice().multiply(BigDecimal.valueOf(cartProduct.getQuantity())));
        clientCart.setProducts(cartProducts);

        return cartServicePort.saveCart(clientCart);
    }

    public Cart createCart(Long clientId) {
        Long cartId = (long)cartPersistencePort.getCartsIds().size() + 1;

        return new Cart(
                cartId,
                clientId,
                0L,
                new BigDecimal(0),
                new ArrayList<>(),
                LocalDateTime.now(),
                LocalDateTime.now()
        );
    }
}
