package org.tokiru.core.creature;

import org.tokiru.core.board.BoardState;
import org.tokiru.core.buff.AttackBuff;
import org.tokiru.core.buff.Buff;
import org.tokiru.core.creature.Creature;
import org.tokiru.core.event.*;
import org.tokiru.core.player.Player;

import java.util.ArrayList;
import java.util.List;

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

    protected int numberOfAttacksThisTurn;
    protected int maxNumberOfAttacks;
    private boolean firstTurn;
    private int maxHealth;

    protected BoardState boardState;
    protected EventManager eventManager;
    protected Player owner;
    protected Race race;
    protected boolean targetImmune;

    protected BuffManager buffManager;

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

        buffManager = new BuffManager(this);
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
        eventManager.send(new SummonMinionEvent(this));
    }

    protected void battleCry() {

    }

    @Override
    public void die() {
        deathRattle();
        eventManager.unsubscribe(this);
    }

    protected void deathRattle() {

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
    public boolean isTargetImmune() {
        return targetImmune;
    }

    @Override
    public void accept(Buff buff) {
        buffManager.accept(buff);
    }

    @Override
    public void changeAttack(int value) {
        attack = Math.max(0, value + attack);
    }

    @Override
    public void changeHealth(int value) {
        health = Math.max(0, value + health);
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

    private class BuffManager {
        public BuffManager(Creature creature) {
            buffList = new ArrayList<>();
            this.creature = creature;
        }

        public void accept(Buff buff) {
            buffList.add(buff);
            buff.init(creature, boardState);
            apply(buff);
        }

        private void apply(Buff buff) {
            if (buff instanceof AttackBuff) {
                AttackBuff attackBuff = (AttackBuff) buff;
                creature.changeAttack(attackBuff.attackBuff);
            } else {
                throw new UnsupportedOperationException();
            }
        }

        private List<Buff> buffList;
        private Creature creature;
    }
}
