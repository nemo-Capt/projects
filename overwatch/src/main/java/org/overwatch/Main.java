package org.overwatch;


import org.overwatch.roles.DamageHero;
import org.overwatch.roles.SupportHero;
import org.overwatch.roles.TankHero;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        Hero genji = new DamageHero("Genji", 200, 33, 0.2);
        Hero mercy = new SupportHero("Mercy", 200, 25, 70);
        Hero orisa = new TankHero("Orisa", 500, 40, 600);

        List<Hero> heroList = new ArrayList<>();
        heroList.add(genji);
        heroList.add(mercy);
        heroList.add(orisa);


        for(Hero hero : heroList) {
            System.out.println("================================");
            hero.heroInfo();
        }

    }
}
