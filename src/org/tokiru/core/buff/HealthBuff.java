package org.tokiru.core.buff;

import org.tokiru.core.event.Event;

/**
 * Created by tokiru.
 */
public class HealthBuff extends SkeletalBuff implements Buff {
    public int healthBuff;

    public HealthBuff(int healthBuff) {
        this.healthBuff = healthBuff;
    }

    @Override
    public void discard() {

    }

    @Override
    public void accept(Event event) {

    }
}
