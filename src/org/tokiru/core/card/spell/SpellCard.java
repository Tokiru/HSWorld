package org.tokiru.core.card.spell;

import org.tokiru.core.board.BoardState;
import org.tokiru.core.card.Card;
import org.tokiru.core.creature.Creature;
import org.tokiru.core.event.EventManager;

/**
 * Created by tokiru.
 */
public interface SpellCard extends Card {
    void play(Creature target, BoardState boardState, EventManager eventManager, int playerID, int spellDamage);
}
