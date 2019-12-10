package games.tron;

import minimax.Game;
import util.GridMove;

/**
 * Game is on a nxn grids; players start in opposite corners.
 * On their turn, a player may move one step orthogonally, leaving a stone behind. Players can't move through stones.
 * When a player should play but has no move left, they loose.
 *
 */
public class TronGame implements Game<TronBoard, GridMove> {
	private int size;

	public TronGame(int size) {
		this.size = size;
	}

	@Override
	public TronBoard initial() {
		return new TronBoard(size);
	}
}
