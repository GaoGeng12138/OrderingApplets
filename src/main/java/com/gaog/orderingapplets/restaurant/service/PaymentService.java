package com.gaog.orderingapplets.restaurant.service;

import com.gaog.orderingapplets.restaurant.entity.Order;
import com.gaog.orderingapplets.restaurant.entity.payment.PaymentResponse;

public interface PaymentService {
    PaymentResponse createPayment(Order order);

    boolean verifyPayment(String paymentId, String paymentInfo);
} 