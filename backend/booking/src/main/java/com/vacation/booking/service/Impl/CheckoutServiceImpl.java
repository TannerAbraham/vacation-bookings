package com.vacation.booking.service.Impl;

import com.vacation.booking.dao.CustomerRepository;
import com.vacation.booking.dao.DivisionRepository;
import com.vacation.booking.entity.Cart;
import com.vacation.booking.entity.CartItem;
import com.vacation.booking.entity.Customer;
import com.vacation.booking.entity.Division;
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
    private final DivisionRepository divisionRepository;

    @Autowired
    public CheckoutServiceImpl(CustomerRepository customerRepository,
                               DivisionRepository divisionRepository) {
        this.customerRepository = customerRepository;
        this.divisionRepository = divisionRepository;
    }

    @Override
    @Transactional
    public PurchaseResponse placeOrder(Purchase purchase) {

        Cart cart = purchase.getCart();

        // Generate a unique order tracking number
        String orderTrackingNumber = UUID.randomUUID().toString();
        cart.setOrderTrackingNumber(orderTrackingNumber);

        // Null out cart id — Angular sends id: 0, which Hibernate misreads as detached
        cart.setId(null);

        Set<CartItem> cartItems = purchase.getCartItems();
        cartItems.forEach(item -> {
            item.setId(null);
            cart.add(item);
        });

        Customer customer = purchase.getCustomer();

        // The Angular CustomerDto carries the existing customer's DB id (set in
        // vacation.component.ts ngOnDestroy). Use it BEFORE nulling the id to
        // load the existing customer from the DB and inherit their managed Division.
        Long existingCustomerId = customer.getId();
        if (existingCustomerId == null || existingCustomerId == 0) {
            throw new RuntimeException("Customer id is required to resolve Division.");
        }

        Customer existingCustomer = customerRepository.findById(existingCustomerId)
                .orElseThrow(() -> new RuntimeException(
                        "Customer not found with id: " + existingCustomerId));

        Division managedDivision = existingCustomer.getDivision();
        if (managedDivision == null) {
            throw new RuntimeException(
                    "No division found on existing customer with id: " + existingCustomerId);
        }

        // Now safe to null the id so Hibernate inserts a new customer record
        customer.setId(null);
        customer.setDivision(managedDivision);

        customer.getCarts().add(cart);
        cart.setCustomer(customer);

        customerRepository.save(customer);

        return new PurchaseResponse(orderTrackingNumber);
    }
}