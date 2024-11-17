package com.example.sophmeth_proj4;



import java.util.ArrayList;
/**
 * Represents the pizza factory
 * @author paldeepsekhon, adityaponni
 */
public interface PizzaFactory {
    Pizza createDeluxe(Size size);
    Pizza createMeatzza(Size size);
    Pizza createBBQChicken(Size size);
    Pizza createBuildYourOwn(Size size, ArrayList<Topping> toppings);
}
