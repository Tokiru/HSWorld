package org.tokiru.core.card;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tokiru.
 */
public class Hand {
    public Hand() {
        cards = new ArrayList<>();
    }

    public Hand(List<Card> cards) {
        this();
        for (Card card : cards) {
            this.cards.add(card);
        }
    }

    public Card play(int cardIndex) {
        Card result = cards.get(cardIndex);
        cards.remove(cardIndex);
        return result;
    }

    public void accept(Card card) {
        if (cards.size() < MAX_SIZE) {
            cards.add(card);
        }
    }

    private static final int MAX_SIZE = 10;
    private List<Card> cards;
}
