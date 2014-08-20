package org.tokiru.test.creature;

import org.tokiru.core.creature.Creature;
import org.tokiru.core.creature.MinionBuilder;
import org.tokiru.test.SkeletalTest;

import java.util.ArrayList;

/**
 * Created by tokiru.
 */
public class TauntTest extends SkeletalTest {

    public TauntTest() {
        super(
                new ArrayList<Creature>() {{
                    add(new MinionBuilder().setAttack(4).setHealth(100).getCreature());
                }},
                new ArrayList<Creature>() {{
                    add(new MinionBuilder().setAttack(1).setHealth(4).taunt().getCreature());
                    add(new MinionBuilder().setAttack(2).setHealth(3).getCreature());
                    add(new MinionBuilder().setAttack(1).setHealth(4).taunt().getCreature());
                }}
        );
    }

    @Override
    public boolean run() {
        Creature myCreature = myCreatures.get(0);
        Creature taunt1 = enemyCreatures.get(0);
        Creature notTaunt = enemyCreatures.get(1);
        Creature taunt2 = enemyCreatures.get(2);

        environment.endTurn(0);
        as.as(myCreature.canAttack(taunt1) && myCreature.canAttack(taunt2), "creature can attack taunted minions");
        as.as(!myCreature.canAttack(notTaunt), "creature can't attack not taunted minion");
        as.as(!myCreature.canAttack(enemyHero), "creature can't attack enemy hero");
        environment.attackTurn(0, 1000, 0);
        environment.endTurn(0);
        as.as(!myCreature.canAttack(notTaunt), "creature can't attack not taunted minion");
        environment.attackTurn(0, 1001, 0);
        environment.endTurn(0);
        as.as(myCreature.canAttack(notTaunt) && myCreature.canAttack(enemyHero), "after taunts are down, creature can attack anything");

        return finish();
    }

    @Override
    public String getName() {
        return "Taunt test";
    }
}
