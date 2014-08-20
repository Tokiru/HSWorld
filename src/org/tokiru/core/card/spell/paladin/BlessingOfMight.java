package org.tokiru.core.card.spell.paladin;

import org.tokiru.core.board.BoardState;
import org.tokiru.core.buff.AttackBuff;
import org.tokiru.core.card.spell.SpellCard;
import org.tokiru.core.card.spell.TargetSpellCard;
import org.tokiru.core.creature.Creature;

/**
 * Created by tokiru.
 */
public class BlessingOfMight extends TargetSpellCard implements SpellCard {

    public BlessingOfMight() {
        super(1, "Blessing of Might");
    }

    @Override
    public void play(Creature target, BoardState boardState, int playerID, int spellDamage) {
        target.accept(new AttackBuff(3));
    }
}
