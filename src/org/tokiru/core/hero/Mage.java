package org.tokiru.core.hero;

import org.tokiru.core.card.Card;

/**
 * Created by tokiru.
 */
public class Mage implements HeroClass {
    @Override
    public Type getType() {
        return Type.MAGE;
    }

    @Override
    public Card getAbilityCard() {
        return null;
    }

    @Override
    public String getName() {
        return "Jaina Proudmoore";
    }
}
