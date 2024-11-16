package com.example.sophmeth_proj4;

import java.util.HashMap;
import java.util.Map;

public class StoreOrders {
    private Map<Integer, Order> orders;

    public StoreOrders() {
        orders = new HashMap<>();
    }

    public void addOrder(Order order) {
        orders.put(order.getNumber(), order);
    }

    public Order getOrder(int orderNumber) {
        return orders.get(orderNumber);
    }

    public Map<Integer, Order> getAllOrders() {
        return orders;
    }

    public void removeOrder(int orderNumber) {
        orders.remove(orderNumber);
    }
}
