package org.tokiru.core.creature.neutral;

import org.tokiru.core.board.BoardState;
import org.tokiru.core.buff.AttackBuff;
import org.tokiru.core.buff.AuraBuff;
import org.tokiru.core.buff.CombinationBuff;
import org.tokiru.core.buff.HealthBuff;
import org.tokiru.core.creature.Creature;
import org.tokiru.core.creature.SkeletonCreature;
import org.tokiru.core.event.EventManager;
import org.tokiru.core.player.Player;

/**
 * Created by tokiru.
 */
public class StormwindChampion extends SkeletonCreature implements Creature {
    public StormwindChampion() {
        super(6, 6, "Stormwind Champion", 1);
    }

    @Override
    public void spawn(Player owner, BoardState boardState, EventManager eventManager, Creature target) {
        super.spawn(owner, boardState, eventManager, target);
        this.accept(new AuraBuff(new CombinationBuff().add(new AttackBuff(1)).add(new HealthBuff(1))));
    }
}
