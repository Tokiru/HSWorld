package org.tokiru.core.hero;

import org.tokiru.core.card.Card;
import org.tokiru.core.card.weapon.SkeletonWeaponCard;
import org.tokiru.core.weapon.Weapon;

/**
 * Created by tokiru.
 */
public class Rogue implements HeroClass {

    @Override
    public Type getType() {
        return Type.ROGUE;
    }

    @Override
    public Card getAbilityCard() {
        return new SkeletonWeaponCard(new Weapon(null, 1, 2, "Wicked knife"), 2);
    }

    @Override
    public String getName() {
        return "Valeera Sanguinar";
    }
}
