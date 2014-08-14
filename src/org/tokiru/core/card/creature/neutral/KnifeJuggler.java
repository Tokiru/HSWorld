package org.tokiru.core.card.creature.neutral;

import org.tokiru.core.board.BoardState;
import org.tokiru.core.card.Card;
import org.tokiru.core.card.creature.Creature;
import org.tokiru.core.card.creature.MinionCard;
import org.tokiru.core.card.creature.SkeletonCreature;
import org.tokiru.core.event.Event;
import org.tokiru.core.event.EventManager;
import org.tokiru.core.event.SummonMinionEvent;
import org.tokiru.core.player.Player;

import java.util.List;

/**
 * Created by tokiru.
 */
public class KnifeJuggler extends SkeletonCreature implements Creature {
    public KnifeJuggler() {
        name = "Knife Juggler";
        attack = 3;
        health = 2;
    }

    @Override
    public void spawn(Player owner, BoardState boardState, EventManager eventManager) {
        super.spawn(owner, boardState, eventManager);
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
