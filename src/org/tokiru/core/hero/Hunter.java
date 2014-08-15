package org.tokiru.core.hero;

import org.tokiru.core.card.Card;

/**
 * Created by tokiru.
 */
public class Hunter implements HeroClass {
    @Override
    public Type getType() {
        return Type.HUNTER;
    }

    @Override
    public Card getAbilityCard() {
        return null;
    }

    @Override
    public String getName() {
        return "Rexxar";
    }
}
