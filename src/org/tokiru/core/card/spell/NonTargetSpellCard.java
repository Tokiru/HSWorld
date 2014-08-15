package org.tokiru.core.card.spell;

import org.tokiru.core.board.BoardState;
import org.tokiru.core.card.Card;
import org.tokiru.core.card.SkeletonCard;
import org.tokiru.core.card.creature.Creature;

/**
 * Created by tokiru.
 */
public class NonTargetSpellCard extends SkeletonCard implements SpellCard {
    @Override
    public void play(Creature target, BoardState boardState, int playerID, int spellDamage) {

    }

    @Override
    public CardType getType() {
        return null;
    }

    @Override
    public boolean canPlay(Creature target, BoardState boardState) {
        return false;
    }
}
