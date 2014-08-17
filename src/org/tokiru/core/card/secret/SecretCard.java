package org.tokiru.core.card.secret;

import org.tokiru.core.board.BoardState;
import org.tokiru.core.card.Card;
import org.tokiru.core.creature.Creature;
import org.tokiru.core.player.Player;

/**
 * Created by tokiru.
 */
public interface SecretCard extends Card {
    @Override
    CardType getType();

    @Override
    boolean canPlay(Creature target, BoardState boardState);

    void play(Player owner, BoardState boardState);
}
