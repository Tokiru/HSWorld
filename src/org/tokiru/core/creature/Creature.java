package org.tokiru.core.creature;

import org.tokiru.core.board.BoardState;
import org.tokiru.core.buff.Buff;
import org.tokiru.core.event.EventManager;
import org.tokiru.core.event.Subscriber;
import org.tokiru.core.player.Player;

/**
 * Created by tokiru.
 */
public interface Creature extends Subscriber {
    int getHealth();

    int getAttack();

    void takeDamage(int damage);

    void takeHeal(int heal);

    void spawn(Player owner, BoardState boardState, Creature target);

    void die();

    String getName();

    boolean isAlive();

    void hit(Creature creature);

    boolean isTaunt();

    boolean canAttack(Creature target);

    boolean canTarget(Creature target, BoardState boardState);

    int getSpellDamage();

    void destroy();

    Player getOwner();

    Race getRace();

    boolean isTargetImmune();

    void accept(Buff buff);

    void changeAttack(int value);

    void changeHealth(int value);

    int getMaxHealth();

    void setAttack(int value);

    void setHealth(int value);

    void silence();

    enum Race {
        NONE,
        BEAST,
        DRAGON,
        MURLOC,
        PIRATE,
        DEMON
    }
}
