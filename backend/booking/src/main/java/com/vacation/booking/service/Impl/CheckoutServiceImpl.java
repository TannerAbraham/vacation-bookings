package com.vacation.booking.service.Impl;

import com.vacation.booking.dao.CartItemRepository;
import com.vacation.booking.dao.CartRepository;
import com.vacation.booking.dao.CustomerRepository;
import com.vacation.booking.entity.Cart;
import com.vacation.booking.entity.CartItem;
import com.vacation.booking.entity.Customer;
import com.vacation.booking.service.CheckoutService;
import com.vacation.booking.service.dto.Purchase;
import com.vacation.booking.service.dto.PurchaseResponse;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.UUID;

@Service
public class CheckoutServiceImpl implements CheckoutService {

    private final CustomerRepository customerRepository;
    private final CartRepository cartRepository;
    private final CartItemRepository cartItemRepository;

    @Autowired
    public CheckoutServiceImpl(CustomerRepository customerRepository,
                               CartRepository cartRepository,
                               CartItemRepository cartItemRepository) {
        this.customerRepository = customerRepository;
        this.cartRepository = cartRepository;
        this.cartItemRepository = cartItemRepository;
    }

    @Override
    @Transactional
    public PurchaseResponse placeOrder(Purchase purchase) {

        Cart cart = purchase.getCart();

        // Generate a unique order tracking number
        String orderTrackingNumber = UUID.randomUUID().toString();
        cart.setOrderTrackingNumber(orderTrackingNumber);

        // Associate cart items with the cart
        Set<CartItem> cartItems = purchase.getCartItems();
        cartItems.forEach(item -> {
            item.setCart(cart);
            cart.getCartItems().add(item);
        });

        // Associate cart with customer
        Customer customer = purchase.getCustomer();
        customer.getCarts().add(cart);
        cart.setCustomer(customer);

        // Persist the customer (cascades to cart and cart items)
        customerRepository.save(customer);

        return new PurchaseResponse(orderTrackingNumber);
    }
}
