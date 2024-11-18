package com.example.sophmeth_proj4;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class BuildYourOwnTest {



    @Test
    public void testSmallPizzaNoToppings() {
        BuildYourOwn pizza = new BuildYourOwn(Crust.PAN, Size.SMALL);
        assertEquals(8.99, pizza.price(), 0.01, "Price for a small pizza with no toppings should be $8.99.");
    }

    @Test
    public void testMediumPizzaWithThreeToppings() {
        BuildYourOwn pizza = new BuildYourOwn(Crust.DEEP_DISH, Size.MEDIUM);
        pizza.addTopping(Topping.MUSHROOM);
        pizza.addTopping(Topping.ONION);
        pizza.addTopping(Topping.GREEN_PEPPER);
        double expectedPrice = 10.99 + (3 * 1.69);
        assertEquals(expectedPrice, pizza.price(), 0.01, "Price for a medium pizza with 3 toppings should include base and topping costs.");
    }

    @Test
    public void testLargePizzaWithMaxToppings() {
        BuildYourOwn pizza = new BuildYourOwn(Crust.STUFFED, Size.LARGE);
        pizza.addTopping(Topping.SAUSAGE);
        pizza.addTopping(Topping.PEPPERONI);
        pizza.addTopping(Topping.HAM);
        pizza.addTopping(Topping.BBQ_CHICKEN);
        pizza.addTopping(Topping.GREEN_PEPPER);
        pizza.addTopping(Topping.ONION);
        pizza.addTopping(Topping.MUSHROOM);
        double expectedPrice = 12.99 + (7 * 1.69);
        assertEquals(expectedPrice, pizza.price(), 0.01, "Price for a large pizza with maximum toppings (7) should include base and topping costs.");
    }

    @Test
    public void testSmallPizzaWithOneTopping() {
        BuildYourOwn pizza = new BuildYourOwn(Crust.PAN, Size.SMALL);
        pizza.addTopping(Topping.BEEF);
        double expectedPrice = 8.99 + (1 * 1.69);
        assertEquals(expectedPrice, pizza.price(), 0.01, "Price for a small pizza with 1 topping should include base and topping costs.");
    }

    @Test
    public void testLargePizzaNoToppings() {
        BuildYourOwn pizza = new BuildYourOwn(Crust.STUFFED, Size.LARGE);
        assertEquals(12.99, pizza.price(), 0.01, "Price for a large pizza with no toppings should be $12.99.");
    }

    @Test
    public void testExceedMaximumToppings() {
        BuildYourOwn pizza = new BuildYourOwn(Crust.PAN, Size.MEDIUM);
        for (int i = 0; i < 8; i++) {
            pizza.addTopping(Topping.MUSHROOM);
        }
        double expectedPrice = 10.99 + (7 * 1.69); // Only 7 toppings are allowed
        assertEquals(expectedPrice, pizza.price(), 0.01, "Price should only account for the first 7 toppings.");
    }

    @Test
    public void testNullSize() {
        BuildYourOwn pizza = new BuildYourOwn(Crust.PAN, null);
        assertEquals(0.0, pizza.price(), 0.01, "Price should be 0 if the size is null.");
    }

    @Test
    public void testCrustVariation() {
        BuildYourOwn pizza1 = new BuildYourOwn(Crust.PAN, Size.SMALL);
        BuildYourOwn pizza2 = new BuildYourOwn(Crust.DEEP_DISH, Size.SMALL);
        assertEquals(pizza1.price(), pizza2.price(), 0.01, "Price should not vary based on crust type.");
    }



}