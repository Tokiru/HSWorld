package org.tokiru.core.card.creature;

import org.tokiru.core.board.BoardState;
import org.tokiru.core.event.Event;

/**
 * Created by tokiru.
 */
public interface Creature {
    int getHealth();

    int getAttack();

    void takeDamage(int damage);

    void spawn(BoardState boardState);

    void die();

    String getName();

    boolean isAlive();

    void hit(Creature creature);

    void acceptEvent(Event event);

    public boolean isTaunt();

    public boolean canAttack(Creature target);

    public int getSpellDamage();
}
