package org.overwatch.roles;

import org.overwatch.Hero;

public class TankHero extends Hero {

    private int shieldValue;

    public TankHero(String name, int hp, int attack, int shieldValue) {
        super(name, hp, attack);
        this.shieldValue = shieldValue;
    }

    @Override
    public void heroInfo() {
        super.heroInfo();
        System.out.println("Shield value: " + shieldValue);
    }
}
