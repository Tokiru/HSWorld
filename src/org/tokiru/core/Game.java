package org.tokiru.core;

import org.tokiru.core.board.BoardState;
import org.tokiru.core.card.Card;
import org.tokiru.core.card.Deck;
import org.tokiru.core.card.Hand;
import org.tokiru.core.card.creature.Creature;
import org.tokiru.core.card.creature.MinionCard;
import org.tokiru.core.card.spell.SpellCard;
import org.tokiru.core.card.spell.neutral.Coin;
import org.tokiru.core.card.spell.weapon.WeaponCard;
import org.tokiru.core.event.EndTurnEvent;
import org.tokiru.core.event.EventManager;
import org.tokiru.core.event.SummonMinionEvent;
import org.tokiru.core.hero.Hero;
import org.tokiru.core.player.Player;
import org.tokiru.core.turn.AttackTurn;
import org.tokiru.core.turn.PlayCardTurn;
import org.tokiru.core.turn.Turn;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tokiru.
 */
public class Game {
    private List<Player> players;
    private State state;
    private BoardState boardState;

    public EventManager eventManager;

    public Game() {
        players = new ArrayList<>();
        state = State.PREPARATION;
        eventManager = new EventManager();
    }

    public void registerPlayer(Player player) {
        assert state == State.PREPARATION;
        assert players.size() < 2;
        players.add(player);
    }

    public void setUp() {
        players.get(0).setID(0);
        players.get(1).setID(1);

        assert state == State.PREPARATION;
        assert players.size() == 2;
        state = State.RUNNING;
        boardState = new BoardState();
        for (int playerID = 0; playerID < players.size(); playerID++) {
            boardState.setHero(players.get(playerID).getHero(), playerID);
            players.get(playerID).getHero().spawn(players.get(playerID), boardState, eventManager);
            boardState.setDeck(players.get(playerID).getDeck(), playerID);
        }

        //mulligan
        for (int playerID = 0; playerID < players.size(); playerID++) {
            Deck deck = boardState.getDeck(playerID);
            List<Card> mulliganCards = deck.mulliganPhase1(playerID + 3);
            List<Boolean> acceptedCards = players.get(playerID).mulligan(mulliganCards);
            List<Card> handList = deck.mulliganPhase2(acceptedCards);
            boardState.setHand(new Hand(handList), playerID);
        }
        boardState.getHand(1).accept(new Coin());
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
            boardState.refreshMana(currentPlayerID);

            Turn turn = new AttackTurn(-1, -1);
            while (turn.getType() != Turn.TurnType.END_TURN) {
                System.out.println(boardState);

                turn = players.get(currentPlayerID).turn(boardState);
                Turn.TurnType type = turn.getType();
                if (type == Turn.TurnType.ATTACK) {
                    AttackTurn attackTurn = (AttackTurn) turn;
                    Creature attackCreature = boardState.getByID(attackTurn.getFromID());
                    Creature defenseCreature = boardState.getByID(attackTurn.getToID());
                    if (attackCreature.canAttack(defenseCreature)) {
                        attackCreature.hit(defenseCreature);
                        if (attackCreature instanceof Hero) {
                            // do nothing
                        } else {
                            defenseCreature.hit(attackCreature);
                        }
                    } else {
                        System.out.println("minion can't attack!");
                    }

                    if (boardState.gameOver()) {
                        break;
                    }

                } else if (type == Turn.TurnType.PLAY_CARD) {
                    PlayCardTurn playCardTurn = (PlayCardTurn) turn;
                    Card cardToPlay = boardState.getHand(currentPlayerID).play(playCardTurn.getCardID());
                    if (boardState.spendMana(cardToPlay.getCost(), currentPlayerID)) {
                        if (cardToPlay.getType() == Card.CardType.MINION) {
                            MinionCard minionCard = (MinionCard) cardToPlay;
                            Creature creature = minionCard.getCreature();
                            boardState.addCreature(creature, currentPlayerID);
                            creature.spawn(players.get(currentPlayerID), boardState, eventManager);
                            eventManager.send(new SummonMinionEvent(creature));
                        } else if (cardToPlay.getType() == Card.CardType.SPELL) {
                            // ToDo use target
                            SpellCard spellCard = (SpellCard) cardToPlay;
                            spellCard.play(null, boardState, currentPlayerID, boardState.getSpellDamage(currentPlayerID));
                        } else if (cardToPlay.getType() == Card.CardType.WEAPON) {
                            WeaponCard weaponCard = (WeaponCard) cardToPlay;
                            weaponCard.play(boardState.getHero(currentPlayerID), boardState, eventManager);
                        }
                    } else {
                        System.out.println("not enough mana");
                    }
                } else if (type == Turn.TurnType.CONCEDE) {
                    boardState.getHero(currentPlayerID).destroy();
                    break;
                }
            }

            eventManager.send(new EndTurnEvent());

            currentPlayerID = 1 - currentPlayerID;
        }

        state = State.OVER;
    }

    public BoardState getBoardState() {
        return boardState;
    }

    private enum State {
        PREPARATION,
        RUNNING,
        OVER
    }
}
