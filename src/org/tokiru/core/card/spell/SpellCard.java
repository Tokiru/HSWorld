package org.tokiru.core.card.spell;

import org.tokiru.core.board.BoardState;
import org.tokiru.core.card.Card;
import org.tokiru.core.card.creature.Creature;

/**
 * Created by tokiru.
 */
public interface SpellCard extends Card {
    void play(Creature target, BoardState boardState, int playerID, int spellDamage);
}
