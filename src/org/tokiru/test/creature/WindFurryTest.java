package org.tokiru.test.creature;

import org.tokiru.core.creature.Creature;
import org.tokiru.core.creature.MinionBuilder;
import org.tokiru.test.SkeletalTest;

import java.util.ArrayList;

/**
 * Created by tokiru.
 */
public class WindFurryTest extends SkeletalTest {

    public WindFurryTest() {
        super(
                new ArrayList<Creature>(){{
                    add(new MinionBuilder().setAttack(3).setHealth(3).windFurry().getCreature());
                }},
                new ArrayList<Creature>(){{}}
        );
    }

    @Override
    public boolean run() {
        Creature creature = myCreatures.get(0);
        as.as(!creature.canAttack(enemyHero), "windfurry doesn't give charge");
        environment.endTurn(0);
        as.as(creature.canAttack(enemyHero), "creature can attack");
        environment.attackTurn(0, 1100, 0);
        as.as(creature.canAttack(enemyHero), "creature can attack second time");
        environment.attackTurn(0, 1100, 0);
        as.as(!creature.canAttack(enemyHero), "creature can't attack 3 times");

        return finish();
    }

    @Override
    public String getName() {
        return "Windfurry test";
    }
}
