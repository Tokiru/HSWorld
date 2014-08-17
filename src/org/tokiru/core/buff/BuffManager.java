package org.tokiru.core.buff;

import org.tokiru.core.board.BoardState;
import org.tokiru.core.creature.Creature;
import org.tokiru.core.creature.SkeletonCreature;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tokiru.
 */
public class BuffManager {
    private List<Buff> buffList;
    private Creature creature;
    private BoardState boardState;

    public BuffManager(SkeletonCreature creature, BoardState boardState) {
        buffList = new ArrayList<>();
        this.creature = creature;
        this.boardState = boardState;
    }

    public void accept(Buff buff) {
        if (buffList.contains(buff)) {
            return ;
        }
        buffList.add(buff);
        buff.init(creature, boardState);
        apply(buff);
    }

    private void apply(Buff buff) {
        buff = buff.getRaw();
        if (buff instanceof AttackBuff) {
            AttackBuff attackBuff = (AttackBuff) buff;
            creature.changeAttack(attackBuff.attackBuff);
        } else if (buff instanceof HealthBuff) {
            HealthBuff healthBuff = (HealthBuff) buff;
            creature.changeHealth(healthBuff.healthBuff);
        } else if (buff instanceof CombinationBuff) {
            CombinationBuff combinationBuff = (CombinationBuff) buff;
            for (Buff buff1 : combinationBuff.buffList) {
                apply(buff1);
            }
        } else {
            System.out.println("WARNING! buff isn't recognized");
        }
    }

    private void discard(Buff buff, boolean f) {
        if (!buffList.contains(buff)) {
            return;
        }

        buff = buff.getRaw();
        if (buff instanceof AttackBuff) {
            AttackBuff attackBuff = (AttackBuff) buff;
            creature.changeAttack(-attackBuff.attackBuff);
        } else if (buff instanceof HealthBuff) {
            HealthBuff healthBuff = (HealthBuff) buff;
            creature.changeHealth(-healthBuff.healthBuff);
        } else if (buff instanceof CombinationBuff) {
            CombinationBuff combinationBuff = (CombinationBuff) buff;
            for (Buff buff1 : combinationBuff.buffList) {
                discard(buff1, false);
            }
        }

        if (f) {
            buffList.remove(buff);
            buff.discard();
        }
    }

    public void discardAll(boolean unapply) {
        List<Buff> buffListCopy = new ArrayList<>(buffList);
        for (Buff buff : buffListCopy) {
            if (unapply) {
                discard(buff);
            }
            buff.discard();
        }
        buffList.clear();
    }

    public void discard(Buff buff) {
        discard(buff, true);
    }
}
