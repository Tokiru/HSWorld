package org.tokiru.core.card.spell.weapon;

import org.tokiru.core.card.Card;
import org.tokiru.core.weapon.*;

/**
 * Created by tokiru.
 */
public class WeaponFactory {
    public static Card truesilverChampion() {
        return new SkeletalWeaponCard(new TruesilverChampion(), 4);
    }

    public static Card arcaniteRapierCard() {
        return new SkeletalWeaponCard(new Weapon(null, 5, 2, "Arcanite Rapier"), 1);
    }

    public static Card gladiatorsLongBowCard() {
        return new SkeletalWeaponCard(new GladiatorsLongbow(), 7);
    }

    public static Card doomhammerCard() {
        return new SkeletalWeaponCard(new Doomhammer(), 5); //ToDo add overload
    }

    public static Card lightsJusticeCard() {
        return new SkeletalWeaponCard(new Weapon(null, 1, 4, "Light's Justice"), 1);
    }

    public static Card fieryWarAxeCard() {
        return new SkeletalWeaponCard(new Weapon(null, 3, 2, "Fiery War Axe"), 2);
    }

    public static Card assasinsBladeCard() {
        return new SkeletalWeaponCard(new Weapon(null, 3, 4, "Assasin's Blade"), 5);
    }

    public static Card gorehowlCard() {
        return new SkeletalWeaponCard(new Gorehowl(), 7);
    }

    public static Card stormforgedAxeCard() {
        return new SkeletalWeaponCard(new StormforgedAxe(), 2);
    }

    public static Card eaglehornBowCard() {
        return new SkeletalWeaponCard(new EaglehornBow(), 3);
    }

    public static Card deathsBiteCard() {
        return new SkeletalWeaponCard(new DeathsBite(), 4);
    }
}
