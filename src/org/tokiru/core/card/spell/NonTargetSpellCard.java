package org.tokiru.core.card.spell;

import org.tokiru.core.board.BoardState;
import org.tokiru.core.creature.Creature;

/**
 * Created by tokiru.
 */
public abstract class NonTargetSpellCard extends SkeletonSpellCard implements SpellCard {
    public NonTargetSpellCard(int cost) {
        super(cost);
    }

    public NonTargetSpellCard(int cost, String name) {
        super(cost, name);
    }

    @Override
    public boolean canPlay(Creature target, BoardState boardState) {
        return target == null;
    }
}
