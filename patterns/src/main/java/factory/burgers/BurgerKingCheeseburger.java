package factory.burgers;

public class BurgerKingCheeseburger extends Burger {

    @Override
    public void createBurger() {
        super.createBurger();
        System.out.println("*BurgerKing's cheeseburger*");
    }

}
