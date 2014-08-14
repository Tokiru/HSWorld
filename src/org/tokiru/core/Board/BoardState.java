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
public class BoardState {
    public BoardState() {
        playerStates = new ArrayList<>();
        for (int i = 0; i < 2;  i++) {
            playerStates.add(new PlayerState());
        }
    }

    public void setHero(Hero hero, int playerID) {
        playerStates.get(playerID).setHero(hero);
    }
    public Hero getHero(int playerID) {
        return playerStates.get(playerID).getHero();
    }

    public void setDeck(Deck deck, int playerID) {
        playerStates.get(playerID).setDeck(deck);
    }
    public Deck getDeck(int playerID) {
        return playerStates.get(playerID).getDeck();
    }

    public void setHand(Hand hand, int playerID) {
        playerStates.get(playerID).setHand(hand);
    }
    public Hand getHand(int playerID) {
        return playerStates.get(playerID).getHand();
    }

    public void addCreature(Creature creature, int index, int playerID) {
        playerStates.get(playerID).addCreature(creature, index);
    }

    public void addCreature(Creature creature, int playerID) {
        playerStates.get(playerID).addCreature(creature);
    }

    public void dealFartigueDamage(int playerID) {
        playerStates.get(playerID).dealFartigueDamage();
    }

    public void addManaCrystal(int playerID) {
        playerStates.get(playerID).addManaCrystal(1);
    }
    public void addManaCrystal(int value, int playerID) {
        playerStates.get(playerID).addManaCrystal(value);
    }

    public int getManaCrystals(int playerID) {
        return playerStates.get(playerID).getManaCrystals();
    }

    public boolean gameOver() {
        for (int i = 0;  i < playerStates.size(); i++) {
            if (!playerStates.get(i).isAlive()) {
                return true;
            }
        }

        return false;
    }

    public Creature getByID(int id) {
        if (id < MAGIC) {
           return playerStates.get(0).getByID(id);
        } else {
            return playerStates.get(1).getByID(id - MAGIC);
        }
    }

    public int getID(Creature creature) {
        int id1 = playerStates.get(0).getID(creature);
        int id2 = playerStates.get(1).getID(creature);
        assert id1 == -1 || id2 == -2;
        if (id2 == -1) {
            return id1;
        } else {
            return id2 + MAGIC;
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Board state\n");
        sb.append(playerStates.get(0).toString());
        sb.append(playerStates.get(1).toString());
        return sb.toString();
    }

    private List<PlayerState> playerStates;
    private int MAGIC = 1000;
}
