package com.example.sophmeth_proj4;

import java.util.ArrayList;

public class OrderManager {
    private ArrayList<Order> orders; // List of all orders

    public OrderManager() {
        this.orders = new ArrayList<>();
    }

    public void addOrder(Order order) {
        orders.add(order);
    }

    public void removeOrder(Order order) {
        orders.remove(order);
    }

    public Order getOrderByNumber(int orderNumber) {
        for (Order order : orders) {
            if (order.getNumber() == orderNumber) {
                return order;
            }
        }
        return null; // Order not found
    }

    public ArrayList<Order> getAllOrders() {
        return orders;
    }

    public void printAllOrders() {
        for (Order order : orders) {
            System.out.println(order);
        }
    }

    public void exportOrdersToFile(String filePath) {
        try (java.io.PrintWriter writer = new java.io.PrintWriter(new java.io.File(filePath))) {
            for (Order order : orders) {
                writer.println("Order #" + order.getNumber());
                for (Pizza pizza : order.getPizzas()) {
                    writer.println("- " + pizza.getClass().getSimpleName() + ": $" + String.format("%.2f", pizza.price()));
                }
                writer.println("Subtotal: $" + String.format("%.2f", order.calculateTotal()));
                writer.println("Sales Tax: $" + String.format("%.2f", order.calculateSalesTax()));
                writer.println("Total: $" + String.format("%.2f", order.calculateOrderTotal()));
                writer.println();
            }
        } catch (Exception e) {
            System.out.println("Failed to export orders: " + e.getMessage());
        }
    }
}
