package player;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringTokenizer;

import board.BoardState;

public class PlayerPC implements IPlayer {
	private char _symbol;
	private String _type;
	
	public PlayerPC(char c) {
		_symbol = c;
		_type = "PC";
	}
	
	public void makeDecision(BoardState b) {	
		ArrayList<BoardState> children = b.getSuccessors(_symbol);
		int[] toka = new int[2];
		boolean done = false;
		while(!done) {
			try {
				System.out.println("Available Moves: ");
				for(int i = 0; i < children.size(); i++) {
					System.out.println(children.get(i).oneMoveDiff(b)[0] + ", " + children.get(i).oneMoveDiff(b)[1]) ;
				}
				
				toka = handleInput();
				for(int i = 0; i < children.size(); i++) {
					if (children.get(i).oneMoveDiff(b)[0] == toka[0] && children.get(i).oneMoveDiff(b)[1] == toka[1]) {
						b.makeMove(toka[0], toka[1], _symbol);
						done = true;
					}
				}
				if (!done) {
					throw new IllegalArgumentException("Illegal Move");
				}
			} catch (Exception e) {
				System.out.println(e);
			}
		}	
	}
	
	private int[] handleInput() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Input Move (x,y): ");
		String in = sc.nextLine();
		
		StringTokenizer stok = new StringTokenizer(in);
		int[] toka = new int[2];
		int tokcnt = 0;
		while(stok.hasMoreTokens()) {
			String tok = stok.nextToken();
			int n = Integer.parseInt(tok);
			if (tokcnt > 1) {
				throw new IllegalArgumentException("Too many arguments: Expected 2");
			} else {
				toka[tokcnt++] = n;
			}
		}
		if ( tokcnt != 2) {
			throw new IllegalArgumentException("Too few of arguments: Expected 2");
		}
		return toka;
	}
	
	public char getSymbol() {
		return _symbol;
	}
	
	public String getType() {
		return _type;
	}
}
