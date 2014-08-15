package org.tokiru.core.creature;

import org.tokiru.core.card.creature.MinionCard;

/**
 * Created by tokiru.
 */
public class MinionBuilder {
    private int health = 1;
    private int attack;
    private int cost;
    private boolean taunt;
    private boolean divineShield;
    private boolean charge;
    private int spellDamage;
    private boolean windFurry;
    private String name;
    private Creature.Race race;
    private boolean targetImmune;

    private SkeletonCreature creature;

    public MinionBuilder() {

    }

    public MinionBuilder(String name, int attack, int health) {
        this.name = name;
        this.attack = attack;
        this.health = health;
    }

    public MinionBuilder setName(String name) {
        this.name = name;
        return this;
    }

    public MinionBuilder setHealth(int health) {
        this.health = health;
        return this;
    }

    public MinionBuilder setAttack(int attack) {
        this.attack = attack;
        return this;
    }

    public MinionBuilder setCost(int cost) {
        this.cost = cost;
        return this;
    }

    public MinionBuilder taunt() {
        taunt = true;
        return this;
    }

    public MinionBuilder divineShield() {
        divineShield = true;
        return this;
    }

    public MinionBuilder charge() {
        charge = true;
        return this;
    }

    public MinionBuilder windFurry() {
        windFurry = true;
        return this;
    }

    public MinionBuilder setSpellDamage(int spellDamage) {
        this.spellDamage = spellDamage;
        return this;
    }

    public MinionBuilder setRace(Creature.Race race) {
        this.race = race;
        return this;
    }

    public MinionBuilder targetImmune() {
        this.targetImmune = true;
        return this;
    }

    public MinionBuilder compile() {
        creature = new SkeletonCreature();
        creature.name = name;
        creature.attack = attack;
        creature.health = health;
        creature.taunt = taunt;
        creature.divineShield = divineShield;
        creature.charge = charge;
        creature.spellDamage = spellDamage;
        creature.windFurry = windFurry;
        creature.race = race;
        creature.targetImmune = targetImmune;
        return this;
    }

    public Creature getCreature() {
        compile();
        return creature;
    }

    public MinionCard getCard() {
        compile();
        return new MinionCard(cost, creature);
    }
}
