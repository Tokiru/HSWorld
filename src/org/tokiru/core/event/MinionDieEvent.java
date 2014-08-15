package org.tokiru.core.event;

import com.sun.javafx.scene.control.behavior.ScrollBarBehavior;
import org.tokiru.core.creature.Creature;

/**
 * Created by tokiru.
 */
public class MinionDieEvent implements Event {
    public Creature minion;

    public MinionDieEvent(Creature minion) {
        this.minion = minion;
    }

    @Override
    public EventType getType() {
        return EventType.MINION_DIE;
    }
}
