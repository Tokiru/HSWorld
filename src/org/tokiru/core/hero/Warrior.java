package org.tokiru.core.hero;

import org.tokiru.core.card.Card;

/**
 * Created by tokiru.
 */
public class Warrior implements HeroClass {
    @Override
    public Type getType() {
        return Type.WARRIOR;
    }

    @Override
    public Card getAbilityCard() {
        return null;
    }

    @Override
    public String getName() {
        return "Garrosh Hellscream";
    }
}
