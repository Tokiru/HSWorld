package org.tokiru.core.card.spell;

import org.tokiru.core.board.BoardState;
import org.tokiru.core.card.creature.Creature;

/**
 * Created by tokiru.
 */
public abstract class TargetSpellCard extends SkeletonSpellCard implements SpellCard {

    @Override
    public CardType getType() {
        return CardType.SPELL;
    }

    @Override
    public boolean canPlay(Creature target, BoardState boardState) {
        if (target == null) {
            return false;
        } else {
            return !target.isTargetImmune();
        }
    }
}
