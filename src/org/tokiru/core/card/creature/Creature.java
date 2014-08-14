package org.tokiru.core.card.creature;

import org.tokiru.core.board.BoardState;
import org.tokiru.core.event.Event;
import org.tokiru.core.event.EventManager;

/**
 * Created by tokiru.
 */
public interface Creature {
    int getHealth();

    int getAttack();

    void takeDamage(int damage);

    void spawn(BoardState boardState, EventManager eventManager);

    void die();

    String getName();

    boolean isAlive();

    void hit(Creature creature);

    public boolean isTaunt();

    public boolean canAttack(Creature target);

    public int getSpellDamage();

    void destroy();
}
