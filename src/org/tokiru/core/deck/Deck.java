package org.tokiru.core.deck;

import org.tokiru.core.card.Card;

import java.util.List;

/**
 * Created by tokiru.
 */
public interface Deck {
    int SIZE = 30;

    Card dealCard();

    int cardsLeft();

    List<Card> mulliganPhase1(int size);

    List<Card> mulliganPhase2(List<Boolean> acceptedCards);
}
