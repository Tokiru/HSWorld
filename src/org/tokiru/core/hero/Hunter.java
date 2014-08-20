package org.tokiru.core.hero;

import org.tokiru.core.board.BoardState;
import org.tokiru.core.card.Card;
import org.tokiru.core.card.spell.NonTargetSpellCard;
import org.tokiru.core.card.spell.SpellCard;
import org.tokiru.core.creature.Creature;

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
        public void play(Creature target, BoardState boardState, int playerID, int spellDamage) {
            assert target == null;
            boardState.getHero(1 - playerID).takeDamage(2);
        }
    }
}
