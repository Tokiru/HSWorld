package org.tokiru.core.event;

import org.tokiru.core.secret.Secret;

/**
 * Created by tokiru.
 */
public class SecretRevealedEvent implements Event {
    public Secret secret;

    SecretRevealedEvent(Secret secret) {
        this.secret = secret;
    }

    @Override
    public EventType getType() {
        return EventType.SECRET_REVEALED;
    }
}
