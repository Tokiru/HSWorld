package org.tokiru.test.creature.neutral;

import org.tokiru.core.card.Card;
import org.tokiru.core.card.Hand;
import org.tokiru.core.card.creature.MinionCard;
import org.tokiru.core.card.creature.neutral.MinionFactory;
import org.tokiru.core.creature.Creature;
import org.tokiru.core.creature.neutral.DefenderOfArgus;
import org.tokiru.core.hero.Druid;
import org.tokiru.core.hero.Rogue;
import org.tokiru.test.SkeletalTest;
import org.tokiru.test.Test;

import java.util.ArrayList;

/**
 * Created by tokiru.
 */
public class DefenderOfArgusTest extends SkeletalTest {

    public DefenderOfArgusTest() {
        super(
                new ArrayList<Creature>(){{

                }},
                new ArrayList<Creature>(){{

                }},
                new Hand(
                        new ArrayList<Card>(
                        ){{
                            add(MinionFactory.defenderOfArgusCard());
                            add(MinionFactory.defenderOfArgusCard());
                            add(MinionFactory.boulderfistOgreCard());
                            add(MinionFactory.boulderfistOgreCard());
                        }},
                        new Druid()
                ),
                new Hand(
                        new ArrayList<Card>(){{}},
                        new Rogue()
                )
        );
    }


    @Override
    public boolean run() {
        MinionCard card = (MinionCard) myHand.get(0);
        as.as(card.getCreature().getAttack() == 2 && card.getCreature().getHealth() == 3 && card.getCost() == 4, "Stats are correct");
        environment.playTurn(2, -1, 0).endTurn(0);
        Creature creature = environment.getBoardState().getByID(0);
        int creatureAttack = creature.getAttack();
        int creatureHealth = creature.getHealth();
        as.as(((MinionCard) myHand.get(0)).getCreature().canTarget(null, environment.getBoardState()), "can be played without target");
        as.as(!((MinionCard) myHand.get(0)).getCreature().canTarget(environment.getBoardState().getByID(0), environment.getBoardState()), "can't be played on minion");
        as.as(!((MinionCard) myHand.get(0)).getCreature().canTarget(enemyHero, environment.getBoardState()), "can't be played on hero");
        environment.playTurn(0, -1, 0);
        as.as(creature.isTaunt(), "Gives taunt to minion to the left");
        as.as(creature.getAttack() == creatureAttack + 1 && creature.getHealth() == creatureHealth + 1, "gives +1/+1 to the minion on the left");
        environment.endTurn(0);
        as.as(environment.getBoardState().getByID(0).isTaunt(), "Taunt still there");
        as.as(environment.getBoardState().getByID(0).canAttack(enemyHero), "can attack");
        creature = environment.getBoardState().getByID(1);
        creatureAttack = creature.getAttack();
        creatureHealth = creature.getHealth();
        environment.playTurn(0, -1, 1, 0);
        as.as(creature.isTaunt(), "Gives taunt to minion to the right");
        as.as(creature.getAttack() == creatureAttack + 1 && creature.getHealth() == creatureHealth + 1, "Gives +1/+1 to minion on the right");

        return finish();
    }

    @Override
    public String getName() {
        return "Defender of Argus test";
    }
}
