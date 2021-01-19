package strategy;

public abstract class Car {

    private Drivable drivable;

    public void setDrivable(Drivable drivable){
        this.drivable = drivable;
    }

    public void performDrive(){
        drivable.drive();
    }
}
