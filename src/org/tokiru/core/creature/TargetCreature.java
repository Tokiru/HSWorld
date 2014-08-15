package org.tokiru.core.creature;

import org.tokiru.core.board.BoardState;
import org.tokiru.core.creature.Creature;
import org.tokiru.core.creature.SkeletonCreature;
import org.tokiru.core.hero.Hero;

/**
 * Created by tokiru.
 */
public class TargetCreature extends SkeletonCreature implements Creature {

    public TargetCreature(int health, int attack, String name, int cost) {
        super(health, attack, name, cost);
    }

    public TargetCreature(int health, int attack, String name) {
        super(health, attack, name);
    }

    @Override
    public boolean canTarget(Creature target, BoardState boardState) {
        if (target != null) {
            return validTarget(target);
        }

        boolean validTargetExist = false;
        for (Creature creature : boardState.getAllCharacters()) {
            if (validTarget(creature)) {
                validTargetExist = true;
                break;
            }
        }

        return !validTargetExist;
    }

    protected boolean validTarget(Creature target) {
        return !(target instanceof Hero);
    }
}
