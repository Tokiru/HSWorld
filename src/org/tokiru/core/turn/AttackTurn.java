package org.tokiru.core.turn;

/**
 * Created by tokiru.
 */
public class AttackTurn extends Turn {
    public AttackTurn(int fromID, int toID) {
        this.fromID = fromID;
        this.toID = toID;
    }

    private int fromID;

    public int getFromID() {
        return fromID;
    }

    public int getToID() {
        return toID;
    }

    private int toID;

    @Override
    public TurnType getType() {
        return TurnType.ATTACK;
    }
}
