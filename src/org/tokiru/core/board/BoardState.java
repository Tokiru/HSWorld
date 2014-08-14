package org.tokiru.core.board;

import org.tokiru.core.Hero;
import org.tokiru.core.card.Deck;
import org.tokiru.core.card.Hand;
import org.tokiru.core.card.creature.Creature;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tokiru.
 */
public class BoardState {
    private List<PlayerState> playerStates;
    private int MAGIC = 1000;
    private int turnCount;

    public BoardState() {
        playerStates = new ArrayList<>();
        for (int i = 0; i < 2; i++) {
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

    public int getSpellDamage(int playerID) {
        return playerStates.get(playerID).getSpellDamage();
    }

    public void refreshMana(int playerID) {
        playerStates.get(playerID).refreshMana();
    }

    public boolean wasteMana(int count, int playerID) {
        return playerStates.get(playerID).wasteMana(count);
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

    public List<Creature> getEnemyMinions(int playerID) {
        return getFriendlyMinions(1 - playerID);
    }

    public List<Creature> getEnemyCharacters(int playerID) {
        return getFriendlyCharacters(1 - playerID);
    }

    public int getPlayerID(Creature creature) {
        int id = getID(creature);
        if (id < MAGIC) {
            return 0;
        } else {
            return 1;
        }
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
        return "board state\n" + playerStates.get(0).toString() + playerStates.get(1).toString();
    }
}
