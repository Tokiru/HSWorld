package org.tokiru.core.creature;

import org.tokiru.core.board.BoardState;
import org.tokiru.core.buff.*;
import org.tokiru.core.event.*;
import org.tokiru.core.player.Player;

/**
 * Created by tokiru.
 */
public class SkeletalCreature implements Creature {

    protected String name;
    protected int health;
    protected int attack;
    public boolean taunt;
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

    protected int cost;

    protected BuffManager buffManager;

    public SkeletalCreature(int health, int attack, String name, int cost) {
        this(health, attack, name);
        this.cost = cost;
    }

    public SkeletalCreature(int health, int attack, String name) {
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

    public SkeletalCreature(int health, int attack) {
        this(health, attack, "Nameless minion");
    }

    public SkeletalCreature() {
        this(1, 0);
    }

    public void setWindFurry(boolean windFurry) {
        if (windFurry && !this.windFurry) {
            maxNumberOfAttacks++;
        }
        this.windFurry = windFurry;

    };

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
    public void spawn(Player owner, BoardState boardState, Creature target) {
        this.boardState = boardState;
        buffManager.setBoardState(boardState);
        this.eventManager = boardState.getEventManager();
        this.owner = owner;
        eventManager.subscribe(this, Event.EventType.END_TURN);
        battleCry();
        eventManager.send(new SummonMinionEvent(this));
    }

    protected void battleCry() {

    }

    @Override
    public void die() {
        deathRattle();
        eventManager.unsubscribe(this);
        eventManager.send(new MinionDieEvent(this));
        buffManager.discardAll(false);
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
    public boolean canTarget(Creature target, BoardState boardState) {
        return target == null;
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
        if (value > 0) {
            maxHealth = Math.max(maxHealth, health);
        } else {

        }
    }

    @Override
    public int getMaxHealth() {
        return maxHealth;
    }

    @Override
    public void setAttack(int value) {
        attack = value;
    }

    @Override
    public void setHealth(int value) {
        health = value;
    }

    @Override
    public void silence() {
        taunt = false;
        divineShield = false;
        charge = false;
        spellDamage = 0;
        windFurry = false;
        maxNumberOfAttacks = 1;
        race = Race.NONE; // ToDo check if silence actually rewoves race
        targetImmune = false; // ToDo check as well

        buffManager.discardAll(true);
    }

    @Override
    public String toString() {
        return "name = " + name + " attack = " + attack + " health = " + health;
    }

    @Override
    public void accept(Event event) {
        if (event.getType() == Event.EventType.END_TURN) {
            numberOfAttacksThisTurn = 0;
            firstTurn = false;
        } else if (event.getType() == Event.EventType.BUFF_DISCONNECT) {
            Buff buff =  ((BuffDisconnectEvent) (event)).buff;
            buffManager.discard(buff);
        }
    }

    public int getCost() {
        return cost;
    }
}
