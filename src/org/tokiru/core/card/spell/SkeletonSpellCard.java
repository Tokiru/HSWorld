package org.tokiru.core.card.spell;

import org.tokiru.core.board.BoardState;
import org.tokiru.core.card.SkeletonCard;
import org.tokiru.core.card.creature.Creature;
import org.tokiru.core.card.spell.SpellCard;

/**
 * Created by tokiru.
 */
public abstract class SkeletonSpellCard extends SkeletonCard implements SpellCard{
    @Override
    public CardType getType() {
        return CardType.SPELL;
    }
}
