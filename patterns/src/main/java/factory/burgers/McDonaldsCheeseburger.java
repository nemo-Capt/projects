package factory.burgers;

public class McDonaldsCheeseburger extends Burger {

    @Override
    public void createBurger() {
        super.createBurger();
        System.out.println("*McDonalds' cheeseburger*");
    }

}
