package heuristic;

import java.util.ArrayList;

import control.OthelloTimer;

import player.IPlayer;

import board.BoardState;

public class MMNode {
	private BoardState _state;
	private MMNode _next;
	private int _eval;
	private char _symbol;
	
	public MMNode() {
		_state = new BoardState();
	}
	
	public MMNode(BoardState b, char s) {
		_state = b;
		_symbol = s;
	}
	
	/*
	 * Builds a minimax tree up to depth d with eval values
	 */
	public int evalTree(MMNode r, MMNode cur, char turn, IPlayer p, int alpha, int beta, int d, int maxd) {
		if(OthelloTimer.timeElapsed() > 10000 && maxd > 4) {
			return Integer.MIN_VALUE;
		}
		if(d > 0) {
			ArrayList<MMNode> children = cur.getChildren();
			
			
			//Pass
			int res = 0;
			if (children.size() == 0 || children.size() <= 1 && cur.getState().oneMoveDiff(children.get(0).getState())[0] == -1) {
				if (cur == r) {
					System.out.println("SETTING NEXT NULL");
					r.setNext(null);
				}
				return Heuristic.evalState(cur.getState(), turn, p.getSymbol());
			}
			
			//One move
			if(cur == r) {
				r.setNext(children.get(0));
				if(children.size() <= 1) {
					return -10000;
				}
			}
			
			if (turn == p.getSymbol()) { //Maximize
				for(int i = 0; i < children.size(); i++) {
					if(turn == 'B') {
						res = evalTree(r, children.get(i), 'W', p, alpha, beta, d-1, maxd);
						//return res; //no pruning
					} else if (turn == 'W') {
						res = evalTree(r, children.get(i), 'B', p, alpha, beta, d-1, maxd);
						//return res;//no pruning
					}
					if(res > alpha) {
						alpha = res;
						if(cur == r) {
							r.setNext(children.get(i));
						}
					}
					if (alpha >= beta) {
						return alpha;
					}
				}
				return alpha;
			}
			
			if (turn != p.getSymbol()) { //Minimize
				for(int i = 0; i < children.size(); i++) {
					if(turn == 'B') {
						res = evalTree(r, children.get(i), 'W', p, alpha, beta, d-1, maxd);
						//return res;//no pruning
					} else if (turn == 'W') {
						res = evalTree(r, children.get(i), 'B', p, alpha, beta, d-1, maxd);
						//return res;//no pruning
					}
					if(res < beta) {
						beta = res;
						if(cur == r) {
							r.setNext(children.get(i));
						}
					}
					if (beta <= alpha) {
						return beta;
					}
				}
				return beta;
			}
		}
		return Heuristic.evalState(cur.getState(), turn, p.getSymbol());
	}
	
	
	public ArrayList<MMNode> getChildren() {
		ArrayList<BoardState> a = null;
		if(_symbol == 'B') {
			a = _state.getSuccessors(_symbol);
		} else {
			a = _state.getSuccessors(_symbol);
		}
		ArrayList<MMNode> m = new ArrayList<MMNode>();
		for(int i = 0; i < a.size(); i++) {
			if(_symbol == 'B') {
				m.add(new MMNode(a.get(i), 'W'));
			} else {
				m.add(new MMNode(a.get(i), 'B'));
			}
		}
		return m;
	}
	
	public MMNode getNext() {
		return _next;
	}
	
	public BoardState getState() {
		return _state;
	}
	
	public void setNext(MMNode b) {
		_next = b;
	}
	
	public void setEval(int e) {
		_eval = e;
	}
	
	public int getEval() {
		return _eval;
	}
	
}
