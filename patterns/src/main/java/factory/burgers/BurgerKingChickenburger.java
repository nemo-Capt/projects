package factory.burgers;

public class BurgerKingChickenburger extends Burger {

    @Override
    public void createBurger() {
        super.createBurger();
        System.out.println("*BurgerKing's chickenburger*");
    }

}
