package org.tokiru.core.card.spell.paladin;

import org.tokiru.core.board.BoardState;
import org.tokiru.core.buff.Buff;
import org.tokiru.core.buff.SkeletonBuff;
import org.tokiru.core.card.Card;
import org.tokiru.core.card.spell.SkeletonSpellCard;
import org.tokiru.core.card.spell.SpellCard;
import org.tokiru.core.creature.Creature;
import org.tokiru.core.event.AttackEvent;
import org.tokiru.core.event.Event;
import org.tokiru.core.event.EventManager;
import org.tokiru.core.hero.Hero;

/**
 * Created by tokiru.
 */
public class BlessingOfWisdom extends SkeletonSpellCard implements SpellCard{

    public BlessingOfWisdom() {
        super(1, "Blessing of Wisdom");
    }

    @Override
    public void play(Creature target, BoardState boardState, EventManager eventManager, int playerID, int spellDamage) {
        target.accept(new SkeletonBuff() {

            @Override
            public void accept(Event event) {
                if (event.getType() == Event.EventType.ATTACK) {
                    int playerID = creature.getOwner().getID();
                    AttackEvent attackEvent = (AttackEvent) event;
                    if (attackEvent.attacker == creature) {
                        Card newCard = boardState.getDeck(playerID).dealCard();
                        if (newCard == null) {
                            boardState.dealFartigueDamage(playerID);
                        } else {
                            boardState.getHand(playerID).accept(newCard);
                        }
                    }
                }
            }

            @Override
            public void init(Creature creature, BoardState boardState) {
                super.init(creature, boardState);
                boardState.getEventManager().subscribe(this, Event.EventType.ATTACK);
            }
        });
    }

    @Override
    public boolean canPlay(Creature target, BoardState boardState) {
        return target != null && (!(target instanceof Hero));
    }
}
