package org.tokiru.core.card.spell.weapon;

import org.tokiru.core.Hero;
import org.tokiru.core.board.BoardState;
import org.tokiru.core.card.Card;

/**
 * Created by tokiru.
 */
public interface WeaponCard extends Card {
    void play(Hero owner, BoardState boardState);
}
