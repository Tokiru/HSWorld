package org.tokiru.core;

import org.tokiru.core.Board.BoardState;
import org.tokiru.core.card.creature.Creature;
import org.tokiru.core.event.Event;

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
    public void acceptEvent(Event event) {

    }

    @Override
    public boolean isTaunt() {
        return false;
    }

    @Override
    public boolean canAttack(Creature target) {
        return false;
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
        if (health <= 0) {
            die();
        }
    }

    @Override
    public void spawn(BoardState boardState) {
        this.boardState = boardState;
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

    private BoardState boardState;
}
