package org.tokiru.core.hero;

import org.tokiru.core.board.BoardState;
import org.tokiru.core.card.Card;
import org.tokiru.core.card.spell.SpellCard;
import org.tokiru.core.card.spell.TargetSpellCard;
import org.tokiru.core.creature.Creature;

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
        return new FireblastCard(2);
    }

    @Override
    public String getName() {
        return "Jaina Proudmoore";
    }

    private class FireblastCard extends TargetSpellCard implements SpellCard {

        public FireblastCard(int cost) {
            super(cost, "Fireblast");
        }

        @Override
        public void play(Creature target, BoardState boardState, int playerID, int spellDamage) {
            target.takeDamage(1);
        }
    }
}
