package org.tokiru.core.player;

import org.tokiru.core.board.BoardState;
import org.tokiru.core.card.Card;
import org.tokiru.core.card.Deck;
import org.tokiru.core.card.SkeletonDeck;
import org.tokiru.core.hero.Hero;
import org.tokiru.core.hero.HeroClass;
import org.tokiru.core.hero.Hunter;
import org.tokiru.core.turn.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by tokiru.
 */
public class SkeletonPlayer implements Player {
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private final Hero hero;
    private final Deck deck;
    private String name;
    private int id;

    public SkeletonPlayer(HeroClass heroClass) {
        this.deck = new SkeletonDeck();
        this.hero = new Hero(heroClass);
    }

    public SkeletonPlayer() {
        this(new Hunter());
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
            } else if (type == -1) {
                return new ConcedeTurn();
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
        return "Poligraph";
    }

    @Override
    public void setID(int id) {
        this.id = id;
    }

    @Override
    public int getID() {
        return id;
    }
}
