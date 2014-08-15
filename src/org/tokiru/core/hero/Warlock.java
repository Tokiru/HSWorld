package org.tokiru.core.hero;

import org.tokiru.core.board.BoardState;
import org.tokiru.core.card.Card;
import org.tokiru.core.creature.Creature;
import org.tokiru.core.card.spell.NonTargetSpellCard;
import org.tokiru.core.card.spell.SpellCard;
import org.tokiru.core.event.EventManager;

/**
 * Created by tokiru.
 */
public class Warlock implements HeroClass {
    @Override
    public Type getType() {
        return Type.WARLOCK;
    }

    @Override
    public Card getAbilityCard() {
        return new LifeDrainCard();
    }

    @Override
    public String getName() {
        return "Gul'dan";
    }

    private class LifeDrainCard extends NonTargetSpellCard implements SpellCard {

        public LifeDrainCard() {
            super(2, "Life Drain");
        }

        @Override
        public void play(Creature target, BoardState boardState, EventManager eventManager, int playerID, int spellDamage) {
            boardState.getHand(playerID).accept(boardState.getDeck(playerID).dealCard());
            boardState.getHero(playerID).takeDamage(2);
        }
    }
}
