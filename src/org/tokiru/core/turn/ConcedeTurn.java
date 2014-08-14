package org.tokiru.core.turn;

/**
 * Created by tokiru.
 */
public class ConcedeTurn extends Turn {
    @Override
    public TurnType getType() {
        return TurnType.CONCEDE;
    }
}
