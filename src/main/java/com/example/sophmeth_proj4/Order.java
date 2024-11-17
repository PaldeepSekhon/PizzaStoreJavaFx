package com.example.sophmeth_proj4;

import java.util.ArrayList;

public class Order {
    private static int orderCounter = 1; // Keeps track of the order number sequence
    private int number; // Unique order number
    private ArrayList<Pizza> pizzas; // List of pizzas in the order

    public Order() {
        this.number = orderCounter++;
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
        return calculateTotal() * 0.06625; // New Jersey sales tax of 6.625%
    }

    public double calculateOrderTotal() {
        return calculateTotal() + calculateSalesTax();
    }

    @Override
    public String toString() {
        return "Order #" + number + " | Total: $" + String.format("%.2f", calculateOrderTotal());
    }
}
