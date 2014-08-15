package org.tokiru.core.weapon;

import org.tokiru.core.Hero;
import org.tokiru.core.card.creature.Creature;

/**
 * Created by tokiru.
 */
public class TruesilverChampion extends Weapon {

    public TruesilverChampion() {
        this(null);
    }

    public TruesilverChampion(Hero owner) {
        super(owner, 4, 2, "Truesilver Champion");
    }

    @Override
    public void use(Creature target) {
        owner.takeHeal(2);
        super.use(target);
    }
}
