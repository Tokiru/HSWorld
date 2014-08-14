package org.tokiru.core;

import org.tokiru.core.card.creature.Creature;

/**
 * Created by tokiru.
 */
public class Hero implements Creature {

    public Hero(HeroClass heroClass) {
        this.heroClass = heroClass;
        health = 30;
    }

    public boolean isAlive() {
        return health > 0;
    }

    @Override
    public void hit(Creature creature) {

    }

    @Override
    public void acceptEvent() {

    }

    private HeroClass heroClass;
    private int health;

    @Override
    public int getHealth() {
        return health;
    }

    @Override
    public int getAttack() {
        return 0;
    }

    @Override
    public void takeDamage(int damage) {
        health -= damage;
    }

    @Override
    public void spawn() {

    }

    @Override
    public void die() {
        System.out.println("AARRRRRRRRRRRRRRGHHHHHhhhhhh");
    }

    @Override
    public String getName() {
        return "Rexxar";
    }

    @Override
    public String toString() {
        return getName() + " " + health;
    }
}
