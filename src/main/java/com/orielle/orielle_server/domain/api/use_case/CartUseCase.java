package com.orielle.orielle_server.domain.api.use_case;

import com.orielle.orielle_server.domain.api.ICartServicePort;
import com.orielle.orielle_server.domain.exception.AlreadyExistsFieldException;
import com.orielle.orielle_server.domain.exception.EmptyCartException;
import com.orielle.orielle_server.domain.exception.InvalidProductQuantityException;
import com.orielle.orielle_server.domain.exception.NotFoundException;
import com.orielle.orielle_server.domain.model.*;
import com.orielle.orielle_server.domain.spi.*;
import com.orielle.orielle_server.domain.util.Constants;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CartUseCase implements ICartServicePort {
    private final ICartPersistencePort cartPersistencePort;
    private final ICartProductPersistencePort cartProductPersistencePort;
    private final IAuthPersistencePort authPersistencePort;
    private final IProductPersistencePort productPersistencePort;
    private final IOrderPersistencePort orderPersistencePort;

    public CartUseCase(ICartPersistencePort cartPersistencePort, ICartProductPersistencePort cartProductPersistencePort, IAuthPersistencePort authPersistencePort, IProductPersistencePort productPersistencePort, IOrderPersistencePort orderPersistencePort) {
        this.cartPersistencePort = cartPersistencePort;
        this.cartProductPersistencePort = cartProductPersistencePort;
        this.authPersistencePort = authPersistencePort;
        this.productPersistencePort = productPersistencePort;
        this.orderPersistencePort = orderPersistencePort;
    }

    @Override
    public Cart saveCart(Cart cart) {
        if (cartPersistencePort.getCartByClientId(cart.getClientId()).isPresent()) {
            throw new AlreadyExistsFieldException(Constants.CART_ALREADY_EXISTS_MESSAGE);
        }

        Long clientId = authPersistencePort.getAuthenticatedUserId();

        cart.setClientId(clientId);
        cart.setCreatedAt(LocalDateTime.now());
        cart.setUpdatedAt(LocalDateTime.now());

        Cart cartSaved = cartPersistencePort.saveCart(cart);
        List<CartProduct> products = cartProductPersistencePort.getAllProducts(cartSaved.getCartId());
        cartSaved.setProducts(products);

        return cartSaved;
    }

    @Override
    public Cart updateCart(Cart cart) {
        cart.setUpdatedAt(LocalDateTime.now());

        Cart cartUpdated = cartPersistencePort.saveCart(cart);
        List<CartProduct> products = cartProductPersistencePort.getAllProducts(cartUpdated.getCartId());
        cartUpdated.setProducts(products);

        return cartUpdated;
    }

    @Override
    public Cart getCartByClientId() {
        Long clientId = authPersistencePort.getAuthenticatedUserId();

        Cart cart = cartPersistencePort.getCartByClientId(clientId)
            .orElse(null);

        if (cart != null) {
            List<CartProduct> products = cartProductPersistencePort.getAllProducts(cart.getCartId());
            cart.setProducts(products);
        }

        return cart;
    }

    @Override
    public List<Long> getCartsIds() {
        return cartPersistencePort.getCartsIds();
    }

    @Override
    public void buyCartProducts() {
        Map<Long, Long> originalQuantities = new HashMap<>();
        Order orderSaved;

        Cart clientCart = getCartByClientId();

        if (clientCart == null) throw new NotFoundException(Constants.CART_NOT_FOUND);

        List<OrderItem> productsSold = getProductsSold(clientCart, originalQuantities);

        cartProductPersistencePort.removeAllCartProducts(clientCart.getCartId());
        orderSaved = buildOrder(clientCart, productsSold);

        orderPersistencePort.saveOrder(orderSaved);

        setCartQuantityAndPriceToZero(clientCart);
    }

    private List<OrderItem> getProductsSold(Cart clientCart, Map<Long, Long> originalQuantities) {
        List<CartProduct> cartProducts = clientCart.getProducts();
        List<OrderItem> productsSold = new ArrayList<>();

        if (cartProducts.size() == Constants.ZERO_CONSTANT) {
            throw new EmptyCartException(Constants.EMPTY_CART_MESSAGE);
        }

        cartProducts.forEach(cartProduct -> {
            Product product = productPersistencePort.getProductById(cartProduct.getProductId())
                .orElseThrow(() -> new NotFoundException(Constants.PRODUCT_NOT_FOUND));

            if (cartProduct.getQuantity() > product.getQuantity()) {
                throw new InvalidProductQuantityException(Constants.INVALID_BUY_PRODUCT_QUANTITY_MESSAGE);
            }

            originalQuantities.put(cartProduct.getProductId(), cartProduct.getQuantity());

            insertOrderItemIntoList(productsSold, cartProduct);
            productPersistencePort.updateProductQuantity(product.getProductId(), cartProduct.getQuantity(), false);
        });

        return productsSold;
    }

    private void setCartQuantityAndPriceToZero(Cart clientCart) {
        clientCart.setTotalQuantity(Constants.QUANTITY_ZERO);
        clientCart.setTotalPrice(Constants.TOTAL_PRICE_ZERO);
        clientCart.setUpdatedAt(LocalDateTime.now());

        cartPersistencePort.saveCart(clientCart);
    }

    private Order buildOrder(Cart clientCart, List<OrderItem> productsSold) {
        Order order = new Order();

        order.setClientId(clientCart.getClientId());
        order.setTotalQuantity(clientCart.getTotalQuantity());
        order.setTotalPrice(clientCart.getTotalPrice());
        order.setProducts(productsSold);

        return order;
    }

    private void insertOrderItemIntoList(List<OrderItem> productsSold, CartProduct cartProduct) {
        OrderItem orderItem = new OrderItem();
        orderItem.setProductId(cartProduct.getProductId());
        orderItem.setQuantity(cartProduct.getQuantity());
        orderItem.setTotalPrice(cartProduct.getTotalPrice());

        productsSold.add(orderItem);
    }
}
