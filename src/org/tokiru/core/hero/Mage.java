package org.tokiru.core.hero;

import org.tokiru.core.board.BoardState;
import org.tokiru.core.card.Card;
import org.tokiru.core.card.SkeletonCard;
import org.tokiru.core.card.creature.Creature;
import org.tokiru.core.card.spell.SpellCard;
import org.tokiru.core.card.spell.TargetSpellCard;
import org.tokiru.core.event.EventManager;

/**
 * Created by tokiru.
 */
public class Mage implements HeroClass {
    @Override
    public Type getType() {
        return Type.MAGE;
    }

    @Override
    public Card getAbilityCard() {
        return null;
    }

    @Override
    public String getName() {
        return "Jaina Proudmoore";
    }

    private class Fireblast extends TargetSpellCard implements SpellCard {

        @Override
        public void play(Creature target, BoardState boardState, EventManager eventManager, int playerID, int spellDamage) {
            target.takeDamage(1);
        }
    }
}
