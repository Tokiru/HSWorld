package org.tokiru.core.turn;

/**
 * Created by tokiru.
 */
public abstract class Turn {
    protected Turn() {

    }

    public enum TurnType {
        PLAY_CARD,
        ATTACK,
        END_TURN
    }

    public abstract TurnType getType();
}
