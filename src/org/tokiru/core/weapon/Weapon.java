package org.tokiru.core.weapon;

import org.tokiru.core.Hero;
import org.tokiru.core.board.BoardState;
import org.tokiru.core.card.creature.Creature;
import org.tokiru.core.event.EventManager;
import org.tokiru.core.event.WeaponDestroyEvent;

/**
 * Created by tokiru.
 */
public class Weapon {
    protected Hero owner;
    protected int durability;
    protected int attack;
    protected String name;
    protected BoardState boardState;
    protected EventManager eventManager;

    public int getDurability() {
        return durability;
    }

    public int getAttack() {
        return attack;
    }

    public Weapon(Hero owner, int attack, int durability, String name) {
        this.owner = owner;
        this.attack = attack;
        this.durability = durability;
        this.name = name;
    }

    public Weapon(Hero owner) {
        this(owner, 1, 1, "Basic axe");
    }
    public Weapon() {
        this(null);
    }

    public void setEnvironment(Hero owner, BoardState boardState, EventManager eventManager) {
        this.owner = owner;
        this.boardState = boardState;
        this.eventManager = eventManager;
    }

    public void use(Creature target) {
        target.takeDamage(attack);
        durability--;
        if (durability == 0) {
            destroy();
        }

        owner.takeDamage(target.getAttack());
    }

    public void changeAttack(int value) {
        attack = Math.min(0, attack + value);
    }

    public void changeDurability(int value) {
        durability = Math.min(0, durability + value);
    }

    public void destroy() {
        deathRattle();
        durability = 0;
        owner.accept(new WeaponDestroyEvent(this));
    }

    public String getName() {
        return name;
    }

    protected void deathRattle() {

    }

    public int maxNumberOfAttacks() {
        return 1;
    }

    @Override
    public String toString() {
        return getName() + " attack = " + attack + " durability = " + durability;
    }
}
