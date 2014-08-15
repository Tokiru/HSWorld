package org.tokiru.core.weapon;

import org.tokiru.core.creature.Creature;

/**
 * Created by tokiru.
 */
public class DeathsBite extends Weapon {
    public DeathsBite() {
        super(null, 4, 2, "Death's Bite");
    }

    @Override
    protected void deathRattle() {
        for (Creature creature : boardState.getAllMinions()) {
            creature.takeDamage(1);
        }
    }
}
