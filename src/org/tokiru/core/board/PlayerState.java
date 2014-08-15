package org.tokiru.core.board;

import org.tokiru.core.hero.Hero;
import org.tokiru.core.card.Deck;
import org.tokiru.core.card.Hand;
import org.tokiru.core.card.creature.Creature;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tokiru.
 */
public class PlayerState {
    public static final int HERO_ID = 100;
    private Hero hero;
    private Deck deck;
    private Hand hand;
    private List<Creature> creatures;
    private int fartigue;
    private int manaCrystals;
    private int currentMana;

    public PlayerState() {
        creatures = new ArrayList<>();
        fartigue = 0;
    }

    public boolean isAlive() {
        return hero.isAlive();
    }

    public Hero getHero() {
        return hero;
    }

    public void setHero(Hero hero) {
        this.hero = hero;
    }

    public Deck getDeck() {
        return deck;
    }

    public void setDeck(Deck deck) {
        this.deck = deck;
    }

    public Hand getHand() {
        return hand;
    }

    public void setHand(Hand hand) {
        this.hand = hand;
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

    public void refreshMana() {
        currentMana = manaCrystals;
    }

    public void addMana(int count) {
        currentMana += count;
        if (currentMana > 10) {
            currentMana = 10;
        }
    }

    public boolean spendMana(int count) {
        if (currentMana >= count) {
            currentMana -= count;
            return true;
        } else {
            return false;
        }
    }

    public List<Creature> getMinions() {
        List<Creature> result = new ArrayList<>(creatures);
        return result;
    }

    public List<Creature> getCharacters() {
        List<Creature> result = getMinions();
        result.add(hero);
        return result;
    }

    public void dealFartigueDamage() {
        hero.takeDamage(++fartigue);
    }

    public int getManaCrystals() {
        return manaCrystals;
    }

    public void addManaCrystal(int value) {
        if (manaCrystals < 10) {
            manaCrystals += value;
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("PlayerState\n");
        sb.append(hero.toString());
        sb.append("\n");
        sb.append("board\n");
        sb.append("{");
        for (Creature creature : creatures) {
            sb.append("[");
            sb.append(creature.toString());
            sb.append("]");
        }
        sb.append("}\n");

        sb.append("hand\n");
        sb.append(hand);
        sb.append("\n");

        sb.append("Mana max = " + manaCrystals + " current = " + currentMana + "\n");

        return sb.toString();
    }

    public void removeCreature(Creature creature) {
        creatures.remove(creature);
    }

    public int getSpellDamage() {
        int result = 0;
        for (Creature creature : creatures) {
            result += creature.getSpellDamage();
        }

        return result;
    }
}
