package com.example.sophmeth_proj4;

public enum Topping {
    SAUSAGE,
    PEPPERONI,
    GREEN_PEPPER,
    ONION,
    MUSHROOM,
    BBQ_CHICKEN,
    PROVOLONE,
    CHEDDAR,
    BEEF,
    HAM,
    EXTRA_CHEESE,
    BLACK_OLIVES,
    SPINACH;

    @Override
    public String toString() {
        // Capitalize the first letter and lowercase the rest
        String name = name().toLowerCase();
        return name.substring(0, 1).toUpperCase() + name.substring(1);
    }
}
