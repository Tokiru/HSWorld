package org.tokiru.core.card.spell.mage;

import org.tokiru.core.board.BoardState;
import org.tokiru.core.card.spell.SkeletalSpellCard;
import org.tokiru.core.card.spell.SpellCard;
import org.tokiru.core.creature.Creature;

/**
 * Created by tokiru.
 */
public class Flamestrike extends SkeletalSpellCard implements SpellCard {
    public Flamestrike() {
        super(7, "Flamestrike");
    }

    @Override
    public boolean canPlay(Creature target, BoardState boardState) {
        return target == null;
    }

    @Override
    public void play(Creature target, BoardState boardState, int playerID, int spellDamage) {
        assert target == null;
        for (Creature creature : boardState.getEnemyMinions(playerID)) {
            creature.takeDamage(4 + spellDamage);
        }
    }
}
