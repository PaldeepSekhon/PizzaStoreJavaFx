package com.example.sophmeth_proj4;

public class GlobalStore {
    private static final StoreOrders storeOrders = new StoreOrders();

    private GlobalStore() {
        // Private constructor to prevent instantiation
    }
    public static StoreOrders getStoreOrders() {
        return storeOrders;
    }
}
