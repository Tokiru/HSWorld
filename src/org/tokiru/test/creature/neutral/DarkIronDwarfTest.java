package org.tokiru.test.creature.neutral;

import org.tokiru.core.card.Card;
import org.tokiru.core.card.Hand;
import org.tokiru.core.card.creature.MinionCard;
import org.tokiru.core.card.creature.neutral.MinionFactory;
import org.tokiru.core.creature.Creature;
import org.tokiru.core.creature.neutral.DarkIronDwarf;
import org.tokiru.core.hero.Druid;
import org.tokiru.core.hero.Rogue;
import org.tokiru.test.SkeletalTest;

import java.util.ArrayList;

/**
 * Created by tokiru.
 */
public class DarkIronDwarfTest extends SkeletalTest {
    public DarkIronDwarfTest() {
        super(
                new ArrayList<Creature>() {{}},
                new ArrayList<Creature>() {{}},
                new Hand(
                        new ArrayList<Card>() {{
                            add(MinionFactory.darkIronDwarfCard());
                            add(MinionFactory.darkIronDwarfCard());
                        }},
                        new Druid()
                ),
                new Hand(
                        new ArrayList<Card>() {{
                            add(MinionFactory.bloodfenRaptorCard());
                            add(MinionFactory.bloodfenRaptorCard());
                        }},
                        new Rogue()
                )
        );
    }

    @Override
    public boolean run() {

        as.as(myHand.get(0).canPlay(null, environment.getBoardState()), "can be played without target if board is empty");
        environment.playTurn(0, -1, 0).endTurn(0);
        environment.playTurn(0, -1, 1);
        as.as(!myHand.get(0).canPlay(enemyHero, environment.getBoardState()), "can't be played on enemy hero");
        as.as(!myHand.get(0).canPlay(myHero, environment.getBoardState()), "can't be played on my hero");
        as.as(!myHand.get(0).canPlay(null, environment.getBoardState()), "can't be played without target if board is not empty");
        as.as(myHand.get(0).canPlay(environment.getBoardState().getByID(0), environment.getBoardState()), "can be played on friedly minion");
        as.as(myHand.get(0).canPlay(environment.getBoardState().getByID(1000), environment.getBoardState()), "can be played on enemy minion");
        int myCreatureAttack = environment.getBoardState().getByID(0).getAttack();
        int myCreatureHealth = environment.getBoardState().getByID(0).getHealth();
        as.as(myCreatureAttack == 4 && myCreatureHealth == 4, "Stats are correct");
        environment.playTurn(0, 0, 0);
        as.as(environment.getBoardState().getByID(0).getAttack() == 2 + myCreatureAttack, "playing dwarf on minion give it +2 attack");
        environment.endTurn(0);
        as.as(environment.getBoardState().getByID(0).getAttack() == myCreatureAttack, "at the end of the turn buff is removed");

        return finish();
    }

    @Override
    public String getName() {
        return "Dark Iron Dwarf test";
    }
}
