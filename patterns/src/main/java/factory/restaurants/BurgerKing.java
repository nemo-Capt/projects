package factory.restaurants;

import factory.BurgerType;
import factory.burgers.Burger;
import factory.burgers.BurgerKingCheeseburger;
import factory.burgers.BurgerKingChickenburger;

public class BurgerKing extends Restaurant {

    @Override
    public Burger createBurger(BurgerType type) {
        Burger burger = null;
        switch (type) {
            case CHEESEBURGER:
                burger = new BurgerKingCheeseburger();
                break;
            case CHICKENBURGER:
                burger = new BurgerKingChickenburger();
                break;
        }
        return burger;
    }

}
