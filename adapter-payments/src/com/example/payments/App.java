package com.example.payments;

import java.util.HashMap;
import java.util.Map;

public class App {
    public static void main(String[] args) {
        FastPayClient fastPayClient = new FastPayClient();
        SafeCashClient safeCashClient = new SafeCashClient();

        Map<String, PaymentGateway> gatewayRegistry = new HashMap<>();
        gatewayRegistry.put("fastpay", new FastPayAdapter(fastPayClient));
        gatewayRegistry.put("safecash", new SafeCashAdapter(safeCashClient));

        OrderService fastPayOrderService = new OrderService(gatewayRegistry.get("fastpay"));
        OrderService safeCashOrderService = new OrderService(gatewayRegistry.get("safecash"));

        String fastPayTxn = fastPayOrderService.processOrder("cust123", 1000);
        String safeCashTxn = safeCashOrderService.processOrder("cust456", 2000);

        System.out.println("FastPay Transaction ID: " + fastPayTxn);
        System.out.println("SafeCash Transaction ID: " + safeCashTxn);
    }
}
