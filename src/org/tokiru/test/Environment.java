package org.tokiru.test;

import org.tokiru.core.Game;
import org.tokiru.core.board.BoardState;
import org.tokiru.core.player.Player;
import org.tokiru.core.player.SkeletalPlayer;
import org.tokiru.core.turn.AttackTurn;
import org.tokiru.core.turn.EndTurn;
import org.tokiru.core.turn.PlayCardTurn;
import org.tokiru.core.turn.Turn;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tokiru.
 */
public class Environment {
    //private BoardState boardState;
    private List<BoardState> boardStateList;

    public Environment() {
        Game game = new Game();

        game.registerPlayer(new SkeletalPlayer());
        game.registerPlayer(new SkeletalPlayer());
        game.setUp();

        boardStateList = new ArrayList<>();
        boardStateList.add(game.getBoardState());
        addMana(getLastBoardState());
    }

    public Environment(BoardState boardState) {
        boardStateList = new ArrayList<>();
        boardStateList.add(boardState);
        addMana(getLastBoardState());
    }

    private void addMana(BoardState boardState) {
        boardState.addManaCrystal(10, 0);
        boardState.addManaCrystal(10, 1);
        boardState.refreshMana(0);
        boardState.refreshMana(1);
    }

    public Environment turn(Turn turn, int playerID) {
        boardStateList.add(Game.makeTurn(getLastBoardState(), turn, playerID));
        return this;
    }

    public Environment endTurn(int playerID) {
        turn(new EndTurn(getLastBoardState().getPlayer(playerID)), playerID);
        return this;
    }

    public Environment attackTurn(int fromID, int toID, int playerID) {
        turn(new AttackTurn(fromID, toID), playerID);
        return this;
    }

    public Environment playTurn(int cardID, int targetID, int playerID) {
        turn(new PlayCardTurn(cardID, targetID, -1), playerID);
        return this;
    }

    public Environment playTurn(int cardID, int targetID, int position, int playerID) {
        turn(new PlayCardTurn(cardID, targetID, position), playerID);
        return this;
    }


    public BoardState getBoardState() {
        return getLastBoardState();
    }

    private BoardState getLastBoardState() {
        return boardStateList.get(boardStateList.size() - 1);
    }
}
