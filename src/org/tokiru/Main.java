package org.tokiru;

import org.tokiru.core.Game;
import org.tokiru.core.card.Card;
import org.tokiru.core.deck.SkeletalDeck;
import org.tokiru.core.hero.Druid;
import org.tokiru.core.hero.Mage;
import org.tokiru.core.hero.Paladin;
import org.tokiru.core.player.SkeletalPlayer;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        Game game = new Game();
        SkeletalPlayer player1 = new SkeletalPlayer(new Druid());
        SkeletalPlayer player2 = new SkeletalPlayer(new Paladin());

        game.registerPlayer(player1);
        game.registerPlayer(player2);
        game.setUp();
        game.begin();
    }

    private static void mulliganTest() {
        SkeletalDeck deck = new SkeletalDeck();
        List<Card> a = deck.mulliganPhase1(4);
        for (Card card : a) {
            System.out.println(card);
        }

        List<Boolean> b = new ArrayList<>();
        b.add(true);
        b.add(false);
        b.add(false);
        b.add(true);
        List<Card> c = deck.mulliganPhase2(b);

        for (Card card : c) {
            System.out.println(card);
        }

        System.out.println("=======");
        while (deck.cardsLeft() > 0) {
            System.out.println(deck.dealCard());
        }
    }
}
