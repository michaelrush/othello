package player;

import control.OthelloTimer;
import heuristic.MMNode;
import board.BoardState;

public class PlayerCPU implements IPlayer {
	private char _symbol;
	private String _type;
	private MMNode _root;
	
	public PlayerCPU(char c) {
		_symbol = c;
		_type = "CPU";
	}
	
	public void makeDecision(BoardState b) {
		OthelloTimer.reset();
		MMNode next = _root;
		_root = new MMNode(b, _symbol);

		if(_root.getNext() != null) {
			next = _root.getNext();
		}
		
		System.out.println("Run time (4): " + OthelloTimer.roundTimeElapsed() + "(" + OthelloTimer.timeElapsed() + ")");
		
		
		int depth = 5;
		int maxDepth = 20;
		
		//Check time and exit if going over, take previous next value
		//while(end - start < 1600 && depth <= maxDepth) {
		while(OthelloTimer.timeElapsed() < 10000 && depth <= maxDepth) {
			OthelloTimer.resetRound();
			_root = new MMNode(b, _symbol);
			_root.evalTree(_root, _root, _symbol, this, -1000000, 1000000, depth, depth); //Minimax search with alpha beta pruning
			depth++;
			if(OthelloTimer.timeElapsed() > 10000) {
				System.out.println("Run time (" + (depth-1) + "): " + "Break" + "(" + OthelloTimer.timeElapsed() + ")");
				break;
			} else {
				next = _root.getNext();
				//end = System.currentTimeMillis();
				System.out.println("Run time (" + (depth-1) + "): " + OthelloTimer.roundTimeElapsed() + "(" + OthelloTimer.timeElapsed() + ")");
			}
		}
		
		int[] move = new int[2];
		move = _root.getState().oneMoveDiff(next.getState());
		b.makeMove(move[0], move[1], _symbol);
		System.out.println("CPU CHOSE: (" + move[0] + ", " + move[1] + ")");
	}
	
	public char getSymbol() {
		return _symbol;
	}
	
	public String getType() {
		return _type;
	}
}
