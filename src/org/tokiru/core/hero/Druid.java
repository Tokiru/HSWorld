package org.tokiru.core.hero;

import org.tokiru.core.card.Card;

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
        return null;
    }

    @Override
    public String getName() {
        return "Malfurion Stormrage";
    }
}
