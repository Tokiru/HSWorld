package org.tokiru.core.event;

import org.tokiru.core.card.Card;

/**
 * Created by tokiru.
 */
public class CardPlayedEvent implements Event{

    public Card card;

    public CardPlayedEvent(Card card) {
        this.card = card;
    }

    @Override
    public EventType getType() {
        return EventType.CARD_PLAYED;
    }
}
