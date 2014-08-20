package org.tokiru.test.creature;

import org.tokiru.core.card.creature.neutral.MinionFactory;
import org.tokiru.core.creature.Creature;
import org.tokiru.core.creature.MinionBuilder;
import org.tokiru.test.SkeletalTest;

import java.util.ArrayList;

/**
 * Created by tokiru.
 */
public class DivineShieldTest extends SkeletalTest {

    public DivineShieldTest() {
        super(
                new ArrayList<Creature>(){{
                    add(new MinionBuilder().setAttack(3).setHealth(100).divineShield().getCreature());
                }},
                new ArrayList<Creature>(){{
                    add(MinionFactory.chillWindYetiCard().getCreature());
                }});
    }

    @Override
    public boolean run() {
        Creature myCreature = myCreatures.get(0);
        Creature enemyCreature = enemyCreatures.get(0);

        environment.endTurn(0);
        environment.attackTurn(0, 1000, 0);
        environment.endTurn(0);
        as.as(myCreature.getHealth() == myCreature.getMaxHealth(), "divine shield creature doesn't take damage");
        environment.attackTurn(0, 1000, 0);
        as.as(myCreature.getHealth() == myCreature.getMaxHealth() - enemyCreature.getAttack(), "when divine creature takes damage," +
                "it loses shield");

        return finish();
    }

    @Override
    public String getName() {
        return "Divine shield test";
    }
}
