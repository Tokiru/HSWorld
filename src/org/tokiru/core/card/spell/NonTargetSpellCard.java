package org.tokiru.core.card.spell;

import org.tokiru.core.board.BoardState;
import org.tokiru.core.card.creature.Creature;
import org.tokiru.core.event.EventManager;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

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
