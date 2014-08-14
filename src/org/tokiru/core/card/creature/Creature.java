package org.tokiru.core.card.creature;

/**
 * Created by tokiru.
 */
public interface Creature {
    int getHealth();
    int getAttack();
    void takeDamage(int damage);
    void spawn();
    void die();
    String getName();
    boolean isAlive();
}
