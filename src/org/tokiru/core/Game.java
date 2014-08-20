package org.tokiru.core;

import org.tokiru.core.board.BoardState;
import org.tokiru.core.card.Card;
import org.tokiru.core.card.Hand;
import org.tokiru.core.card.creature.MinionCard;
import org.tokiru.core.card.secret.SecretCard;
import org.tokiru.core.card.spell.SpellCard;
import org.tokiru.core.card.spell.neutral.Coin;
import org.tokiru.core.card.weapon.WeaponCard;
import org.tokiru.core.creature.Creature;
import org.tokiru.core.creature.MinionBuilder;
import org.tokiru.core.deck.Deck;
import org.tokiru.core.event.AttackEvent;
import org.tokiru.core.event.CardPlayedEvent;
import org.tokiru.core.event.EndTurnEvent;
import org.tokiru.core.event.Event;
import org.tokiru.core.hero.Hero;
import org.tokiru.core.hero.HeroClass;
import org.tokiru.core.hero.Hunter;
import org.tokiru.core.player.Player;
import org.tokiru.core.player.SkeletalPlayer;
import org.tokiru.core.turn.AttackTurn;
import org.tokiru.core.turn.PlayCardTurn;
import org.tokiru.core.turn.Turn;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tokiru.
 */
public class Game {
    protected List<Player> players;
    protected State state;
    protected BoardState boardState;

    //public boardState.getEventManager() boardState.getEventManager();

    public Game() {
        players = new ArrayList<>();
        state = State.PREPARATION;        
    }

    public Game(BoardState boardState, HeroClass heroClass1, HeroClass heroClass2) {
        this.boardState = boardState;
        this.players = new ArrayList<Player>();
        players.add(new SkeletalPlayer(heroClass1));
        players.add(new SkeletalPlayer(heroClass2));
        this.state = State.RUNNING;        
    }

    public static BoardState makeTurn(BoardState boardState, Turn turn, int playerID) {
        Game game = new Game(boardState, new Hunter(), new Hunter());
        game.turn(playerID, turn);
        return boardState;
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
            boardState.setPlayer(players.get(playerID));
            boardState.setHero(players.get(playerID).getHero(), playerID);
            players.get(playerID).getHero().spawn(players.get(playerID), boardState, null);
            boardState.setDeck(players.get(playerID).getDeck(), playerID);
        }

        //mulligan
        for (int playerID = 0; playerID < players.size(); playerID++) {
            Deck deck = boardState.getDeck(playerID);
            List<Card> mulliganCards = deck.mulliganPhase1(playerID + 3);
            List<Boolean> acceptedCards = players.get(playerID).mulligan(mulliganCards);
            List<Card> handList = deck.mulliganPhase2(acceptedCards);
            boardState.setHand(new Hand(handList, boardState.getHero(playerID).getHeroClass()), playerID);
            boardState.getEventManager().subscribe(boardState.getHand(playerID), Event.EventType.END_TURN);
        }
        boardState.getHand(1).accept(new Coin());


        boardState.addCreature(new MinionBuilder("Wisp", 1, 1).getCreature(), null, 1);
        boardState.addCreature(new MinionBuilder("Wisp", 1, 1).getCreature(), null, 1);
        boardState.addCreature(new MinionBuilder("Wisp", 1, 1).getCreature(), null, 1);
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
                if (newCard instanceof SpellCard) {
                    SpellCard spellCard = (SpellCard) newCard;
                    spellCard.init(boardState);
                }
            }

            // add mana crystal
            boardState.addManaCrystal(currentPlayerID);
            boardState.refreshMana(currentPlayerID);

            Turn turn = new AttackTurn(-1, -1);
            while (turn.getType() != Turn.TurnType.END_TURN) {
                System.out.println(boardState);

                turn = players.get(currentPlayerID).turn(boardState);
                turn(currentPlayerID, turn);
            }

            boardState.getEventManager().send(new EndTurnEvent());
            boardState.endTurn();
            currentPlayerID = 1 - currentPlayerID;
        }

        state = State.OVER;
    }

    private void turn(int currentPlayerID, Turn turn) {

        Turn.TurnType type = turn.getType();
        if (type == Turn.TurnType.ATTACK) {
            AttackTurn attackTurn = (AttackTurn) turn;
            Creature attackCreature = boardState.getByID(attackTurn.getFromID());
            Creature defenseCreature = boardState.getByID(attackTurn.getToID());

            boardState.getEventManager().send(new AttackEvent(attackCreature, defenseCreature));

            if (attackCreature.canAttack(defenseCreature)) {
                attackCreature.hit(defenseCreature);
                //noinspection StatementWithEmptyBody
                if (attackCreature instanceof Hero || defenseCreature instanceof Hero) {
                    // do nothing
                } else {
                    defenseCreature.hit(attackCreature);
                }
            } else {
                System.out.println("minion can't attack!");
            }
        } else if (type == Turn.TurnType.PLAY_CARD) {
            PlayCardTurn playCardTurn = (PlayCardTurn) turn;
            Creature target;
            if (playCardTurn.getTargetID() == -1) {
                target = null;
            } else {
                target = boardState.getByID(playCardTurn.getTargetID());
            }
            Card cardToPlay = boardState.getHand(currentPlayerID).play(playCardTurn.getCardID(), target, boardState);
            if (cardToPlay == null) {
                System.out.println("card can't be played on this target");
            } else {
                boardState.getEventManager().send(new CardPlayedEvent(cardToPlay));
                if (boardState.spendMana(cardToPlay.getCost(), currentPlayerID)) {
                    if (cardToPlay.getType() == Card.CardType.MINION) {
                        MinionCard minionCard = (MinionCard) cardToPlay;
                        Creature creature = minionCard.getCreature();
                        if (playCardTurn.getPosition() == -1) {
                            boardState.addCreature(creature, target, currentPlayerID);
                        } else {
                            boardState.addCreature(creature, target, playCardTurn.getPosition(), currentPlayerID);
                        }
                    } else if (cardToPlay.getType() == Card.CardType.SPELL) {
                        SpellCard spellCard = (SpellCard) cardToPlay;
                        spellCard.play(target, boardState, currentPlayerID, boardState.getSpellDamage(currentPlayerID));
                    } else if (cardToPlay.getType() == Card.CardType.WEAPON) {
                        WeaponCard weaponCard = (WeaponCard) cardToPlay;
                        weaponCard.play(boardState.getHero(currentPlayerID), boardState);
                    } else if (cardToPlay.getType() == Card.CardType.SECRET) {
                        SecretCard secretCard = (SecretCard) cardToPlay;
                        secretCard.play(players.get(currentPlayerID), boardState);
                    } else {
                        throw new UnsupportedOperationException();
                    }
                } else {
                    System.out.println("not enough mana");
                }
            }
        } else if (type == Turn.TurnType.CONCEDE) {
            boardState.getHero(currentPlayerID).destroy();
        } else if (type == Turn.TurnType.END_TURN) {
            boardState.getEventManager().send(new EndTurnEvent());
            boardState.endTurn();
            boardState.refreshMana(currentPlayerID);
        }
    }
    
    public BoardState getBoardState() {
        return boardState;
    }

    protected enum State {
        PREPARATION,
        RUNNING,
        OVER
    }
}
