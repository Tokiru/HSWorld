package org.tokiru.core.card.spell.rogue;

import org.tokiru.core.board.BoardState;
import org.tokiru.core.card.Card;
import org.tokiru.core.card.SkeletonCard;
import org.tokiru.core.card.spell.SkeletonSpellCard;
import org.tokiru.core.creature.Creature;
import org.tokiru.core.event.Event;
import org.tokiru.core.event.EventManager;
import org.tokiru.core.event.Subscriber;

/**
 * Created by tokiru.
 */
public abstract class ComboCard extends SkeletonSpellCard implements Card, Subscriber {

    private BoardState boardState;
    protected boolean comboActive;

    public ComboCard(int cost) {
        super(cost);
    }

    public ComboCard(int cost, String name) {
        super(cost, name);
    }

    @Override
    public void init(BoardState boardState) {
        super.init(boardState);
        boardState.getEventManager().subscribe(this, Event.EventType.CARD_PLAYED);
        boardState.getEventManager().subscribe(this, Event.EventType.END_TURN);
    }

    @Override
    public void accept(Event event) {
        if (event.getType() == Event.EventType.CARD_PLAYED) {
            comboActive = true;
        }

        if (event.getType() == Event.EventType.END_TURN) {
            comboActive = false;
        }
    }

}
