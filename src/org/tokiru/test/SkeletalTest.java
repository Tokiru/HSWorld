package org.tokiru.test;

import org.tokiru.core.board.BoardState;
import org.tokiru.core.card.Hand;
import org.tokiru.core.creature.Creature;
import org.tokiru.core.creature.MinionBuilder;
import org.tokiru.core.hero.Druid;
import org.tokiru.core.hero.Hero;
import org.tokiru.core.hero.Rogue;
import org.tokiru.core.player.SkeletalPlayer;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tokiru.
 */
public abstract class SkeletalTest implements Test {

    protected Environment environment;
    protected Creature myHero;
    protected Creature enemyHero;
    protected List<Creature> enemyCreatures;
    protected List<Creature> myCreatures;
    protected Assert as;
    protected BoardState initialBoardState;
    protected Hand myHand;
    protected Hand enemyHand;

    public SkeletalTest(List<Creature> creatures1, List<Creature> creatures2, Hand hand1, Hand hand2) {
        myCreatures = creatures1;
        enemyCreatures = creatures2;
        myHand = hand1;
        enemyHand = hand2;
    }

    public SkeletalTest(List<Creature> creatures1, List<Creature> creatures2) {
        this(creatures1, creatures2, new Hand(new Druid()), new Hand(new Rogue()));
    }

    public SkeletalTest() {
        this(new ArrayList<>(), new ArrayList<>());
    }

    @Override
    public void init() {
        initialBoardState = new BoardState(new SkeletalPlayer(), new SkeletalPlayer(), new Hero(new Druid()), new Hero(new Rogue()),
                myHand, enemyHand, myCreatures, enemyCreatures);
        environment = new Environment(initialBoardState);
        myHero = initialBoardState.getHero(0);
        enemyHero = initialBoardState.getHero(1);
        as = new Assert();
    }

    protected boolean finish() {
        boolean result = as.getResult();
        if (!result) {
            System.out.println(environment.getBoardState());
        }
        return result;
    }
}
