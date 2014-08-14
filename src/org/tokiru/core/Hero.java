package org.tokiru.core;

import org.tokiru.core.board.BoardState;
import org.tokiru.core.card.creature.Creature;
import org.tokiru.core.card.creature.SkeletonCreature;
import org.tokiru.core.event.Event;
import org.tokiru.core.event.EventManager;
import org.tokiru.core.event.Subscriber;
import org.tokiru.core.player.Player;

/**
 * Created by tokiru.
 */
public class Hero extends SkeletonCreature implements Creature, Subscriber {

    private HeroClass heroClass;
    private int health;
    private BoardState boardState;
    private EventManager eventManager;
    private Player owner;

    public Hero(HeroClass heroClass) {
        this.heroClass = heroClass;
        health = 30;
        attack = 0;
    }    
}
