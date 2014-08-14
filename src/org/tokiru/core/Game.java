package org.tokiru.core;

import org.tokiru.core.Board.BoardState;
import org.tokiru.core.card.*;
import org.tokiru.core.card.creature.Creature;
import org.tokiru.core.card.creature.MinionCard;
import org.tokiru.core.player.Player;
import org.tokiru.core.turn.AttackTurn;
import org.tokiru.core.turn.PlayCardTurn;
import org.tokiru.core.turn.Turn;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tokiru.
 */
public class Game {
    public Game() {
        players = new ArrayList<>();
        state = State.PREPARATION;
    }

    public void registerPlayer(Player player) {
        assert state == State.PREPARATION;
        assert players.size() < 2;
        players.add(player);
    }

    public void setUp() {
        assert state == State.PREPARATION;
        assert players.size() == 2;
        state = State.RUNNING;
        boardState = new BoardState();
        for (int playerID = 0; playerID < players.size(); playerID++) {
            boardState.setHero(players.get(playerID).getHero(), playerID);
            boardState.setDeck(players.get(playerID).getDeck(), playerID);
            //mulligan
            Deck deck = boardState.getDeck(playerID);
            List<Card> mulliganCards = deck.mulliganPhase1(playerID + 3);
            List<Boolean> acceptedCards = players.get(playerID).mulligan(mulliganCards);
            List<Card> handList = deck.mulliganPhase2(acceptedCards);
            boardState.setHand(new Hand(handList), playerID);
        }
    }

    public void begin() {
        assert state == State.RUNNING;

        int currentPlayerID = 0;
        while (!boardState.gameOver()) {
            System.out.println(currentPlayerID + " turn!");

            // draw card
            Card newCard = boardState.getDeck(currentPlayerID).dealCard();
            if (newCard == null) {
                boardState.dealFartigueDamage(currentPlayerID);
            } else {
                boardState.getHand(currentPlayerID).accept(newCard);
            }

            // add mana crystal
            boardState.addManaCrystal(currentPlayerID);


            Turn turn = new AttackTurn(-1, -1);
            while (turn.getType() != Turn.TurnType.END_TURN) {
                turn = players.get(currentPlayerID).turn(boardState);
                Turn.TurnType type = turn.getType();
                if (type == Turn.TurnType.ATTACK) {
                    AttackTurn attackTurn = (AttackTurn) turn;
                    Creature attackCreature = boardState.getByID(attackTurn.getFromID());
                    Creature defenseCreature = boardState.getByID(attackTurn.getToID());
                    attackCreature.hit(defenseCreature);
                    defenseCreature.hit(attackCreature);

                    if (boardState.gameOver()) {
                        break;
                    }

                } else if (type == Turn.TurnType.PLAY_CARD) {
                    PlayCardTurn playCardTurn = (PlayCardTurn) turn;
                    Card cardToPlay = boardState.getHand(currentPlayerID).play(playCardTurn.getCardID());
                    if (cardToPlay.getType() == Card.CardType.MINION) {
                        MinionCard minionCard = (MinionCard) cardToPlay;
                        Creature creature = minionCard.getCreature();
                        boardState.addCreature(creature, currentPlayerID);
                        creature.spawn(boardState);
                    } else {
                        throw new NotImplementedException();
                    }
                }

                System.out.println(boardState);
            }
            currentPlayerID = 1 - currentPlayerID;
        }

        state = State.OVER;
    }

    private enum State {
        PREPARATION,
        RUNNING,
        OVER
    }

    public BoardState getBoardState() {
        return boardState;
    }

    private List<Player> players;
    private State state;

    private BoardState boardState;
}
