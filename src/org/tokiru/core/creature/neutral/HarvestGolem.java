package org.tokiru.core.creature.neutral;

import org.tokiru.core.creature.Creature;
import org.tokiru.core.creature.MinionBuilder;
import org.tokiru.core.creature.SkeletalCreature;

/**
 * Created by tokiru.
 */
public class HarvestGolem extends SkeletalCreature implements Creature {
    public HarvestGolem() {
        super(3, 2, "Harvest Golem");
    }

    @Override
    protected void deathRattle() {
        Creature damagedGolem = new MinionBuilder("Damaged golem", 2, 1).getCreature();
        boardState.addCreatureRight(damagedGolem, this, null, owner.getID());
    }
}
