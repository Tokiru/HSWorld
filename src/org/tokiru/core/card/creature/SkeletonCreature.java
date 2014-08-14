package org.tokiru.core.card.creature;

import org.tokiru.core.Board.BoardState;
import org.tokiru.core.event.Event;

/**
 * Created by tokiru.
 */
public class SkeletonCreature implements Creature {

    public SkeletonCreature(int health, int attack, String name) {
        this.health = health;
        this.attack = attack;
        this.name = name;

        firstTurn = true;
        maxNumberOfAttacks = 1;
        if (windFurry) {
            maxNumberOfAttacks++;
        }
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

        if (health <= 0) {
            die();
            boardState.removeCreature(this);
        }
    }

    @Override
    public void spawn(BoardState boardState) {
        this.boardState = boardState;
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
        numberOfAttacksThisTurn++;
    }

    @Override
    public void acceptEvent(Event event) {
        if (event.getType() == Event.EventType.END_TURN) {
            numberOfAttacksThisTurn = 0;
            firstTurn = false;
        }
    }

    @Override
    public boolean isTaunt() {
        return taunt;
    }

    @Override
    public boolean canAttack(Creature target) {
        if (firstTurn && !charge) {
            return false;
        } else {
            if (numberOfAttacksThisTurn >= maxNumberOfAttacks) {
                return false;
            } else {
                if (target.isTaunt()) {
                    return true;
                } else {
                    boolean result = true;
                    for (Creature creature : boardState.getEnemyMinions(boardState.getPlayerID(this))) {
                        if (creature.isTaunt()) {
                            result = false;
                            break;
                        }
                    }

                    return result;
                }
            }
        }
    }

    @Override
    public String toString() {
        return "name = " + name + " health = " + health + " attack = " + attack;
    }

    String name;
    int health;
    int attack;
    boolean taunt;
    boolean divineShield;
    boolean charge;
    int spellDamage;
    boolean windFurry;


    private int numberOfAttacksThisTurn;
    private int maxNumberOfAttacks;
    private boolean firstTurn;

    private BoardState boardState;
}
