package org.tokiru.core.card.spell;

import org.tokiru.core.board.BoardState;
import org.tokiru.core.card.SkeletalCard;

/**
 * Created by tokiru.
 */
public abstract class SkeletalSpellCard extends SkeletalCard implements SpellCard {
    public SkeletalSpellCard(int cost) {
        super(cost);
    }

    public SkeletalSpellCard(int cost, String name) {
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
