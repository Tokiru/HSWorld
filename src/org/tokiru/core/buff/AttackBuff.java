package org.tokiru.core.buff;

import org.tokiru.core.board.BoardState;
import org.tokiru.core.creature.Creature;
import org.tokiru.core.creature.SkeletonCreature;
import org.tokiru.core.event.Event;

/**
 * Created by tokiru.
 */
public class AttackBuff extends SkeletonBuff implements Buff {
    public int attackBuff;

    public AttackBuff(int attackBuff) {
        this.attackBuff = attackBuff;
    }

    @Override
    public void init(Creature creature, BoardState boardState) {
        super.init(creature, boardState);
    }

    @Override
    public void discard() {

    }

    @Override
    public void accept(Event event) {

    }
}
