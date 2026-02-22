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
}
