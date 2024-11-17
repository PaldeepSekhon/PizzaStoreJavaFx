package com.example.sophmeth_proj4;
import java.util.ArrayList;
/**
 * Represents Chicago style pizzas
 * @author paldeepsekhon, adityaponni
 */
public class ChicagoPizza implements PizzaFactory {

    @Override
    public Pizza createDeluxe(Size size) {
        return new Deluxe(Crust.DEEP_DISH, size);
    }

    @Override
    public Pizza createMeatzza(Size size) {
        return new Meatzza(Crust.STUFFED, size);
    }

    @Override
    public Pizza createBBQChicken(Size size) {
        return new BBQChicken(Crust.PAN, size);
    }

    @Override
    public Pizza createBuildYourOwn(Size size, ArrayList<Topping> toppings) {
        Pizza pizza = new BuildYourOwn(Crust.PAN, size);
        for (Topping topping : toppings) {
            pizza.addTopping(topping);
        }
        return pizza;
    }
}
