package org.tokiru.core.card.spell.rogue;

import org.tokiru.core.board.BoardState;
import org.tokiru.core.card.spell.TargetSpellCard;
import org.tokiru.core.creature.Creature;
import org.tokiru.core.event.EventManager;

/**
 * Created by tokiru.
 */
public class Eviscerate extends ComboCard {

    public Eviscerate() {
        super(2, "Eviscerate");
    }

    @Override
    public void play(Creature target, BoardState boardState, EventManager eventManager, int playerID, int spellDamage) {
        if (comboActive) {
            target.takeDamage(4 + spellDamage);
        } else {
            target.takeDamage(2 + spellDamage);
        }
    }

    @Override
    public boolean canPlay(Creature target, BoardState boardState) {
        return TargetSpellCard.canTargetSpellPlay(target, boardState);
    }
}
