package org.tokiru.core.card.spell.shaman;

import org.tokiru.core.board.BoardState;
import org.tokiru.core.card.spell.SpellCard;
import org.tokiru.core.card.spell.TargetSpellCard;
import org.tokiru.core.creature.Creature;
import org.tokiru.core.event.EventManager;

/**
 * Created by tokiru.
 */
public class Earthshock extends TargetSpellCard implements SpellCard {
    public Earthshock() {
        super(1, "Earthshock");
    }

    @Override
    public void play(Creature target, BoardState boardState, EventManager eventManager, int playerID, int spellDamage) {
        target.silence();
        target.takeDamage(1 + spellDamage);
    }
}
