package games.tron;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import minimax.Board;
import minimax.GameState;
import util.GridMove;

public class TronBoard implements Board<GridMove> {
	private int[][] board;
	private int size;
	private int toPlay;
	private int p1x, p1y, p2x, p2y;
	
	public TronBoard(int size) {
		this.size = size;
		board = new int[size][size];
		for (int i = 0; i < size; i++) Arrays.fill(board[i], -1);
		toPlay = 0;
		p1x = 0;
		p1y = 0;
		p2x = size - 1;
		p2y = size - 1;
		board[p1x][p1y] = 0;
		board[p2x][p2y] = 1;
	}

	@Override
	public GameState getGameState() {
		//A player looses when it should play but has no moves
		if (generateMoves(toPlay).isEmpty()) return toPlay == 0 ? GameState.TWO_WIN : GameState.ONE_WIN;
		else return GameState.IN_PROGRESS;
	}

	@Override
	public List<GridMove> generateMoves(int player) {
		List<GridMove> list = new ArrayList<>();
		int x = player == 0 ? p1x : p2x;
		int y = player == 0 ? p1y : p2y;
		
		if (x > 0 && board[x-1][y] == -1) list.add(new GridMove(x-1,y));
		if (x < size-1 && board[x+1][y] == -1) list.add(new GridMove(x+1,y));
		if (y > 0 && board[x][y-1] == -1) list.add(new GridMove(x,y-1));
		if (y < size-1 && board[x][y+1] == -1) list.add(new GridMove(x,y+1));
		
		return list;
	}

	@Override
	public Board<GridMove> copy() {
		var copy = new TronBoard(size);
		copy.toPlay = this.toPlay;
		copy.p1x = this.p1x;
		copy.p1y = this.p1y;
		copy.p2x = this.p2x;
		copy.p2y = this.p2y;
		for (int i = 0; i < size; i++) System.arraycopy(this.board[i], 0, copy.board[i], 0, size);
		return copy;
	}

	@Override
	public Board<GridMove> move(GridMove move) {
		board[move.x()][move.y()] = toPlay;
		if (toPlay == 0) {
			board[p1x][p1y] += 2;
			p1x = move.x();
			p1y = move.y();
		}
		else {
			board[p2x][p2y] += 2;
			p2x = move.x();
			p2y = move.y();
		}
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
				else if (board[x][y] == 2) sb.append('o');
				else if (board[x][y] == 3) sb.append('x');
				else sb.append(' ');
				if (y < size-1) sb.append('|');
			}
			if (x < size-1) sb.append(separator);
		}
		return sb.toString();
	}

}
