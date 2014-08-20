package org.tokiru.core.turn;

/**
 * Created by tokiru.
 */
public class PlayCardTurn extends Turn {
    private final int position;
    private int cardID;
    private int targetID;

    public PlayCardTurn(int cardID, int targetID, int position) {
        this.cardID = cardID;
        this.targetID = targetID;
        this.position = position;
    }

    public int getCardID() {
        return cardID;
    }

    public int getTargetID() {
        return targetID;
    }

    public int getPosition() {
        return position;
    }

    @Override
    public TurnType getType() {
        return TurnType.PLAY_CARD;
    }
}
