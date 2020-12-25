package facade;

public class Main {

    public static void main(String[] args) {

        Plate plate = new Plate();
        Fork fork = new Fork();
        Food food = new Food();

        Eating eating = new Eating(plate, fork, food);

        eating.startEating(false);
    }
}
