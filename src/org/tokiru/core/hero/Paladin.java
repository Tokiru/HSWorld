package org.tokiru.core.hero;

import org.tokiru.core.card.Card;

/**
 * Created by tokiru.
 */
public class Paladin implements HeroClass {
    @Override
    public Type getType() {
        return Type.PALADIN;
    }

    @Override
    public Card getAbilityCard() {
        return null;
    }

    @Override
    public String getName() {
        return "Uther Lightbringer";
    }
}
