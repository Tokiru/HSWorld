package org.tokiru.core.player;

import org.tokiru.core.board.BoardState;
import org.tokiru.core.card.Card;
import org.tokiru.core.card.Deck;
import org.tokiru.core.hero.Hero;
import org.tokiru.core.turn.Turn;

import java.util.List;

/**
 * Created by tokiru.
 */
public interface Player {
    Hero getHero();

    Deck getDeck();

    List<Boolean> mulligan(List<Card> cards);

    Turn turn(BoardState state);

    String getName();

    void setID(int id);

    int getID();
}
