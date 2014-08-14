package org.tokiru.core.card;

import java.util.List;

/**
 * Created by tokiru.
 */
public interface Deck {
    Card dealCard();
    int cardsLeft();
    int SIZE = 30;
    List<Card> mulliganPhase1(int size);
    List<Card> mulliganPhase2(List<Boolean> acceptedCards);
}
