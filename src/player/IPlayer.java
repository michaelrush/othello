package player;

import board.BoardState;

public interface IPlayer {
	public void makeDecision(BoardState b);
	public char getSymbol();
	public String getType();
}
