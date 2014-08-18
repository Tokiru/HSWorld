package org.tokiru.core.card.spell;

import org.tokiru.core.board.BoardState;
import org.tokiru.core.card.SkeletonCard;

/**
 * Created by tokiru.
 */
public abstract class SkeletonSpellCard extends SkeletonCard implements SpellCard {
    public SkeletonSpellCard(int cost) {
        super(cost);
    }

    public SkeletonSpellCard(int cost, String name) {
        super(cost, name);
    }

    @Override
    public CardType getType() {
        return CardType.SPELL;
    }

    @Override
    public void init(BoardState boardState) {

    }
}
