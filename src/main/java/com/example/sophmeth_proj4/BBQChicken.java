package com.example.sophmeth_proj4;
/**
 * Represents BBQChicken pizza with predefined topics
 * @author paldeepsekhon, adityaponni
 */
public class BBQChicken extends Pizza {
    public BBQChicken(Crust crust, Size size) {
        super(crust, size);
        // Add the default toppings for BBQ Chicken pizza
        getToppings().add(Topping.BBQ_CHICKEN);
        getToppings().add(Topping.GREEN_PEPPER);
        getToppings().add(Topping.PROVOLONE);
        getToppings().add(Topping.CHEDDAR);
    }

    @Override
    public double price() {
        switch (getSize()) {
            case SMALL: return 14.99;
            case MEDIUM: return 16.99;
            case LARGE: return 19.99;
            default: return 0;
        }
    }
}
