package org.tokiru.core.weapon;

import org.tokiru.core.Hero;
import org.tokiru.core.board.BoardState;
import org.tokiru.core.event.EventManager;

/**
 * Created by tokiru.
 */
public class EaglehornBow extends Weapon {
    // ToDo implement secret interaction
    public EaglehornBow() {
        super(null, 3, 2, "Eaglehorn Bow");
    }

    @Override
    public void setEviroment(Hero owner, BoardState boardState, EventManager eventManager) {
        super.setEviroment(owner, boardState, eventManager);
    }
}
