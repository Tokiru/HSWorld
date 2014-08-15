package org.tokiru.core.buff;

import org.tokiru.core.board.BoardState;
import org.tokiru.core.creature.Creature;
import org.tokiru.core.event.Event;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tokiru.
 */
public class CombinationBuff extends SkeletonBuff implements Buff {
    private boolean initialized;

    public CombinationBuff() {
        initialized = false;
        buffList = new ArrayList<>();
    }

    public CombinationBuff add(Buff buff) {
        if (initialized) {
            throw new UnsupportedOperationException();
        }
        buffList.add(buff);
        return this;
    }

    public List<Buff> buffList;

    @Override
    public void discard() {

    }

    @Override
    public void accept(Event event) {

    }

    @Override
    public void init(Creature creature, BoardState boardState) {
        initialized = true;
        super.init(creature, boardState);
    }
}
