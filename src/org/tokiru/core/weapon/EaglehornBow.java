package org.tokiru.core.weapon;

import org.tokiru.core.hero.Hero;
import org.tokiru.core.board.BoardState;
import org.tokiru.core.event.Event;
import org.tokiru.core.event.EventManager;
import org.tokiru.core.event.SecretRevealedEvent;
import org.tokiru.core.event.Subscriber;

/**
 * Created by tokiru.
 */
public class EaglehornBow extends Weapon implements Subscriber {
    // ToDo test secret interaction
    public EaglehornBow() {
        super(null, 3, 2, "Eaglehorn Bow");
    }


    @Override
    public void setEnvironment(Hero owner, BoardState boardState, EventManager eventManager) {
        super.setEnvironment(owner, boardState, eventManager);
        eventManager.subscribe(this, Event.EventType.SECRET_REVEALED);
    }

    @Override
    public void accept(Event event) {
        if (event.getType() == Event.EventType.SECRET_REVEALED) {
            SecretRevealedEvent secretRevealedEvent = (SecretRevealedEvent) event;
            if (secretRevealedEvent.secret.getOwner() == owner) {
                changeDurability(1);
            }
        }
    }
}
