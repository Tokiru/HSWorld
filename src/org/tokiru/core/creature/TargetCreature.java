package org.tokiru.core.creature;

import org.tokiru.core.board.BoardState;
import org.tokiru.core.creature.Creature;
import org.tokiru.core.creature.SkeletonCreature;
import org.tokiru.core.hero.Hero;

/**
 * Created by tokiru.
 */
public class TargetCreature extends SkeletonCreature implements Creature {
    private boolean validateHero;

    public TargetCreature(int health, int attack, String name, int cost, boolean validateHero) {
        super(health, attack, name, cost);
        this.validateHero = validateHero;
    }

    public TargetCreature(int health, int attack, String name) {
        this(health, attack, name, 0, false);
    }
    public TargetCreature(int health, int attack, String name, int cost) {
        this(health, attack, name, cost, false);
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
        return !(target instanceof Hero) || validateHero;
    }
}
