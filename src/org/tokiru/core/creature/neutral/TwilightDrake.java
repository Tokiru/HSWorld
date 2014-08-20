package org.tokiru.core.creature.neutral;

import org.tokiru.core.buff.HealthBuff;
import org.tokiru.core.creature.Creature;
import org.tokiru.core.creature.SkeletalCreature;

/**
 * Created by tokiru.
 */
public class TwilightDrake extends SkeletalCreature implements Creature {
    public TwilightDrake() {
        super(1, 4, "Twilight Drake", 4);
        race = Race.DRAGON;
    }

    @Override
    protected void battleCry() {
        this.accept(new HealthBuff(boardState.getHand(owner.getID()).getSize()));
    }
}
