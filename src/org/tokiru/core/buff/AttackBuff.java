package org.tokiru.core.buff;

import org.tokiru.core.event.Event;

/**
 * Created by tokiru.
 */
public class AttackBuff extends SkeletalBuff implements Buff {
    public int attackBuff;

    public AttackBuff(int attackBuff) {
        this.attackBuff = attackBuff;
    }

    @Override
    public void discard() {

    }

    @Override
    public void accept(Event event) {

    }
}
