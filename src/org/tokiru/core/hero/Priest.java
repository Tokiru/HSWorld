package org.tokiru.core.hero;

import org.tokiru.core.card.Card;

/**
 * Created by tokiru.
 */
public class Priest implements HeroClass {
    @Override
    public Type getType() {
        return Type.PRIEST;
    }

    @Override
    public Card getAbilityCard() {
        return null;
    }

    @Override
    public String getName() {
        return "Anduin Wrynn";
    }
}
