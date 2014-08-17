package org.tokiru.core.card.weapon;

import org.tokiru.core.card.Card;
import org.tokiru.core.weapon.*;

/**
 * Created by tokiru.
 */
public class WeaponFactory {
    public static Card truesilverChampion() {
        return new SkeletonWeaponCard(new TruesilverChampion(), 4);
    }

    public static Card arcaniteRapierCard() {
        return new SkeletonWeaponCard(new Weapon(null, 5, 2, "Arcanite Rapier"), 1);
    }

    public static Card gladiatorsLongBowCard() {
        return new SkeletonWeaponCard(new GladiatorsLongbow(), 7);
    }

    public static Card doomhammerCard() {
        return new SkeletonWeaponCard(new Doomhammer(), 5); //ToDo add overload
    }

    public static Card lightsJusticeCard() {
        return new SkeletonWeaponCard(new Weapon(null, 1, 4, "Light's Justice"), 1);
    }

    public static Card fieryWarAxeCard() {
        return new SkeletonWeaponCard(new Weapon(null, 3, 2, "Fiery War Axe"), 2);
    }

    public static Card assasinsBladeCard() {
        return new SkeletonWeaponCard(new Weapon(null, 3, 4, "Assasin's Blade"), 5);
    }

    public static Card gorehowlCard() {
        return new SkeletonWeaponCard(new Gorehowl(), 7);
    }

    public static Card stormforgedAxeCard() {
        return new SkeletonWeaponCard(new StormforgedAxe(), 2);
    }

    public static Card eaglehornBowCard() {
        return new SkeletonWeaponCard(new EaglehornBow(), 3);
    }

    public static Card deathsBiteCard() {
        return new SkeletonWeaponCard(new DeathsBite(), 4);
    }
}
