package org.tokiru.core.card.spell;

import org.tokiru.core.card.SkeletonCard;

/**
 * Created by tokiru.
 */
public abstract class SkeletonSpellCard extends SkeletonCard implements SpellCard{
    @Override
    public CardType getType() {
        return CardType.SPELL;
    }
}
