package board;

import java.util.ArrayList;
import java.util.Scanner;
import common.*;

public class BoardState {
	private char[][] _board;
  private int _width = Constants.tilesPerRow;
  private int _height = Constants.tilesPerColumn;
	private char _winner;
	
	public BoardState() {	
		_board = new char[_width][_height];
		for(int i = 0; i < _width; i++) {
			for(int j = 0; j < _height; j++) {
				_board[i][j] = '.';
			}
		}
		System.out.println("Board setup?: ");
		System.out.println("\t(1) BW/BW: ");
		System.out.println("\t(2) WB/WB: ");
		System.out.print("Input: ");
		Scanner sc = new Scanner(System.in);
		int in = sc.nextInt();
		if(in == 1) {
			_board[3][3] = 'B';
			_board[4][4] = 'B';
			_board[3][4] = 'W';
			_board[4][3] = 'W';
		} else {
			_board[3][3] = 'W';
			_board[4][4] = 'W';
			_board[3][4] = 'B';
			_board[4][3] = 'B';
		}
	}
	
	public BoardState(char[][] b) {
		_board = new char[_width][_height];
		for(int i = 0; i < _width; i++) {
			for(int j = 0; j < _height; j++) {
				_board[i][j] = b[i][j];
			}
		}
		
	}
	

