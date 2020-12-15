package org.overwatch.roles;

import org.overwatch.Hero;

public class SupportHero extends Hero {

    private int healValue;

    public SupportHero(String name, int hp, int attack, int healValue) {
        super(name, hp, attack);
        this.healValue = healValue;
    }

    @Override
    public void heroInfo() {
        super.heroInfo();
        System.out.println("Heal value: " + healValue);
    }
}
