package org.tokiru.core.creature;

import org.tokiru.core.board.BoardState;
import org.tokiru.core.event.EventManager;
import org.tokiru.core.player.Player;

/**
 * Created by tokiru.
 */
public interface Creature {
    int getHealth();

    int getAttack();

    void takeDamage(int damage);

    void takeHeal(int heal);

    void spawn(Player owner, BoardState boardState, EventManager eventManager);

    void die();

    String getName();

    boolean isAlive();

    void hit(Creature creature);

    boolean isTaunt();

    boolean canAttack(Creature target);

    int getSpellDamage();

    void destroy();

    Player getOwner();

    Race getRace();

    boolean isTargetImmune();

    enum Race {
        NONE,
        BEAST,
        DRAGON,
        MURLOC,
        PIRATE,
        DEMON
    }
}
