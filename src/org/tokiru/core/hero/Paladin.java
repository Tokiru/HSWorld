package org.tokiru.core.hero;

import org.tokiru.core.board.BoardState;
import org.tokiru.core.card.Card;
import org.tokiru.core.card.spell.NonTargetSpellCard;
import org.tokiru.core.card.spell.SpellCard;
import org.tokiru.core.creature.Creature;
import org.tokiru.core.creature.MinionBuilder;

/**
 * Created by tokiru.
 */
public class Paladin implements HeroClass {
    @Override
    public Type getType() {
        return Type.PALADIN;
    }

    @Override
    public Card getAbilityCard() {
        return new ReinforcementCard(2);
    }

    @Override
    public String getName() {
        return "Uther Lightbringer";
    }

    private class ReinforcementCard extends NonTargetSpellCard implements SpellCard {

        public ReinforcementCard(int cost) {
            super(cost, "Reinforcement");
        }

        @Override
        public void play(Creature target, BoardState boardState, int playerID, int spellDamage) {
            Creature creature = new MinionBuilder().setHealth(1).setAttack(1).setName("Silverhand Recruit").compile().getCreature();
            boardState.addCreature(creature, null, playerID);
        }
    }
}
