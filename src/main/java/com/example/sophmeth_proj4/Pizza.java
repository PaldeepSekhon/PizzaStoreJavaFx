package com.example.sophmeth_proj4;

import java.util.ArrayList;

public abstract class Pizza {
    private ArrayList<Topping> toppings;
    private Crust crust;
    private Size size;

    public Pizza(Crust crust, Size size) {
        this.crust = crust;
        this.size = size;
        this.toppings = new ArrayList<>();
    }

    public void addTopping(Topping topping) {
        if (toppings.size() < 7) {
            toppings.add(topping);
        }
    }

    public void removeTopping(Topping topping) {
        toppings.remove(topping);
    }

    public ArrayList<Topping> getToppings() {
        return toppings;
    }

    public Crust getCrust() {
        return crust;
    }

    public Size getSize() {
        return size;
    }

    public abstract double price();
}
