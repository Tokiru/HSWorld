package org.tokiru.core.card.spell;

import org.tokiru.core.board.BoardState;
import org.tokiru.core.creature.Creature;

/**
 * Created by tokiru.
 */
public abstract class TargetSpellCard extends SkeletalSpellCard implements SpellCard {

    public TargetSpellCard(int cost) {
        super(cost);
    }

    public TargetSpellCard(int cost, String name) {
        super(cost, name);
    }

    @Override
    public CardType getType() {
        return CardType.SPELL;
    }

    @Override
    public boolean canPlay(Creature target, BoardState boardState) {
        return canTargetSpellPlay(target, boardState);
    }

    public static boolean canTargetSpellPlay(Creature target, BoardState boardState) {
        return target != null && !target.isTargetImmune();
    }
}
