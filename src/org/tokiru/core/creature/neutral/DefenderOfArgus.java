package org.tokiru.core.creature.neutral;

import org.tokiru.core.board.BoardState;
import org.tokiru.core.buff.AttackBuff;
import org.tokiru.core.buff.CombinationBuff;
import org.tokiru.core.buff.HealthBuff;
import org.tokiru.core.creature.Creature;
import org.tokiru.core.creature.SkeletalCreature;
import org.tokiru.core.player.Player;

import java.util.List;

/**
 * Created by tokiru.
 */
public class DefenderOfArgus extends SkeletalCreature {
    public DefenderOfArgus() {
        super(3, 2, "Defender of Argus", 4);
    }

    @Override
    public void spawn(Player owner, BoardState boardState, Creature target) {
        super.spawn(owner, boardState, target);
        List<Creature> neighbours = boardState.getNeighbours(this);
        for (Creature neighbour : neighbours) {
            try {
                SkeletalCreature skeletalCreature = (SkeletalCreature) neighbour;
                skeletalCreature.taunt = true;
                skeletalCreature.accept(new CombinationBuff().add(new AttackBuff(1)).add(new HealthBuff(1)));
            } catch (ClassCastException e) {
                throw new AssertionError("neighbour must be a skeletal creature");
            }
        }
    }

    @Override
    public boolean canTarget(Creature target, BoardState boardState) {
        return super.canTarget(target, boardState);
    }
}
