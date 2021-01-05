package factory.restaurants;

import factory.burgers.Burger;
import factory.BurgerType;
import factory.burgers.McDonaldsCheeseburger;
import factory.burgers.McDonaldsChickenburger;

public class McDonalds extends Restaurant {

    @Override
    public Burger createBurger(BurgerType type) {
        Burger burger = null;
        switch (type) {
            case CHEESEBURGER:
                burger = new McDonaldsCheeseburger();
                break;
            case CHICKENBURGER:
                burger = new McDonaldsChickenburger();
                break;
        }
        return burger;
    }

}
