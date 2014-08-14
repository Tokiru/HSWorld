package org.tokiru.core.player;

import org.tokiru.core.Board.BoardState;
import org.tokiru.core.Hero;
import org.tokiru.core.HeroClass;
import org.tokiru.core.card.Card;
import org.tokiru.core.card.Deck;
import org.tokiru.core.card.SkeletonDeck;
import org.tokiru.core.turn.AttackTurn;
import org.tokiru.core.turn.EndTurn;
import org.tokiru.core.turn.PlayCardTurn;
import org.tokiru.core.turn.Turn;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.channels.Channels;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by tokiru.
 */
public class SkeletonPlayer implements Player {
    public SkeletonPlayer() {
        this("Unnamed");
    }

    public SkeletonPlayer(String name) {
        this.deck = new SkeletonDeck();
        this.hero = new Hero(HeroClass.HUNTER);
        this.name = name;
    }

    @Override
    public Hero getHero() {
        return hero;
    }

    @Override
    public Deck getDeck() {
        return deck;
    }
    @Override
    public List<Boolean> mulligan(List<Card> cards) {
        List<Boolean> result = cards.stream().map(card -> true).collect(Collectors.toList());
        return result;
    }

    @Override
    public Turn turn(BoardState state) {
        System.out.print("turn pls: ");
//        try {
//            int turnType = System.in.read();
//            Turn result = null;
//            switch (turnType) {
//                case '1':
//                    int a = System.in.read() - '1';
//                    int b = System.in.read() - '1';
//                    result = new AttackTurn(a, b);
//                    break;
//
//                case '2':
//                    System.in.read();
//                    a = System.in.read() - '1';
//                    System.in.read();
//                    b = System.in.read() - '1';
//                    result = new PlayCardTurn(a, b);
//                    break;
//            }
//
//            if (result != null) {
//                return result;
//            } else {
//                return new EndTurn();
//            }
//        } catch (IOException e) {
//            return new EndTurn();
//        }

        try {
            String s = reader.readLine();
            String[] parts = s.split(" ");
            int type = new Integer(parts[0]);
            int a = new Integer(parts[1]);
            int b = new Integer(parts[2]);

            if (type == 1) {
                return new PlayCardTurn(a, b);
            } else if (type == 2) {
                return new AttackTurn(a, b);
            } else {
                return new EndTurn();
            }
        } catch (IOException e) {
            e.printStackTrace();
            return new EndTurn();
        }
    }

    @Override
    public String getName() {
        return name;
    }

    private final Hero hero;
    private final Deck deck;
    private String name;
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
}
