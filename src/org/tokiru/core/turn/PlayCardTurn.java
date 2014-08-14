package org.tokiru.core.turn;

/**
 * Created by tokiru.
 */
public class PlayCardTurn extends Turn {
    private int cardID;
    private int targetID;

    public PlayCardTurn(int cardID, int targetID) {
        this.cardID = cardID;
        this.targetID = targetID;
    }

    public int getCardID() {
        return cardID;
    }

    public int getTargetID() {
        return targetID;
    }

    @Override
    public TurnType getType() {
        return TurnType.PLAY_CARD;
    }
}
