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

        turnCount = 0;
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

    public void removeCreature(Creature creature) {
        int id1 = playerStates.get(0).getID(creature);
        int id2 = playerStates.get(1).getID(creature);
        assert id1 == -1 ^ id2 == -1;
        if (id1 == -1) {
            playerStates.get(1).removeCreature(creature);
        } else {
            playerStates.get(0).removeCreature(creature);
        }
    }

    public void removeCreature(int creatureID) {
        for (PlayerState playerState : playerStates) {
            playerState.removeCreature(playerState.getByID(creatureID));
        }
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
        for (PlayerState playerState : playerStates) {
            if (!playerState.isAlive()) {
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

    public void endTurn() {
        turnCount++;
    }

    public int getTurnCount() {
        return turnCount;
    }

    public List<Creature> getFriendlyCharacters(int playerID) {
        return playerStates.get(playerID).getCharacters();
    }

    public List<Creature> getFriendlyMinions(int playerID) {
        return playerStates.get(playerID).getMinions();
    }

    public List<Creature> getAllMinions() {
        List<Creature> result = getFriendlyMinions(0);
        result.addAll(getFriendlyMinions(1));
        return result;
    }

    public List<Creature> getAllCharacters() {
        List<Creature> result = getFriendlyCharacters(0);
        result.addAll(getFriendlyCharacters(1));
        return result;
    }

    @Override
    public String toString() {
        return "Board state\n" + playerStates.get(0).toString() + playerStates.get(1).toString();
    }

    private List<PlayerState> playerStates;
    private int MAGIC = 1000;
    private int turnCount;
}
