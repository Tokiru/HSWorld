package org.tokiru.core.hero;

import org.tokiru.core.board.BoardState;
import org.tokiru.core.card.Card;
import org.tokiru.core.card.creature.Creature;
import org.tokiru.core.card.spell.SpellCard;
import org.tokiru.core.card.spell.TargetSpellCard;
import org.tokiru.core.event.EventManager;

/**
 * Created by tokiru.
 */
public class Priest implements HeroClass {
    @Override
    public Type getType() {
        return Type.PRIEST;
    }

    @Override
    public Card getAbilityCard() {
        return new LesserHealCard();
    }

    @Override
    public String getName() {
        return "Anduin Wrynn";
    }

    private class LesserHealCard extends TargetSpellCard implements SpellCard {

        public LesserHealCard() {
            super(2, "Lesser heal");
        }

        @Override
        public void play(Creature target, BoardState boardState, EventManager eventManager, int playerID, int spellDamage) {
            target.takeHeal(2);
        }
    }
}
