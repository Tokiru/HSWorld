package org.tokiru.core.board;

import org.tokiru.core.card.Hand;
import org.tokiru.core.creature.Creature;
import org.tokiru.core.deck.Deck;
import org.tokiru.core.hero.Hero;
import org.tokiru.core.player.Player;
import org.tokiru.core.secret.Secret;

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
    private Player player;
    private SecretArray secretArray;

    public PlayerState() {
        creatures = new ArrayList<>();
        secretArray = new SecretArray();
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

    public void addCreatureLeft(Creature creature, Creature target) {
        int index = Math.max(0, creatures.indexOf(target) - 1);
        addCreature(creature, index);
    }

    public void addCreatureRight(Creature creature, Creature target) {
        int index = Math.min(creatures.indexOf(target) + 1, creatures.size() - 1);
        addCreature(creature, index);
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
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
        sb.append("board: ");
        sb.append("{");
        for (Creature creature : creatures) {
            sb.append("[");
            sb.append(creature.toString());
            sb.append("]");
        }
        sb.append("}\n");

        sb.append("hand: ");
        sb.append(hand);
        sb.append("\n");

        sb.append("secrets: ").append(secretArray).append("\n");

        sb.append("Mana max = ").append(manaCrystals).append(" current = ").append(currentMana).append("\n");

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


    public SecretArray getSecretArray() {
        return secretArray;
    }

    public class SecretArray {
        public SecretArray() {
            secrets = new ArrayList<>();
        }

        public void clear() {
            secrets.clear();
        }

        public void add(Secret secret) {
            secrets.add(secret);
        }

        public void remove(Secret secret) {
            secrets.remove(secret);
        }

        private List<Secret> secrets;

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            for (Secret secret : secrets) {
                sb.append("[").append(secret).append("]");
            }
            return sb.toString();
        }
    }
}
