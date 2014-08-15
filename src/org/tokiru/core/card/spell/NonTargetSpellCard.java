package org.tokiru.core.card.spell;

import org.tokiru.core.board.BoardState;
import org.tokiru.core.card.creature.Creature;

/**
 * Created by tokiru.
 */
public abstract class NonTargetSpellCard extends SkeletonSpellCard implements SpellCard {
    @Override
    public boolean canPlay(Creature target, BoardState boardState) {
        return target == null;
    }
}
