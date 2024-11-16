package com.example.sophmeth_proj4;

public class GlobalOrder {
    private static  Order currentOrder = new Order();

    private GlobalOrder() {
        // Private constructor to prevent instantiation
    }

    public static Order getCurrentOrder() {
        return currentOrder;
    }

    public static void resetCurrentOrder() {
        currentOrder = new Order();
    }


}
