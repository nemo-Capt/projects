package factory.restaurants;

import factory.BurgerType;
import factory.burgers.Burger;

public abstract class Restaurant {

    public void orderBurger(BurgerType type) {

        Burger burger = createBurger(type);
        burger.createBurger();

        System.out.println("Here's your burger!" +
                "\n=========================");

    }

    protected abstract Burger createBurger(BurgerType type);
}
