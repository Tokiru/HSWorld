package org.tokiru.core.card.creature.neutral;

import org.tokiru.core.card.Card;
import org.tokiru.core.card.creature.MinionCard;
import org.tokiru.core.creature.Creature;
import org.tokiru.core.creature.MinionBuilder;
import org.tokiru.core.creature.neutral.HarvestGolem;
import org.tokiru.core.creature.neutral.KnifeJuggler;

/**
 * Created by tokiru.
 */
public class MinionFactory {
    public static Card senjinShieldmastaCard() {
        return new MinionBuilder("Sen'jin Shieldmasta", 3, 5).taunt().compile().setCost(4).getCard();
    }

    public static Card chillWindYetiCard() {
        return new MinionBuilder("Chillwind Yeti", 4, 5).compile().setCost(4).getCard();
    }

    public static Card bloodfenRaptorCard() {
        return new MinionBuilder("BloodfenRaptor", 3, 2).setRace(Creature.Race.BEAST).setCost(3).getCard();
    }

    public static Card ogreMagiCard() {
        return new MinionBuilder("Ogre Magi", 4, 4).setSpellDamage(4).setCost(4).getCard();
    }

    public static Card boulderfistOgreCard() {
        return new MinionBuilder("Boulderfist Ogre", 6, 7).setCost(6).getCard();
    }

    public static Card knifeJugglerCard() {
        return new MinionCard(2, new KnifeJuggler());
    }

    public static Card harvestGolemCard() {
        return new MinionCard(3, new HarvestGolem());
    }
}
