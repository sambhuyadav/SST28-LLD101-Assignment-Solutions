package com.example.payments;

public class SafeCashAdapter implements PaymentGateway {
    private final SafeCashClient safeCashClient;

    public SafeCashAdapter(SafeCashClient safeCashClient) {
        this.safeCashClient = java.util.Objects.requireNonNull(safeCashClient);
    }

    @Override
    public String charge(String customerId, int amountCents) {
        SafeCashPayment payment = new SafeCashPayment(amountCents, customerId);
        return payment.confirm();
    }
}