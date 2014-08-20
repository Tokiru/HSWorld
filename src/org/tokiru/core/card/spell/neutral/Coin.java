package org.tokiru.core.card.spell.neutral;

import org.tokiru.core.board.BoardState;
import org.tokiru.core.card.spell.SkeletalSpellCard;
import org.tokiru.core.card.spell.SpellCard;
import org.tokiru.core.creature.Creature;

/**
 * Created by tokiru.
 */
public class Coin extends SkeletalSpellCard implements SpellCard {
    public Coin() {
        super(0, "Coin");
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
