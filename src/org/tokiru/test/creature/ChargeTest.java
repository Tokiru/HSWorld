package org.tokiru.test.creature;

import org.tokiru.core.creature.Creature;
import org.tokiru.core.creature.MinionBuilder;
import org.tokiru.test.SkeletalTest;

import java.util.ArrayList;

/**
 * Created by tokiru.
 */
public class ChargeTest extends SkeletalTest {

    public ChargeTest() {
        super(
                new ArrayList<Creature>(){{
                    add(new MinionBuilder().setAttack(3).setHealth(3).getCreature());
                    add(new MinionBuilder().setAttack(3).setHealth(3).charge().getCreature());
                }},
                new ArrayList<Creature>(){{
                    add(new MinionBuilder().setAttack(1).setHealth(100).getCreature());
                }}
        );
    }
    @Override
    public boolean run() {
        Creature charge = myCreatures.get(1);
        Creature notCharge = myCreatures.get(0);
        Creature enemyCreature = enemyCreatures.get(0);

        as.as(!notCharge.canAttack(enemyCreature), "creature without charge can't attack on first turn");
        as.as(charge.canAttack(enemyCreature), "charger can attack enemy creature");
        as.as(charge.canAttack(enemyHero), "charger can attack enemy creature");
        return finish();
    }

    @Override
    public String getName() {
        return "Charge test";
    }
}
