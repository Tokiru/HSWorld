package org.tokiru.core.card.creature.neutral;

import org.tokiru.core.card.Card;
import org.tokiru.core.card.creature.MinionCard;
import org.tokiru.core.creature.Creature;
import org.tokiru.core.creature.MinionBuilder;
import org.tokiru.core.creature.TargetCreature;
import org.tokiru.core.creature.neutral.DarkIronDwarf;
import org.tokiru.core.creature.neutral.DefenderOfArgus;
import org.tokiru.core.creature.neutral.HarvestGolem;
import org.tokiru.core.creature.neutral.KnifeJuggler;

/**
 * Created by tokiru.
 */
public class MinionFactory {
    // 0 mana
    public static MinionCard wisp() {
        return new MinionBuilder("Wisp", 1, 1).setCost(0).getCard();
    }

    public static MinionCard argentSquire() {
        return new MinionBuilder("Argent Squire", 1, 1).setCost(1).divineShield().getCard();
    }

    public static MinionCard shieldbearer() {
        return new MinionBuilder("Shieldbearer", 0, 4).setCost(1).taunt().getCard();
    }

    public static MinionCard youngDragonhawk() {
        return new MinionBuilder("Young Dragonhawk", 1, 1).setCost(1).windFurry().setRace(Creature.Race.BEAST).getCard();
    }

    public static MinionCard senjinShieldmastaCard() {
        return new MinionBuilder("Sen'jin Shieldmasta", 3, 5).taunt().compile().setCost(4).getCard();
    }

    public static MinionCard chillWindYetiCard() {
        return new MinionBuilder("Chillwind Yeti", 4, 5).compile().setCost(4).getCard();
    }

    public static MinionCard bloodfenRaptorCard() {
        return new MinionBuilder("BloodfenRaptor", 3, 2).setRace(Creature.Race.BEAST).setCost(3).getCard();
    }

    public static MinionCard ogreMagiCard() {
        return new MinionBuilder("Ogre Magi", 4, 4).setSpellDamage(4).setCost(4).getCard();
    }

    public static MinionCard boulderfistOgreCard() {
        return new MinionBuilder("Boulderfist Ogre", 6, 7).setCost(6).getCard();
    }

    public static MinionCard knifeJugglerCard() {
        return new MinionCard(2, new KnifeJuggler());
    }

    public static MinionCard harvestGolemCard() {
        return new MinionCard(3, new HarvestGolem());
    }

    public static MinionCard darkIronDwarfCard() {
        return new MinionCard(4, new DarkIronDwarf());
    }

    public static MinionCard defenderOfArgusCard() {
        return new MinionCard(4, new DefenderOfArgus());
    }
}