	public boolean full() {
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				if (_board[i][j] == '.') {
					return false;
				}
			}
		}
		return true;
	}
	
	public boolean legalMove(int x, int y, char c) {
		if(_board[x][y] != '.') {
			return false;
		}
		int[] position = new int[2];
		for (int i = 0; i <= 7; i++) {
			position = hasSandwich(x,y,c,i);
			if (position[0] != -1) {
				return true;
			}
		}
		
		return false;
	}
	
	private int[] hasSandwich(int x, int y, char c, int dir) {
		char other = c == 'B'? 'W':'B';
		int[] position = {-1,-1};
		
		//above
		if (dir == 0 && (y-1 >=0) && _board[x][y-1] == other) {
			for (int i = y - 2; i >= 0; i--) {
				if (_board[x][i] == '.') {
					break;
				} else if (_board[x][i] == c) {
					position[0] = x;
					position[1] = i;
					break;
				}
			
			}
			//below
		} else if (dir == 1 && (y+1 <=7) && _board[x][y+1] == other) {
			for (int i = y + 2; i <=7; i++) {
				if (_board[x][i] == '.') {
					break;
				} else if (_board[x][i] == c) {
					position[0] = x;
					position[1] = i;
					break;
				}
			}
			//right
		} else if (dir == 2 && (x+1 <=7) && _board[x+1][y] == other) {
			for (int i = x + 2; i <= 7; i++) {
				if (_board[i][y] == '.') {
					break;
				} else if (_board[i][y] == c) {
					position[0] = i;
					position[1] = y;
					break;
				}
			}
			//left
		} else if (dir == 3 && (x-1 >=0) && _board[x-1][y] == other) {
			for (int i = x - 2; i >= 0; i--) {
				if (_board[i][y] == '.') {
					break;
				} else if (_board[i][y] == c) {
					position[0] = i;
					position[1] = y;
					break;
				}
			}
			//ftop
		} else if (dir == 4 && (x-1 >=0 && y-1 >=0) && _board[x-1][y-1] == other) {
			for (int i = 2; i <= 7; i++) {
				if (x-i < 0 || y-i < 0) {
					break;
				} else if (_board[x-i][y-i] == '.') {
					break;
				} else if (_board[x-i][y-i] == c) {
					position[0] = x-i;
					position[1] = y-i;
					break;
				}
			}
			//fbot
		} else if (dir == 5 && (x+1 <= 7 && y+1 <= 7) && _board[x+1][y+1] == other) {
			for (int i = 2; i <= 7; i++) {
				if (x+i > 7 || y+i > 7) {
					break;
				} else if (_board[x+i][y+i] == '.') {
					break;
				} else if (_board[x+i][y+i] == c) {
					position[0] = x+i;
					position[1] = y+i;
					break;
				}
			}
			//btop
		} else if (dir == 6 && (x+1 <= 7 && y-1 >= 0) && _board[x+1][y-1] == other) {
			for (int i = 2; i <= 7; i++) {
				if (x+i > 7 || y-i < 0) {
					break;
				} else if (_board[x+i][y-i] == '.') {
					break;
				} else if (_board[x+i][y-i] == c) {
					position[0] = x+i;
					position[1] = y-i;
					break;
				}
			}
			//bbot
		} else if (dir == 7 && (x-1 >= 0 && y+1 <= 7) && _board[x-1][y+1] == other) {
			for (int i = 2; i <= 7; i++) {
				if (x-i < 0 || y+i > 7) {
					break;
				} else if (_board[x-i][y+i] == '.') {
					break;
				} else if (_board[x-i][y+i] == c) {
					position[0] = x-i;
					position[1] = y+i;
					break;
				}
			}
		}
		return position;
	}
	
	public void makeMove(int x, int y, char c) {
		if(x == -1 && y == -1) {
			return;
		}
		for (int i = 0; i <= 7; i++) {
			int[] pos = new int[2];
			pos = hasSandwich(x, y, c, i);
			if (pos[0] != -1) {
				changePieces(x, y, pos, c, i);
			}
		}
	}
	
	private void changePieces(int x, int y, int[] pos, char c, int dir) {
		char other = c == 'B'? 'W':'B';
		_board[x][y] = c;
		
		//above
		if (dir == 0) {
			for (int i = y - 1; i >= 0; i--) {
				if (_board[x][i] == other) {
					_board[x][i] = c;
				} else if (_board[x][i] == c) {
					break;
				}
			
			}
			//below
		} else if (dir == 1) {
			for (int i = y + 1; i <=7; i++) {
				if (_board[x][i] == other) {
					_board[x][i] = c;
				} else if (_board[x][i] == c) {
					break;
				}
			}
			//right
		} else if (dir == 2) {
			for (int i = x + 1; i <= 7; i++) {
				if (_board[i][y] == other) {
					_board[i][y] = c;
				} else if (_board[i][y] == c) {
					break;
				}
			}
			//left
		} else if (dir == 3) {
			for (int i = x - 1; i >= 0; i--) {
				if (_board[i][y] == other) {
					_board[i][y] = c;
				} else if (_board[i][y] == c) {
					break;
				}
			}
			//ftop
		} else if (dir == 4) {
			for (int i = 1; i <= 7; i++) {
				if (x-i < 0 || y-i < 0) {
					break;
				} else if (_board[x-i][y-i] == other) {
					_board[x-i][y-i] = c;
				} else if (_board[x-i][y-i] == c) {
					break;
				}
			}
			//fbot
		} else if (dir == 5) {
			for (int i = 1; i <= 7; i++) {
				if (x+i > 7 || y+i > 7) {
					break;
				} else if (_board[x+i][y+i] == other) {
					_board[x+i][y+i] = c;
				} else if (_board[x+i][y+i] == c) {
					break;
				}
			}
			//btop
		} else if (dir == 6) {
			for (int i = 1; i <= 7; i++) {
				if (x+i > 7 || y-i < 0) {
					break;
				} else if (_board[x+i][y-i] == other) {
					_board[x+i][y-i] = c;
				} else if (_board[x+i][y-i] == c) {
					break;
				}
			}
			//bbot
		} else if (dir == 7) {
			for (int i = 1; i <= 7; i++) {
				if (x-i < 0 || y+i > 7) {
					break;
				} else if (_board[x-i][y+i] == other) {
					_board[x-i][y+i] = c;
				} else if (_board[x-i][y+i] == c) {
					break;
				}
			}
		}
	}

	
	public void printBoard() {
		System.out.print("  ");
		for (int i = 0; i < 8; i++) {
			System.out.print(i + " ");
		}
		System.out.println("");
		for (int i = 0; i < 8; i++) {
			System.out.print(i + " ");
			for (int j = 0; j < 8; j++) {
				System.out.print(_board[j][i] + " ");
			}
			System.out.println("");
		}
	}
	
	public int[] oneMoveDiff(BoardState b) {
		for(int i = 0; i < _width; i++) {
			for(int j = 0; j < _height; j++) {
				if ((_board[i][j] == '.' && b.getState()[i][j] != '.') ||
						(_board[i][j] != '.' && b.getState()[i][j] == '.')) {
					int[] move = new int[2];
					move[0] = i;
					move[1] = j;
					return move;
				}
			}
		}
		return new int[]{-1, -1};
	}
	
	public ArrayList<BoardState> getSuccessors(char c) {
		ArrayList<BoardState> tb = new ArrayList<BoardState>();
		for(int i = 0; i < _width; i++) {
			for(int j = 0; j < _height; j++) {
				if (legalMove(i,j,c)) {
					BoardState bs = new BoardState(_board);
					bs.makeMove(i,j,c);
					tb.add(bs);
				}
			}
		}
		if(tb.isEmpty()) {
			tb.add(new BoardState(_board));
		}
		return tb;
	}
	
	/*
	 * Returns true if the game has terminated
	 */
	public Boolean checkWinCondition() {
		//or have all and board is not full
		if(full()) {
			int whiteLead = numDiscs('W') - numDiscs('B');
			if(whiteLead > 0) {
				_winner = 'W';
			} else if (whiteLead < 0) {
				_winner = 'B';
			} else {
				_winner = 'S';
			}
			return true;
		}
		return false;
	}
	
	public int numDiscs(char c) {
		int cnt = 0;
		for(int i = 0; i < _width; i++) {
			for(int j = 0; j < _height; j++) {
				if(c == _board[i][j]) {
					cnt++;
				}
			}
		}
		return cnt;
	}
	
	public void printResults() {
		if(_winner == 'W' || _winner == 'B') {
			System.out.println("Player '" + _winner + "' Wins!");
		}
		if(_winner == 'S') {
			System.out.println("Cats Game!");
		}
		if(_winner == '.') {
			System.out.println("Game is still in progress");
		}
	}
	
	public int getWidth() {
		return _width;
	}
	
	public int getHeight() {
		return _height;
	}
	
	public char getWinner() {
		return _winner;
	}
	
	public void setWinner(char c) {
		_winner = c;
	}
	
	public char[][] getState() {
		return _board;		
	}
	
	public char getSymbol(int x, int y) {
		return _board[x][y];		
	}
}
