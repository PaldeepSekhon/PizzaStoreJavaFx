package com.example.sophmeth_proj4;

public class GlobalOrder {
    private static Order currentOrder = new Order();

    private GlobalOrder() {
    }

    public static Order getCurrentOrder() {
        return currentOrder;
    }

    public static void resetCurrentOrder() {
        int lastOrderNumber = currentOrder.getNumber();
        currentOrder = new Order(lastOrderNumber + 1);
    }
}
