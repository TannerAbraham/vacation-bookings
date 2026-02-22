package com.vacation.booking.service;

import com.vacation.booking.service.dto.Purchase;
import com.vacation.booking.service.dto.PurchaseResponse;

public interface CheckoutService {

    PurchaseResponse placeOrder(Purchase purchase);
}
