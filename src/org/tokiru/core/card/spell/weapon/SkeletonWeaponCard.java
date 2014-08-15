package org.tokiru.core.card.spell.weapon;

import org.tokiru.core.hero.Hero;
import org.tokiru.core.board.BoardState;
import org.tokiru.core.card.creature.Creature;
import org.tokiru.core.event.EventManager;
import org.tokiru.core.weapon.Weapon;

/**
 * Created by tokiru.
 */
public class SkeletonWeaponCard implements WeaponCard {
    private final Weapon weapon;
    private int cost;

    public SkeletonWeaponCard(Weapon weapon, int cost) {
        this.weapon = weapon;
        this.cost = cost;
    }

    @Override
    public void play(Hero owner, BoardState boardState, EventManager eventManager) {
        weapon.setEnvironment(owner, boardState, eventManager);
        owner.setWeapon(weapon);
    }

    @Override
    public int getCost() {
        return cost;
    }

    @Override
    public void changeCost(int value) {
        cost = Math.min(0, cost + value);
    }

    @Override
    public String getName() {
        return "Card " + weapon.getName();
    }

    @Override
    public CardType getType() {
        return CardType.WEAPON;
    }

    @Override
    public boolean canPlay(Creature target, BoardState boardState) {
        return target == null;
    }

    @Override
    public String toString() {
        return "card weapon = " + weapon.toString();
    }
}
