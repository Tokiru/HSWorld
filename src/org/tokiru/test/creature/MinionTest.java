package org.tokiru.test.creature;

import org.tokiru.core.board.BoardState;
import org.tokiru.core.card.Hand;
import org.tokiru.core.creature.Creature;
import org.tokiru.core.creature.MinionBuilder;
import org.tokiru.core.hero.Druid;
import org.tokiru.core.hero.Hero;
import org.tokiru.core.hero.Rogue;
import org.tokiru.core.player.SkeletalPlayer;
import org.tokiru.test.SkeletalTest;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tokiru.
 */
public class MinionTest extends SkeletalTest {

    public MinionTest() {
        super(
                new ArrayList<Creature>(){{
                    add(new MinionBuilder().setAttack(3).setHealth(2).getCreature());
                    add(new MinionBuilder().setAttack(3).setHealth(2).getCreature());
                }},
                new ArrayList<Creature>(){{
                    add(new MinionBuilder().setAttack(1).setHealth(100).getCreature());
                }}
        );
    }

    @Override
    public boolean run() {

        Creature myCreature = myCreatures.get(0);
        Creature enemyCreature = enemyCreatures.get(0);


        as.as(!myCreature.canAttack(enemyHero), "creature can't attack hero on first turn");
        as.as(!myCreature.canAttack(enemyCreature), "creature can't attack enemy creature on first turn");
        environment.endTurn(0);
        as.as(myCreature.canAttack(enemyHero), "creature can attack hero after first turn");
        as.as(myCreature.canAttack(enemyCreature), "creature can attack enemy creature after first turn");
        as.as(!myCreature.canAttack(myCreatures.get(1)), "creature can't attack friendly minions");
        as.as(!myCreature.canAttack(myHero), "creature can't attack friendly hero");
        environment.attackTurn(0, 1100, 0);
        as.as(myCreature.getHealth() == 2, "creature isn't hurt after attacking hero");
        as.as(!myCreature.canAttack(enemyCreature), "creature can't attack twice");
        environment.endTurn(0);
        environment.attackTurn(0, 1000, 0);
        as.as(myCreature.getHealth() == myCreature.getMaxHealth() - enemyCreature.getAttack(), "attacking creature deals damage");
        as.as(enemyCreature.getHealth() == enemyCreature.getMaxHealth() - myCreature.getAttack(), "defense creature takes damage");


        return finish();
    }

    @Override
    public String getName() {
        return "Minion test";
    }
}
