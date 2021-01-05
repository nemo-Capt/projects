package factory;

import factory.restaurants.BurgerKing;
import factory.restaurants.McDonalds;
import factory.restaurants.Restaurant;

public class Main {
    public static void main(String[] args) {

        Restaurant mcdonalds = new McDonalds();
        mcdonalds.orderBurger(BurgerType.CHEESEBURGER);

        Restaurant burgerKing = new BurgerKing();
        burgerKing.orderBurger(BurgerType.CHICKENBURGER);

    }
}
