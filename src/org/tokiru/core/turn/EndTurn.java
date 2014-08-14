package org.tokiru.core.turn;

/**
 * Created by tokiru.
 */
public class EndTurn extends Turn {
    public EndTurn() {

    }

    @Override
    public TurnType getType() {
        return TurnType.END_TURN;
    }
}
