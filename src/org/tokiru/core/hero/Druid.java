package org.tokiru.core.hero;

import org.tokiru.core.card.Card;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

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
        throw new NotImplementedException();
    }

    @Override
    public String getName() {
        return "Malfurion Stormrage";
    }

}
