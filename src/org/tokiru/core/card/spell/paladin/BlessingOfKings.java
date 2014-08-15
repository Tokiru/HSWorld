package org.tokiru.core.card.spell.paladin;

import org.tokiru.core.board.BoardState;
import org.tokiru.core.buff.AttackBuff;
import org.tokiru.core.buff.CombinationBuff;
import org.tokiru.core.buff.HealthBuff;
import org.tokiru.core.card.spell.SpellCard;
import org.tokiru.core.card.spell.TargetSpellCard;
import org.tokiru.core.creature.Creature;
import org.tokiru.core.event.EventManager;

/**
 * Created by tokiru.
 */
public class BlessingOfKings extends TargetSpellCard implements SpellCard {

    public BlessingOfKings() {
        // ToDo set actual cost
        super(1, "Blessing of Kings");
    }

    @Override
    public void play(Creature target, BoardState boardState, EventManager eventManager, int playerID, int spellDamage) {
        target.accept(new CombinationBuff().add(new AttackBuff(4)).add(new HealthBuff(4)));
    }

    @Override
    public boolean canPlay(Creature target, BoardState boardState) {
        return false;
    }
}
