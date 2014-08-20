package org.tokiru.core.card.spell.paladin;

import org.tokiru.core.board.BoardState;
import org.tokiru.core.buff.OneTurnAttackBuff;
import org.tokiru.core.card.spell.SpellCard;
import org.tokiru.core.card.spell.TargetSpellCard;
import org.tokiru.core.creature.Creature;

/**
 * Created by tokiru.
 */
public class BlessingOfKings extends TargetSpellCard implements SpellCard {

    public BlessingOfKings() {
        // ToDo set actual cost
        super(1, "Blessing of Kings");
    }

    @Override
    public void play(Creature target, BoardState boardState, int playerID, int spellDamage) {
        //target.accept(new CombinationBuff().add(new AttackBuff(4)).add(new HealthBuff(4)));
        //target.accept(new OneTurnBuff());
        target.accept(new OneTurnAttackBuff(2));
    }

    @Override
    public boolean canPlay(Creature target, BoardState boardState) {
        return false;
    }
}
