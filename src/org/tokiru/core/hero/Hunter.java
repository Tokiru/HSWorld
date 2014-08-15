package org.tokiru.core.hero;

import org.tokiru.core.board.BoardState;
import org.tokiru.core.card.Card;
import org.tokiru.core.card.creature.Creature;
import org.tokiru.core.card.spell.NonTargetSpellCard;
import org.tokiru.core.card.spell.SpellCard;
import org.tokiru.core.event.EventManager;

/**
 * Created by tokiru.
 */
public class Hunter implements HeroClass {
    @Override
    public Type getType() {
        return Type.HUNTER;
    }

    @Override
    public Card getAbilityCard() {
        return new SteadyShotCard();
    }

    @Override
    public String getName() {
        return "Rexxar";
    }

    private class SteadyShotCard extends NonTargetSpellCard implements SpellCard {
        public SteadyShotCard() {
            super(2, "Steady Shot");
        }
        @Override
        public void play(Creature target, BoardState boardState, EventManager eventManager, int playerID, int spellDamage) {
            assert target == null;
            boardState.getHero(1 - playerID).takeDamage(2);
        }
    }
}
