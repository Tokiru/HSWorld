package org.tokiru.core.secret;

import org.tokiru.core.board.BoardState;
import org.tokiru.core.event.Subscriber;
import org.tokiru.core.player.Player;

/**
 * Created by tokiru.
 */
public interface Secret extends Subscriber {
    Player getOwner();
    void play(Player owner, BoardState boardState);
    void destroy();
}
