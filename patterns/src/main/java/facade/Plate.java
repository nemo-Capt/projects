package facade;

public class Plate {

    void washPlate(boolean isWashed) {
        if(isWashed) {
            System.out.println("Plate is clean!");
        }
        else {
            System.out.println("Plate is dirty!");
            System.out.println("Washing plate...");
            System.out.println("Plate is clean!");
        }
    }

    void getPlate() {
        System.out.println("Plate is ready!");
    }
}
