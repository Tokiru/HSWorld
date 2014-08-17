package org.tokiru.core.secret;

import org.tokiru.core.board.BoardState;
import org.tokiru.core.event.Event;
import org.tokiru.core.player.Player;
import org.tokiru.core.secret.Secret;

/**
 * Created by tokiru.
 */
public abstract class SkeletonSecret implements Secret {

    protected Player owner;
    protected BoardState boardState;
    protected String name;

    public SkeletonSecret(String name) {
        this.name = name;
    }

    @Override
    public Player getOwner() {
        return owner;
    }

    @Override
    public void play(Player owner, BoardState boardState) {
        this.owner = owner;
        this.boardState = boardState;
        boardState.getSecretArray(owner.getID()).add(this);
    }

    @Override
    public void destroy() {
        boardState.getSecretArray(owner.getID()).remove(this);
        boardState.getEventManager().unsubscribe(this);
    }

    @Override
    public String toString() {
        return name;
    }
}
