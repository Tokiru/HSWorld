package org.tokiru.core.creature.neutral;

import org.tokiru.core.board.BoardState;
import org.tokiru.core.buff.AuraAttackBuff;
import org.tokiru.core.creature.Creature;
import org.tokiru.core.creature.SkeletalCreature;
import org.tokiru.core.player.Player;

/**
 * Created by tokiru.
 */
public class RaidLeader extends SkeletalCreature implements Creature {
    public RaidLeader() {
        super(2, 2, "Raid Leader", 1);
    }

    @Override
    public void spawn(Player owner, BoardState boardState, Creature target) {
        super.spawn(owner, boardState, target);
        this.accept(new AuraAttackBuff(1));
    }
}
