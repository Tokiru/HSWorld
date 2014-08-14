package org.tokiru.core.card.creature;

import org.tokiru.core.card.creature.Creature;

/**
 * Created by tokiru.
 */
public class SkeletonCreature implements Creature {

    public SkeletonCreature(int health, int attack, String name) {
        this.health = health;
        this.attack = attack;
        this.name = name;
    }

    public SkeletonCreature(int health, int attack) {
        this(health, attack, "Nameless minion");
    }

    public SkeletonCreature() {
        this(1, 0);
    }

    @Override
    public int getHealth() {
        return health;
    }

    @Override
    public int getAttack() {
        return attack;
    }

    @Override
    public void takeDamage(int damage) {
        if (damage > 0) {
            if (divineShield) {
                divineShield = false;
            } else {
                this.health -= damage;
            }
        }
    }

    @Override
    public void spawn() {

    }

    @Override
    public void die() {
        System.out.println(toString() + " deathrattle");
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public boolean isAlive() {
        return health > 0;
    }

    @Override
    public void hit(Creature creature) {
        creature.takeDamage(attack);
    }

    @Override
    public void acceptEvent() {

    }

    @Override
    public String toString() {
        return "name = " + name + " health = " + health + " attack = " + attack;
    }

    String name;
    int health;
    int attack;
    int cost;
    boolean taunt;
    boolean divineShield;
    boolean charge;
    int spellDamage;
    boolean windFurry;
}
