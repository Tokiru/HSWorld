package org.tokiru.core.card;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by tokiru.
 */
public class Hand {
    public Hand() {
        cards = new ArrayList<>();
    }

    public Hand(List<Card> cards) {
        this();
        this.cards.addAll(cards.stream().collect(Collectors.toList()));
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

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        for (Card card : cards) {
            sb.append("{");
            sb.append(card);
            sb.append("}");
        }
        return sb.toString();
    }

    private static final int MAX_SIZE = 10;
    private List<Card> cards;
}
