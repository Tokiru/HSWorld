package org.tokiru.core.turn;

/**
 * Created by tokiru.
 */
public class AttackTurn extends Turn {
    private int fromID;
    private int toID;

    public AttackTurn(int fromID, int toID) {
        this.fromID = fromID;
        this.toID = toID;
    }

    public int getFromID() {
        return fromID;
    }

    public int getToID() {
        return toID;
    }

    @Override
    public TurnType getType() {
        return TurnType.ATTACK;
    }
}
