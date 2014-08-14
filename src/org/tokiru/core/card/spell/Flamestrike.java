package org.tokiru.core.card.spell;

import org.tokiru.core.board.BoardState;
import org.tokiru.core.card.creature.Creature;

/**
 * Created by tokiru.
 */
public class Flamestrike extends SkeletonSpellCard implements SpellCard {
    public Flamestrike() {
        cost = 7;
        name = "Flamestrike";
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

    @Override
    public String getName() {
        return name;
    }
}
