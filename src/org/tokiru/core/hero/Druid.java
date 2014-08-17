package org.tokiru.core.hero;

import org.tokiru.core.board.BoardState;
import org.tokiru.core.buff.OneTurnAttackBuff;
import org.tokiru.core.card.Card;
import org.tokiru.core.card.spell.NonTargetSpellCard;
import org.tokiru.core.creature.Creature;
import org.tokiru.core.event.EventManager;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

/**
 * Created by tokiru.
 */
public class Druid implements HeroClass {

    @Override
    public Type getType() {
        return Type.DRUID;
    }

    @Override
    public Card getAbilityCard() {
        return new ShapeShift();
    }

    @Override
    public String getName() {
        return "Malfurion Stormrage";
    }

    private class ShapeShift extends NonTargetSpellCard implements Card {

        public ShapeShift() {
            super(2);
        }

        @Override
        public void play(Creature target, BoardState boardState, EventManager eventManager, int playerID, int spellDamage) {
            boardState.getHero(playerID).addArmor(1);
            boardState.getHero(playerID).accept(new OneTurnAttackBuff(1));
        }
    }
}
