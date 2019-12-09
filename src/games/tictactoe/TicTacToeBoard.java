package games.tictactoe;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import minimax.Board;
import minimax.GameState;
import util.GridMove;

public class TicTacToeBoard implements Board<GridMove> {
	private int[][] board;
	private int size;
	private int toPlay;
	
	public TicTacToeBoard(int size) {
		this.size = size;
		board = new int[size][size];
		for (int i = 0; i < size; i++) Arrays.fill(board[i], -1);
		toPlay = 0;
	}

	@Override
	public GameState getGameState() {
		boolean full = true;
		//Lines and full board
		for (int x = 0; x < size; x++) {
			int val = board[x][0];
			if (val == -1) {
				full = false;
				continue;
			}
			boolean line = true;
			for (int y = 1; y < size; y++) {
				if (board[x][y] == -1) {
					full = false;
					line = false;
					break;
				}
				else if (board[x][y] != val) {
					line = false;
					if (!full) break;
				}
			}
			if (line) return val == 0 ? GameState.ONE_WIN : GameState.TWO_WIN;
		}
		//Columns
		for (int y = 0; y < size; y++) {
			int val = board[0][y];
			if (val == -1) continue;
			boolean col = true;
			for (int x = 1; x < size; x++) {
				if (board[x][y] != val) {
					col = false;
					break;
				}
			}
			if (col) return val == 0 ? GameState.ONE_WIN : GameState.TWO_WIN;
		}
		//Diagonal one
		int val = board[0][0];
		if (val != -1) {
			boolean diag = true;
			for (int i = 1; i < size; i++) {
				if (board[i][i] != val) {
					diag = false;
					break;
				}
			}
			if (diag) return val == 0 ? GameState.ONE_WIN : GameState.TWO_WIN;
		}
		//Diagonal two
		val = board[0][size-1];
		if (val != -1) {
			boolean diag = true;
			for (int i = 1; i < size; i++) {
				if (board[i][size-i-1] != val) {
					diag = false;
					break;
				}
			}
			if (diag) return val == 0 ? GameState.ONE_WIN : GameState.TWO_WIN;
		}
		return full ? GameState.DRAW : GameState.IN_PROGRESS;
	}

	@Override
	public List<GridMove> generateMoves(int player) {
		List<GridMove> list = new ArrayList<>();
		for (int x = 0; x < size; x++) {
			for (int y = 0; y < size; y++) {
				if (board[x][y] == -1) list.add(new GridMove(x, y));
			}
		}
		return list;
	}

	@Override
	public Board<GridMove> copy() {
		var copy = new TicTacToeBoard(size);
		copy.toPlay = this.toPlay;
		for (int i = 0; i < size; i++) System.arraycopy(this.board[i], 0, copy.board[i], 0, size);
		return copy;
	}

	@Override
	public Board<GridMove> move(GridMove move) {
		board[move.x()][move.y()] = toPlay;
		toPlay ^= 1;
		return this;
	}

	@Override
	public String toString() {
		var sb = new StringBuilder();
		String separator = '\n' + ("-+".repeat(size).substring(0, size*2-1)) + '\n';
		for (int x = 0; x < size; x++) {
			for (int y = 0; y < size; y++) {
				if (board[x][y] == 0) sb.append('O');
				else if (board[x][y] == 1) sb.append('X');
				else sb.append(' ');
				if (y < size-1) sb.append('|');
			}
			if (x < size-1) sb.append(separator);
		}
		return sb.toString();
	}

}
