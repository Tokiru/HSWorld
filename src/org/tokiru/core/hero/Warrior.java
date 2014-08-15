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
public class Warrior implements HeroClass {
    @Override
    public Type getType() {
        return Type.WARRIOR;
    }

    @Override
    public Card getAbilityCard() {
        return new ShieldUpCard();
    }

    @Override
    public String getName() {
        return "Garrosh Hellscream";
    }

    private class ShieldUpCard extends NonTargetSpellCard implements SpellCard {
        public ShieldUpCard() {
            super(2, "Shield up");
        }

        @Override
        public void play(Creature target, BoardState boardState, EventManager eventManager, int playerID, int spellDamage) {
            boardState.getHero(playerID).addArmor(2);
        }
    }
}
