package com.example.sophmeth_proj4;

import java.util.ArrayList;
/**
 * Represents the current order
 * @author paldeepsekhon, adityaponni
 */
public class Order {
    private static int orderCounter = 1; // Keeps track of the order number sequence
    private int number; // Unique order number
    private ArrayList<Pizza> pizzas; // List of pizzas in the order

    // Default constructor
    public Order() {
        this.number = orderCounter++;
        this.pizzas = new ArrayList<>();
    }

    // Constructor with explicit order number
    public Order(int orderNumber) {
        this.number = orderNumber;
        this.pizzas = new ArrayList<>();
    }

    public int getNumber() {
        return number;
    }

    public ArrayList<Pizza> getPizzas() {
        return pizzas;
    }

    public void addPizza(Pizza pizza) {
        pizzas.add(pizza);
    }

    public void removePizza(Pizza pizza) {
        pizzas.remove(pizza);
    }

    public double calculateTotal() {
        double total = 0;
        for (Pizza pizza : pizzas) {
            total += pizza.price();
        }
        return total;
    }

    public void clearPizzas() {
        pizzas.clear();
    }

    public double calculateSalesTax() {
        return calculateTotal() * 0.06625;
    }

    public double calculateOrderTotal() {
        return calculateTotal() + calculateSalesTax();
    }

    @Override
    public String toString() {
        return "Order #" + number + " | Total: $" + String.format("%.2f", calculateOrderTotal());
    }
}
