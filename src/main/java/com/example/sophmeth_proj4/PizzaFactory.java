package com.example.sophmeth_proj4;



import java.util.ArrayList;

public interface PizzaFactory {
    Pizza createDeluxe(Size size);
    Pizza createMeatzza(Size size);
    Pizza createBBQChicken(Size size);
    Pizza createBuildYourOwn(Size size, ArrayList<Topping> toppings);
}
