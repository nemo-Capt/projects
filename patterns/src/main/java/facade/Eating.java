package facade;

public class Eating {

    private Plate plate;
    private Fork fork;
    private  Food food;

    public Eating(Plate plate, Fork fork, Food food) {
        this.plate = plate;
        this.fork = fork;
        this.food = food;
    }

    public void startEating(boolean isWashed) {
        plate.washPlate(isWashed);
        plate.getPlate();
        fork.getFork();
        food.getFood();
    }
}
