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
        this.health -= damage;
        if (health <= 0) {
            die();
        }
    }

    @Override
    public void spawn() {

    }

    @Override
    public void die() {

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
