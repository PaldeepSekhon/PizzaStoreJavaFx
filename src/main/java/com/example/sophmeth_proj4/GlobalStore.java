package com.example.sophmeth_proj4;
/**
 * Represents the pizza place where orders are being placed to
 * @author paldeepsekhon, adityaponni
 */
public class GlobalStore {
    private static final StoreOrders storeOrders = new StoreOrders();

    private GlobalStore() {
        // Private constructor to prevent instantiation
    }
    public static StoreOrders getStoreOrders() {
        return storeOrders;
    }
}
