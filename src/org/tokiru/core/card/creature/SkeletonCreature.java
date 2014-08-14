package org.tokiru.core.card.creature;

import org.tokiru.core.board.BoardState;
import org.tokiru.core.event.CharacterHealEvent;
import org.tokiru.core.event.Event;
import org.tokiru.core.event.EventManager;
import org.tokiru.core.event.Subscriber;
import org.tokiru.core.player.Player;

/**
 * Created by tokiru.
 */
public class SkeletonCreature implements Creature, Subscriber {

    protected String name;
    protected int health;
    protected int attack;
    protected boolean taunt;
    protected boolean divineShield;
    protected boolean charge;
    protected int spellDamage;
    protected boolean windFurry;

    private int numberOfAttacksThisTurn;
    private int maxNumberOfAttacks;
    private boolean firstTurn;
    private int maxHealth;

    protected BoardState boardState;
    protected EventManager eventManager;
    protected Player owner;
    protected Race race;

    public SkeletonCreature(int health, int attack, String name) {
        this.health = health;
        this.maxHealth = health;
        this.attack = attack;
        this.name = name;
        this.race = Race.NONE;

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
    public void takeHeal(int heal) {
        int oldHealth = health;
        this.health = Math.min(health + heal, maxHealth);
        eventManager.send(new CharacterHealEvent(this, health - oldHealth));
    }

    @Override
    public void spawn(Player owner, BoardState boardState, EventManager eventManager) {
        this.boardState = boardState;
        this.eventManager = eventManager;
        this.owner = owner;
        eventManager.subscribe(this, Event.EventType.END_TURN);
    }

    @Override
    public void die() {
        System.out.println(toString() + " deathrattle");
        eventManager.unsubscribe(this);
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
    public int getSpellDamage() {
        return spellDamage;
    }

    @Override
    public void destroy() {
        this.health = 0;
        die();
    }

    @Override
    public Player getOwner() {
        return owner;
    }

    @Override
    public Race getRace() {
        return race;
    }

    @Override
    public String toString() {
        return "name = " + name + " health = " + health + " attack = " + attack;
    }

    @Override
    public void accept(Event event) {
        if (event.getType() == Event.EventType.END_TURN) {
            numberOfAttacksThisTurn = 0;
            firstTurn = false;
        }
    }
}
