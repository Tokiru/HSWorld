package org.tokiru.core.card.creature;

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

    public MinionBuilder() {

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

    public MinionBuilder spellDamage(int spellDamage) {
        this.spellDamage = spellDamage;
        return this;
    }

    public MinionCard getCard() {
        SkeletonCreature creature = new SkeletonCreature();
        creature.name = name;
        creature.attack = attack;
        creature.health = health;
        creature.taunt = taunt;
        creature.divineShield = divineShield;
        creature.charge = charge;
        creature.spellDamage = spellDamage;
        creature.windFurry = windFurry;
        return new MinionCard(cost, creature);
    }

}
