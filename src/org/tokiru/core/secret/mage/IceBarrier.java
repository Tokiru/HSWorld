package org.tokiru.core.secret.mage;

import org.tokiru.core.board.BoardState;
import org.tokiru.core.secret.SkeletalSecret;
import org.tokiru.core.event.AttackEvent;
import org.tokiru.core.event.Event;
import org.tokiru.core.player.Player;
import org.tokiru.core.secret.Secret;

/**
 * Created by tokiru.
 */
public class IceBarrier extends SkeletalSecret implements Secret {
    public IceBarrier() {
        super("Ice Barrier");
    }

    @Override
    public void play(Player owner, BoardState boardState) {
        super.play(owner, boardState);
        boardState.getEventManager().subscribe(this, Event.EventType.ATTACK);
    }

    @Override
    public void accept(Event event) {
        if (event.getType() == Event.EventType.ATTACK) {
            AttackEvent attackEvent = (AttackEvent) event;
            if (attackEvent.defender == owner.getHero()) {
                boardState.getHero(owner.getID()).addArmor(8);
                destroy();
            }
        }
    }
}
