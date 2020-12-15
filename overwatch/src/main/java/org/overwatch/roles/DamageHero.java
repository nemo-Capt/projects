package org.overwatch.roles;

import org.overwatch.Hero;

public class DamageHero extends Hero {

    private double critValue;

    public DamageHero(String name, int hp, int attack, double critValue) {
        super(name, hp, attack);
        this.critValue = critValue;
    }

    @Override
    public void heroInfo() {
        super.heroInfo();
        System.out.println("Crit value: " + critValue * 100 + "%");
    }
}
