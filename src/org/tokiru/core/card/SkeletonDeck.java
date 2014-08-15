package org.tokiru.core.card;

import org.tokiru.core.card.creature.MinionBuilder;
import org.tokiru.core.card.creature.MinionCard;
import org.tokiru.core.card.spell.mage.Flamestrike;
import org.tokiru.core.card.spell.weapon.WeaponFactory;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by tokiru.
 */
public class SkeletonDeck implements Deck {
    private List<Card> cards;
    private int cardCounter;
    private List<Card> mulliganPhase1Cards;

    public SkeletonDeck() {
        cards = new ArrayList<>();
        cardCounter = 0;

        cards.add(WeaponFactory.arcaniteRapierCard());
        cards.add(new MinionBuilder().setAttack(2).setHealth(1).charge().setName("charger").setCost(2).getCard());
        cards.add(new MinionBuilder().setAttack(0).setHealth(10).taunt().setName("taunt").setCost(3).getCard());

        cards.add(new Flamestrike());

        while (cards.size() < SIZE) {
            cards.add(new MinionCard((int) (10 * Math.random()), (int) (10 * Math.random()), (int) (10 * Math.random())));
        }
    }

    @Override
    public Card dealCard() {
        if (cardCounter < cards.size()) {
            return cards.get(cardCounter++);
        } else {
            return null;
        }
    }

    @Override
    public int cardsLeft() {
        return cards.size() - cardCounter;
    }

    @Override
    public List<Card> mulliganPhase1(int size) {
        mulliganPhase1Cards = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            mulliganPhase1Cards.add(cards.get(i));
        }

        return mulliganPhase1Cards;
    }

    @Override
    public List<Card> mulliganPhase2(List<Boolean> acceptedCards) {
        assert acceptedCards.size() == mulliganPhase1Cards.size();
        int changedCards = 0;
        List<Card> newCards = new ArrayList<>();
        for (int i = 0; i < acceptedCards.size(); i++) {
            if (!acceptedCards.get(i)) {
                changedCards++;
                newCards.add(mulliganPhase1Cards.get(i));
                mulliganPhase1Cards.set(i, cards.get(acceptedCards.size() + changedCards - 1));
            }
        }

        for (int i = acceptedCards.size() + changedCards; i < cards.size(); i++) {
            newCards.add(cards.get(i));
        }

        cards = newCards;
        Collections.shuffle(cards);
        return mulliganPhase1Cards;
    }
}
