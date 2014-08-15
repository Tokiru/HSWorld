package org.tokiru.core.hero;

import org.tokiru.core.board.BoardState;
import org.tokiru.core.card.Card;
import org.tokiru.core.card.creature.Creature;
import org.tokiru.core.card.creature.MinionBuilder;
import org.tokiru.core.card.spell.NonTargetSpellCard;
import org.tokiru.core.card.spell.SpellCard;
import org.tokiru.core.event.EventManager;

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
        public void play(Creature target, BoardState boardState, EventManager eventManager, int playerID, int spellDamage) {
            boardState.addCreature(new MinionBuilder().setHealth(1).setAttack(1).setName("Silverhand Recruit").compile().getCreature(), playerID);
        }
    }
}
