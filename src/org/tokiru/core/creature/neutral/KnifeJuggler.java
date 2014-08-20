package org.tokiru.core.creature.neutral;

import org.tokiru.core.board.BoardState;
import org.tokiru.core.creature.Creature;
import org.tokiru.core.creature.SkeletalCreature;
import org.tokiru.core.event.Event;
import org.tokiru.core.event.SummonMinionEvent;
import org.tokiru.core.player.Player;

import java.util.List;

/**
 * Created by tokiru.
 */
public class KnifeJuggler extends SkeletalCreature implements Creature {
    public KnifeJuggler() {
        name = "Knife Juggler";
        attack = 3;
        health = 2;
    }

    @Override
    public void spawn(Player owner, BoardState boardState, Creature target) {
        super.spawn(owner, boardState, null);
        eventManager.subscribe(this, Event.EventType.SUMMON_MINION);
    }

    @Override
    public void accept(Event event) {
        super.accept(event);
        if (event.getType() == Event.EventType.SUMMON_MINION) {
            SummonMinionEvent summonMinionEvent = (SummonMinionEvent) event;
            Creature summonedMinion = summonMinionEvent.summonedMinion;
            Player minionOwner = summonedMinion.getOwner();
            if (minionOwner == getOwner() && summonedMinion != this) {
                List<Creature> enemies = boardState.getEnemyCharacters(getOwner().getID());
                int index = (int) (Math.random() * enemies.size());
                enemies.get(index).takeDamage(1);
            }
        }
    }
}
