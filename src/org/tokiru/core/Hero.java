package org.tokiru.core;

import org.tokiru.core.board.BoardState;
import org.tokiru.core.card.creature.Creature;
import org.tokiru.core.event.Event;
import org.tokiru.core.event.EventManager;
import org.tokiru.core.event.Subscriber;
import org.tokiru.core.player.Player;

/**
 * Created by tokiru.
 */
public class Hero implements Creature, Subscriber {

    private HeroClass heroClass;
    private int health;
    private BoardState boardState;
    private EventManager eventManager;
    private Player owner;

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
    public boolean isTaunt() {
        return false;
    }

    @Override
    public boolean canAttack(Creature target) {
        return false;
    }

    @Override
    public int getSpellDamage() {
        return 0;
    }

    @Override
    public void destroy() {
        health = 0;
        die();
    }

    @Override
    public Player getOwner() {
        return owner;
    }

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
    public void spawn(Player owner, BoardState boardState, EventManager eventManager) {
        this.boardState = boardState;
        this.eventManager = eventManager;
        this.owner = owner;
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

    @Override
    public void accept(Event event) {

    }
}
