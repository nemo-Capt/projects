package factory.burgers;

public class McDonaldsChickenburger extends Burger {

    @Override
    public void createBurger() {
        super.createBurger();
        System.out.println("*McDonalds' chickenburger*");
    }

}
