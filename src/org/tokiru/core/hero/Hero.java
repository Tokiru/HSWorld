package org.tokiru.core.hero;

import org.tokiru.core.card.creature.Creature;
import org.tokiru.core.card.creature.SkeletonCreature;
import org.tokiru.core.event.Event;
import org.tokiru.core.event.Subscriber;
import org.tokiru.core.weapon.Weapon;

/**
 * Created by tokiru.
 */
public class Hero extends SkeletonCreature implements Creature, Subscriber {

    private HeroClass heroClass;
    private Weapon weapon;
    private int armor;

    public Hero(HeroClass heroClass) {
        super(30, 0, heroClass.getName());
        this.heroClass = heroClass;
        charge = true;
        armor = 0;
    }

    @Override
    public void hit(Creature creature) {
        assert weapon.getAttack() > 0;
        weapon.use(creature);
        numberOfAttacksThisTurn++;
    }

    public void setWeapon(Weapon weapon) {
        if (this.weapon != null) {
            this.weapon.destroy();
        }
        this.weapon = weapon;
        maxNumberOfAttacks = weapon.maxNumberOfAttacks();
    }

    @Override
    public boolean canAttack(Creature target) {
        //noinspection StatementWithEmptyBody
        if (weapon != null && weapon.getAttack() > 0) {

        } else {
            return false;
        }
        return super.canAttack(target);
    }

    @Override
    public void accept(Event event) {
        super.accept(event);

        if (event.getType() == Event.EventType.WEAPON_DESTROY) {
            this.weapon = null;
        }
    }
    public void addArmor(int value) {
        armor = Math.min(0, armor + value);
    }

    public int getArmor() {
        return armor;
    }

    public void takeDamage(int damage, boolean ignoreArmor) {
        if (ignoreArmor) {
            super.takeDamage(damage);
        } else {
            if (damage > armor) {
                damage -= armor;
                armor = 0;
                super.takeDamage(damage);
            } else {
                armor -= damage;
            }
        }
    }

    @Override
    public void takeDamage(int damage) {
        takeDamage(damage, false);
    }

    @Override
    public String toString() {
        String s;
        if (weapon != null) {
            s = weapon.toString();
        } else {
            s = "none";
        }
        return getName() + " health = " + health + " armor = " + armor + " weapon = " + s;
    }
}
