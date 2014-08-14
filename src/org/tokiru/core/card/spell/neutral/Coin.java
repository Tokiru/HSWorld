package org.tokiru.core.card.spell.neutral;

import org.tokiru.core.board.BoardState;
import org.tokiru.core.card.creature.Creature;
import org.tokiru.core.card.spell.SkeletonSpellCard;
import org.tokiru.core.card.spell.SpellCard;

/**
 * Created by tokiru.
 */
public class Coin extends SkeletonSpellCard implements SpellCard {
    public Coin() {
        cost = 0;
        name = "Coin";
    }

    @Override
    public void play(Creature target, BoardState boardState, int playerID, int spellDamage) {
        assert target == null;
        boardState.addMana(1, playerID);
    }

    @Override
    public boolean canPlay(Creature target, BoardState boardState) {
        return target == null;
    }
}
