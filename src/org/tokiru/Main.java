package org.tokiru;

import org.tokiru.core.Game;
import org.tokiru.core.Test;
import org.tokiru.core.card.Card;
import org.tokiru.core.card.SkeletonDeck;
import org.tokiru.core.player.SkeletonPlayer;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        Test t = new Test(5).setA(3).setB(4);
        Game game = new Game();
        SkeletonPlayer player1 = new SkeletonPlayer();
        SkeletonPlayer player2 = new SkeletonPlayer();

        game.registerPlayer(player1);
        game.registerPlayer(player2);
        game.setUp();
        game.begin();
    }

    private static void mulliganTest() {
        SkeletonDeck deck = new SkeletonDeck();
        List<Card> a = deck.mulliganPhase1(4);
        for (int i = 0; i < a.size(); i++) {
            System.out.println(a.get(i));
        }

        List<Boolean> b = new ArrayList<>();
        b.add(true);
        b.add(false);
        b.add(false);
        b.add(true);
        List<Card> c = deck.mulliganPhase2(b);

        for (int i = 0; i < a.size(); i++) {
            System.out.println(a.get(i));
        }

        System.out.println("=======");
        while (deck.cardsLeft() > 0) {
            System.out.println(deck.dealCard());
        }
    }
}
