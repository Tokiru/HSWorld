package org.tokiru.core.card.spell.weapon;

import org.tokiru.core.board.BoardState;
import org.tokiru.core.card.Card;
import org.tokiru.core.event.EventManager;
import org.tokiru.core.hero.Hero;

/**
 * Created by tokiru.
 */
public interface WeaponCard extends Card {
    void play(Hero owner, BoardState boardState, EventManager eventManager);
}
