package com.example.payments;

import java.util.Objects;

public class OrderService {
    private final PaymentGateway paymentGateway;

    public OrderService(PaymentGateway paymentGateway) {
        this.paymentGateway = java.util.Objects.requireNonNull(paymentGateway);
    }

    public String processOrder(String customerId, int amountCents) {
        return paymentGateway.charge(customerId, amountCents);
    }
}
