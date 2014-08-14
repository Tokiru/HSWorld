package org.tokiru.core.Board;

import org.tokiru.core.card.creature.Creature;
import org.tokiru.core.Hero;
import org.tokiru.core.card.Deck;
import org.tokiru.core.card.Hand;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tokiru.
 */
public class PlayerState {
    public PlayerState() {
        creatures = new ArrayList<>();
        fartigue = 0;
    }

    public static final int HERO_ID = 100;

    public void setHero(Hero hero) {
        this.hero = hero;
    }

    public void setDeck(Deck deck) {
        this.deck = deck;
    }

    public boolean isAlive() {
        return hero.isAlive();
    }

    public void setHand(Hand hand) {
        this.hand = hand;
    }

    public Hero getHero() {
        return hero;
    }

    public Deck getDeck() {
        return deck;
    }

    public Hand getHand() {
        return hand;
    }

    public void addCreature(Creature creature, int index) {
        creatures.add(index, creature);
    }

    public void addCreature(Creature creature) {
        creatures.add(creature);
    }

    public Creature getByID(int id) {
        if (id == HERO_ID) {
            return hero;
        } else {
            return creatures.get(id);
        }
    }

    public int getID(Creature creature) {
        if (creature == hero) {
            return HERO_ID;
        } else {
            for (int i = 0; i < creatures.size(); i++) {
                if (creatures.get(i) == creature) {
                    return i;
                }
            }
        }

        return -1;
    }

    public void dealFartigueDamage() {
        hero.takeDamage(++fartigue);
    }

    public int getManaCrystals() {
        return manaCrystals;
    }

    public void addManaCrystal(int value) {
        manaCrystals+= value;
    }

    private Hero hero;
    private Deck deck;
    private Hand hand;
    private List<Creature> creatures;
    private int fartigue;
    private int manaCrystals;

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("PlayerState\n");
        sb.append(hero.toString());
        sb.append("\n");
        sb.append("board\n");
        sb.append("{");
        for (int i = 0; i < creatures.size(); i++) {
            sb.append("[");
            sb.append(creatures.get(i).toString());
            sb.append("]");
            sb.append("\n");
        }

        return sb.toString();
    }
}
