package com.example.sophmeth_proj4;
/**
 * Represents deluxe pizza with predefined topping
 * @author paldeepsekhon, adityaponni
 */
public class Deluxe extends Pizza {
    public Deluxe(Crust crust, Size size) {
        super(crust, size);
        // Add the default toppings for Deluxe pizza
        getToppings().add(Topping.SAUSAGE);
        getToppings().add(Topping.PEPPERONI);
        getToppings().add(Topping.GREEN_PEPPER);
        getToppings().add(Topping.ONION);
        getToppings().add(Topping.MUSHROOM);
    }

    @Override
    public double price() {
        switch (getSize()) {
            case SMALL: return 16.99;
            case MEDIUM: return 18.99;
            case LARGE: return 20.99;
            default: return 0;
        }
    }
}
