package org.tokiru.core.weapon;

import org.tokiru.core.creature.Creature;

/**
 * Created by tokiru.
 */
public class GladiatorsLongbow extends Weapon {
    public GladiatorsLongbow() {
        super(null, 5, 2, "Gladiator's Longbow");
    }
    @Override
    public void use(Creature target) {
        target.takeDamage(attack);
        durability--;
        if (durability == 0) {
            destroy();
        }

        // no damage taken - hero is immune
    }
}
