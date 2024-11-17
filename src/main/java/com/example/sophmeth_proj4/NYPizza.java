package com.example.sophmeth_proj4;

import java.util.ArrayList;
/**
 * Represents New york style pizzas
 * @author paldeepsekhon, adityaponni
 */
public class NYPizza implements PizzaFactory {

    @Override
    public Pizza createDeluxe(Size size) {
        return new Deluxe(Crust.BROOKLYN, size);
    }

    @Override
    public Pizza createMeatzza(Size size) {
        return new Meatzza(Crust.HAND_TOSSED, size);
    }

    @Override
    public Pizza createBBQChicken(Size size) {
        return new BBQChicken(Crust.THIN, size);
    }

    @Override
    public Pizza createBuildYourOwn(Size size, ArrayList<Topping> toppings) {
        Pizza pizza = new BuildYourOwn(Crust.HAND_TOSSED, size);
        for (Topping topping : toppings) {
            pizza.addTopping(topping);
        }
        return pizza;
    }
}
