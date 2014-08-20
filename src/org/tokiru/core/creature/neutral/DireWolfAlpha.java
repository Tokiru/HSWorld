package org.tokiru.core.creature.neutral;

import org.tokiru.core.board.BoardState;
import org.tokiru.core.buff.AttackBuff;
import org.tokiru.core.buff.NeighbourAuraBuff;
import org.tokiru.core.creature.Creature;
import org.tokiru.core.creature.SkeletalCreature;
import org.tokiru.core.player.Player;

/**
 * Created by tokiru.
 */
public class DireWolfAlpha extends SkeletalCreature implements Creature {
    public DireWolfAlpha() {
        super(2, 2, "Dire wolf alpha", 1);
    }

    @Override
    public void spawn(Player owner, BoardState boardState, Creature target) {
        super.spawn(owner, boardState, target);
        this.accept(new NeighbourAuraBuff(new AttackBuff(1)));
    }
}
