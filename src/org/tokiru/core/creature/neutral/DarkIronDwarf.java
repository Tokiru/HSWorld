package org.tokiru.core.creature.neutral;

import org.tokiru.core.board.BoardState;
import org.tokiru.core.buff.OneTurnAttackBuff;
import org.tokiru.core.creature.Creature;
import org.tokiru.core.creature.TargetCreature;
import org.tokiru.core.event.EventManager;
import org.tokiru.core.hero.Hero;
import org.tokiru.core.player.Player;

/**
 * Created by tokiru.
 */
public class DarkIronDwarf extends TargetCreature implements Creature {
    @Override
    public void spawn(Player owner, BoardState boardState, EventManager eventManager, Creature target) {
        super.spawn(owner, boardState, eventManager, target);
        assert !(target instanceof Hero);
        if (target != null) {
            target.accept(new OneTurnAttackBuff(2));
        }
    }

    @Override
    protected boolean validTarget(Creature target) {
        return !(target instanceof Hero);
    }

    public DarkIronDwarf() {
        super(4, 4, "Dark Iron Dwarf", 4, false);
    }
}
