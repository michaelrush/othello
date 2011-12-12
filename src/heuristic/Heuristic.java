package heuristic;

import java.util.ArrayList;

import board.BoardState;

public class Heuristic {
	
	public static int[] getMove(MMNode root) {
		if(root.getNext() == null) {
			return new int[]{-1,-1};
		}
		return root.getState().oneMoveDiff(root.getNext().getState());
	}
	
	public static int evalState(BoardState board, char turn, char s) {
		char opp = 'B';
		if (s == 'B') {
			opp = 'W';
		}
		return evalStateHelper(board, turn, s) - evalStateHelper(board, turn, opp);
	}
	
	private static int evalStateHelper(BoardState board, char turn, char s) {
		int eval = 0;
		
		char opp = 'B';
		if (s == 'B') {
			opp = 'W';
		}
		
		//Winning takes priority endgame
		if (board.getWinner() == s && board.numDiscs('W') + board.numDiscs('B') > 55) {
			return 10000;
		} 
		
		//Corners highly valued
		if (board.getState()[0][0] == s || (turn == s && board.legalMove(0,0,s))) {
			eval += 400;
		}
		if (board.getState()[7][7] == s || (turn == s && board.legalMove(7,7,s))) {
			eval += 400;
		}
		if (board.getState()[0][7] == s || (turn == s && board.legalMove(0,7,s))) {
			eval += 400;
		}
		if (board.getState()[7][0] == s || (turn == s && board.legalMove(7,0,s))) {
			eval += 400;
		}
		
		//Giving corners is bad
		if (board.getState()[0][1] == s && board.legalMove(0, 0, opp)) {
			eval += 200;
		}
		if (board.getState()[1][0] == s && board.legalMove(0, 0, opp)) {
			eval += 200;
		}
		if (board.getState()[1][1] == s && board.getState()[0][0] == '.') {
			eval -= 200;
		}
		if (board.getState()[6][7] == s && board.legalMove(7, 7, opp)) {
			eval += 200;
		}
		if (board.getState()[7][6] == s && board.legalMove(7, 7, opp)) {
			eval += 200;
		}
		if (board.getState()[6][6] == s && board.getState()[7][7] == '.') {
			eval -= 200;
		}
		if (board.getState()[1][7] == s && board.legalMove(0, 7, opp)) {
			eval += 200;
		}
		if (board.getState()[0][6] == s && board.legalMove(0, 7, opp)) {
			eval += 200;
		}
		if (board.getState()[1][6] == s && board.getState()[0][7] == '.') {
			eval -= 200;
		}
		if (board.getState()[7][1] == s && board.legalMove(7, 0, opp)) {
			eval += 200;
		}
		if (board.getState()[6][0] == s && board.legalMove(7, 0, opp)) {
			eval += 200;
		}
		if (board.getState()[6][1] == s && board.getState()[7][0] == '.') {
			eval -= 200;
		}
		
		//Edges valued
		for(int i = 0; i < board.getWidth(); i++) {
			if (board.getState()[i][0] == s) {
				eval += 20;
			}
			if (board.getState()[i][7] == s) {
				eval += 20;
			}
		}
		for(int i = 0; i < board.getHeight(); i++) {
			if (board.getState()[0][i] == s) {
				eval += 20;
			}
			if (board.getState()[7][i] == s) {
				eval += 20;
			}
		}
		
		//Having more pieces than opponent is values, especially late game
		if(board.numDiscs('W') + board.numDiscs('B') > 50) {
			eval += 20*(board.numDiscs(s) - board.numDiscs(opp));
		}
		
		//Minimize their moves mid to late game
		if (board.numDiscs('W') + board.numDiscs('B') > 30) {
			ArrayList<BoardState> children = board.getSuccessors(opp);
			if(children.size() == 0) {
				eval += 200;
			} else if (children.size() < 3) {
				eval += 100;
			} else {
				eval -= children.size()*25;
			}
			
		}
		
		//Try to have one piece in each col/row midgame
		if (board.numDiscs('W') + board.numDiscs('B') > 20 && board.numDiscs('W') + board.numDiscs('B') < 50) {
			for(int y = 0; y < board.getHeight(); y++) {
				int cnt = 0;
				for(int x = 0; x < board.getWidth(); x++) {
					if (board.getState()[x][y] == s) {
						cnt++;
					}
				}
				if (cnt > 0) {
					eval += 10;
				}
			}
			for(int x = 0; x < board.getWidth(); x++) {
				int cnt = 0;
				for(int y = 0; y < board.getHeight(); y++) {
					if (board.getState()[x][y] == s) {
						cnt++;
					}
				}
				if (cnt > 0) {
					eval += 10;
				}
			}
		}
		
		//Secured rows are valuable
		for(int y = 0; y < board.getHeight(); y+=7) {
			for(int x = 0; x < board.getWidth(); x++) {
				if (board.getState()[0][y] == s) {
					if(board.getState()[x][y] != s) {
						break;
					}
					eval += 20;
				}
			}
			for(int x = board.getWidth() - 1; x >= 0; x--) {
				if (board.getState()[7][y] == s) {
					if(board.getState()[x][y] != s) {
						break;
					}
					eval += 20;
				}
			}
		}
		
		//Secured cols are valuable
		for(int x = 0; x < board.getWidth(); x+=7) {
			for(int y = 0; y < board.getHeight(); y++) {
				if (board.getState()[x][0] == s) {
					if(board.getState()[x][y] != s) {
						break;
					}
					eval += 20;
				}
			}
			for(int y = board.getHeight() - 1; y >= 0; y--) {
				if (board.getState()[7][y] == s) {
					if(board.getState()[x][y] != s) {
						break;
					}
					eval += 20;
				}
			}
		}
		
		
		return eval;
	}
}
