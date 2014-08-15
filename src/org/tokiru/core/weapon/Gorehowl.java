package org.tokiru.core.weapon;

import org.tokiru.core.hero.Hero;
import org.tokiru.core.card.creature.Creature;

/**
 * Created by tokiru.
 */
public class Gorehowl extends Weapon {
    public Gorehowl() {
        super(null, 7, 1, "Gorehowl");
    }

    @Override
    public void use(Creature target) {
        target.takeDamage(attack);
        if (target instanceof Hero) {
            durability--;
        } else {
            attack--;
        }

        if (durability == 0 || attack == 0) {
            destroy();
        }

        owner.takeDamage(target.getAttack());
    }
}
