package org.tokiru.core.card.creature;

import org.tokiru.core.Board.BoardState;

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
    void acceptEvent();
}
