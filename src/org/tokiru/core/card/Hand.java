package org.tokiru.core.card;

import org.tokiru.core.board.BoardState;
import org.tokiru.core.creature.Creature;
import org.tokiru.core.event.Event;
import org.tokiru.core.event.Subscriber;
import org.tokiru.core.hero.HeroClass;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by tokiru.
 */
public class Hand implements Subscriber {
    private static final int MAX_SIZE = 10;
    public static final int ABILITY_CARD_ID = 42;
    private List<Card> cards;
    private Card abilityCard;
    private HeroClass heroClass;

    public Hand(HeroClass heroClass) {
        this.heroClass = heroClass;
        cards = new ArrayList<>();
        abilityCard = heroClass.getAbilityCard();
    }

    public Hand(List<Card> cards, HeroClass heroClass) {
        this(heroClass);
        this.cards.addAll(cards.stream().collect(Collectors.toList()));
    }

    public Card play(int cardIndex, Creature target, BoardState boardState) {
        Card result;
        if (cardIndex == ABILITY_CARD_ID) {
            result = abilityCard;
        } else {
            result = cards.get(cardIndex);
        }

        if (!result.canPlay(target, boardState)) {
            return null;
        }

        if (cardIndex == ABILITY_CARD_ID) {
            abilityCard = null;
            return result;
        } else {
            cards.remove(cardIndex);
            return result;
        }

    }

    public void accept(Card card) {
        if (cards.size() < MAX_SIZE) {
            cards.add(card);
        }
    }

    public boolean contains(Card card) {
        return cards.contains(card);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        for (Card card : cards) {
            sb.append("{");
            sb.append(card);
            sb.append("}");
        }

        if (abilityCard != null) {
            sb.append("{");
            sb.append(abilityCard);
            sb.append("}");
        }

        return sb.toString();
    }

    @Override
    public void accept(Event event) {
        if (event.getType() == Event.EventType.END_TURN) {
            abilityCard = heroClass.getAbilityCard();
        }
    }
}
