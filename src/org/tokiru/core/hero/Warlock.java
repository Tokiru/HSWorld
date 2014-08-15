package org.tokiru.core.hero;

import org.tokiru.core.card.Card;

/**
 * Created by tokiru.
 */
public class Warlock implements HeroClass {
    @Override
    public Type getType() {
        return Type.WARLOCK;
    }

    @Override
    public Card getAbilityCard() {
        return null;
    }

    @Override
    public String getName() {
        return "Gul'dan";
    }
}
