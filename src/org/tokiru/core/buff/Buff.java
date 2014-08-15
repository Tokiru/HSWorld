package org.tokiru.core.buff;

import org.tokiru.core.board.BoardState;
import org.tokiru.core.creature.Creature;
import org.tokiru.core.event.Subscriber;

/**
 * Created by tokiru.
 */
public interface Buff extends Subscriber {
    void init(Creature creature, BoardState boardState);
    void discard();
    String getName();
    Buff getRaw();
}
