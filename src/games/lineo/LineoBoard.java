package games.lineo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import minimax.Board;
import minimax.GameState;
import util.GridMove;

public class LineoBoard implements Board<GridMove> {
	private int[][] board;
	private int size;
	private int toPlay;
	private int[] stones;
	private boolean lightRules; 
	
	public LineoBoard(int size, boolean lightRules) {
		this.size = size;
		this.lightRules = lightRules;
		board = new int[size][size];
		for (int i = 0; i < size; i++) Arrays.fill(board[i], -1);
		toPlay = 0;
		stones = new int[2];
	}

	@Override
	public GameState getGameState() {
		if (!generateMoves(toPlay).isEmpty()) return GameState.IN_PROGRESS;
		
		if (lightRules) return toPlay == 0 ? GameState.TWO_WIN : GameState.ONE_WIN;
		else {
			if (stones[0] > stones[1]) return GameState.ONE_WIN;
			else if (stones[1] > stones[0]) return GameState.TWO_WIN;
			else return GameState.DRAW;
		}
	}

	@Override
	public List<GridMove> generateMoves(int player) {
		List<GridMove> list = new ArrayList<>();
		int other = player^1;
		for (int x = 0; x < size; x++) {
			for (int y = 0; y < size; y++) {
				if (board[x][y] != -1) continue;
				//Check for blocked positions
				if (x > 0 && x < size-1 && board[x-1][y] == other && board[x+1][y] == other) continue;
				if (y > 0 && y < size-1 && board[x][y-1] == other && board[x][y+1] == other) continue;
				
				list.add(new GridMove(x, y));
			}
		}
		return list;
	}

	@Override
	public Board<GridMove> copy() {
		var copy = new LineoBoard(size, lightRules);
		copy.toPlay = this.toPlay;
		System.arraycopy(this.stones, 0, copy.stones, 0, 2);
		for (int i = 0; i < size; i++) System.arraycopy(this.board[i], 0, copy.board[i], 0, size);
		return copy;
	}

	@Override
	public Board<GridMove> move(GridMove move) {
		playMove(move);
		toPlay ^= 1;
		return this;
	}
	
	private void playMove(GridMove move) {
		int x = move.x();
		int y = move.y();
		board[x][y] = toPlay;
		stones[toPlay]++;

		int other = toPlay^1;
		//Capture
		if (x >= 2 && board[x-2][y] == toPlay && board[x-1][y] == (other)) {
			board[x-1][y] = -1;
			stones[other]--;
		}
		if (x <= size-3 && board[x+2][y] == toPlay && board[x+1][y] == (other)) {
			board[x+1][y] = -1;
			stones[other]--;
		}
		if (y >= 2 && board[x][y-2] == toPlay && board[x][y-1] == (other)) {
			board[x][y-1] = -1;
			stones[other]--;
		}
		if (y <= size-3 && board[x][y+2] == toPlay && board[x][y+1] == (other)) {
			board[x][y+1] = -1;
			stones[other]--;
		}
		//If next player has no play, play out the remaining moves
		if (!lightRules && generateMoves(other).isEmpty()) {
			var moves = generateMoves(toPlay);
			if (!moves.isEmpty()) playMove(moves.get(0));
		}
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
		sb.append("\n" + stones[0] + " / " + stones[1]);
		return sb.toString();
	}

}
