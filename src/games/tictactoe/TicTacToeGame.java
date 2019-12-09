package games.tictactoe;

import minimax.Game;
import util.GridMove;

/**
 * Game is on a nxn grids, first to align n in a row wins. O is first player, X is second player.
 *
 */
public class TicTacToeGame implements Game<TicTacToeBoard, GridMove> {
	private int size;

	public TicTacToeGame(int size) {
		this.size = size;
	}

	@Override
	public TicTacToeBoard initial() {
		return new TicTacToeBoard(size);
	}
}
