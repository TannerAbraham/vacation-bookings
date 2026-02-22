package com.vacation.booking.service.dto;

import com.vacation.booking.entity.Cart;
import com.vacation.booking.entity.CartItem;
import com.vacation.booking.entity.Customer;
import lombok.Data;

import java.util.Set;

@Data
public class Purchase {

    private Customer customer;
    private Cart cart;
    private Set<CartItem> cartItems;

    public void validate() {
        if (customer == null) {
            throw new IllegalArgumentException("Customer information is required");
        }
        if (cart == null) {
            throw new IllegalArgumentException("Cart information is required");
        }
        if (cartItems == null || cartItems.isEmpty()) {
            throw new IllegalArgumentException("Cart must contain at least one item");
        }
    }
}