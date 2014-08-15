package org.tokiru.core.buff;

import org.tokiru.core.board.BoardState;
import org.tokiru.core.creature.Creature;

/**
 * Created by tokiru.
 */
public abstract class SkeletonBuff implements Buff {
    @Override
    public void init(Creature creature, BoardState boardState) {
        this.creature = creature;
        this.boardState = boardState;
    }

    @Override
    public String getName() {
        return "Buff";
    }

    protected Creature creature;
    protected BoardState boardState;
}
