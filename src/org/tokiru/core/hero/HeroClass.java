package org.tokiru.core.hero;

import org.tokiru.core.card.Card;

/**
 * Created by tokiru.
 */
public interface HeroClass {
    public enum Type {
        DRUID,
        HUNTER,
        MAGE,
        PALADIN,
        PRIEST,
        ROGUE,
        SHAMAN,
        WARLOCK,
        WARRIOR
    }

    public Type getType();
    public Card getAbilityCard();
    public String getName();
}

