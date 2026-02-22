package com.vacation.booking.controller;

import com.vacation.booking.service.CheckoutService;
import com.vacation.booking.service.dto.Purchase;
import com.vacation.booking.service.dto.PurchaseResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("http://localhost:4200")
@RestController
@RequestMapping("/api/checkout")
public class CheckoutController {

    private final CheckoutService checkoutService;

    @Autowired
    public CheckoutController(CheckoutService checkoutService) {
        this.checkoutService = checkoutService;
    }

    @PostMapping("/purchase")
    public ResponseEntity<PurchaseResponse> placeOrder(@RequestBody Purchase purchase) {

        // Angular frontend input validation
        purchase.validate();

        // Pass validated DTO to the service layer
        PurchaseResponse purchaseResponse = checkoutService.placeOrder(purchase);

        return ResponseEntity.status(HttpStatus.CREATED).body(purchaseResponse);
    }
}