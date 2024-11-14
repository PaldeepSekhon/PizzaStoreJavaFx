package com.example.sophmeth_proj4;

public class Meatzza extends Pizza {
    public Meatzza(Crust crust, Size size) {
        super(crust, size);
        // Add the default toppings for Meatzza pizza
        getToppings().add(Topping.SAUSAGE);
        getToppings().add(Topping.PEPPERONI);
        getToppings().add(Topping.BEEF);
        getToppings().add(Topping.HAM);
    }

    @Override
    public double price() {
        switch (getSize()) {
            case SMALL: return 17.99;
            case MEDIUM: return 19.99;
            case LARGE: return 21.99;
            default: return 0;
        }
    }
}
