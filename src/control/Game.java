package control;

import board.BoardState;
import player.IPlayer;

public class Game {
	
	public void playGame(IPlayer p1, IPlayer p2, BoardState b) {
		int ply = 0;
		int moves = 4;
		int tieCnt = 0;
		b.printBoard();
		System.out.println(p1.getType() + " (" + p1.getSymbol() + ") player goes first");
		IPlayer[] players = {p1, p2};
		IPlayer curPlayer = null;
		
		//Main game loop
		while(!b.full()) {
			//Set current player
			curPlayer = players[ply];			
			
			//find and take next move
			System.out.println(curPlayer.getType() + " (" + curPlayer.getSymbol() + ") ACTION:");
			curPlayer.makeDecision(b);
			b.printBoard();
			
			System.out.println("");
			
			//Check for win conditions
			if(b.checkWinCondition()) {
				b.printResults();
				return;
			}
			if (moves == b.numDiscs('B') + b.numDiscs('W')) {
				tieCnt++;
			} else {
				tieCnt = 0;
			}
			if(tieCnt >= 2) {
				if(b.numDiscs('B') > b.numDiscs('W')) {
					b.setWinner('B');
					b.printResults();
				} else if (b.numDiscs('B') < b.numDiscs('W')){
					b.setWinner('W');
					b.printResults();
				} else {
					b.setWinner('S');
					b.printResults();
				}
				return;
			}
			
			moves = b.numDiscs('B') + b.numDiscs('W');
			
			//Switch players
			ply = (ply + 1) % 2;			
		}
	}
}
