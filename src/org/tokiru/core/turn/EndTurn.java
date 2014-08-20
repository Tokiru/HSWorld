package org.tokiru.core.turn;

import org.tokiru.core.player.Player;

/**
 * Created by tokiru.
 */
public class EndTurn extends Turn {
    public Player player;
    public EndTurn(Player player) {
        this.player = player;
    }

    @Override
    public TurnType getType() {
        return TurnType.END_TURN;
    }
}
