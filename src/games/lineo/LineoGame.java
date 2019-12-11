package games.lineo;

import minimax.Game;
import util.GridMove;

/**
 * Game is on a nxn grids. Each players places a stone of their color anywhere empty.
 * When 2 stones are placed OXO vertically or horizontally, enemy stone on X is removed
 * and opponent can't place on the X anymore unless the stones get removed.
 * 
 * Normal rules:
 * When a player no longer has moves, the other places all stones they can place and game ends.
 * Whoever has the most stones on the final board wins.
 * 
 * Light rules:
 * When a player has no move left on their turn, they loose;
 *
 */
public class LineoGame implements Game<LineoBoard, GridMove> {
	private int size;
	private boolean lightRules; 

	public LineoGame(int size, boolean lightRules) {
		this.size = size;
		this.lightRules = lightRules;
	}

	public LineoGame(int size) {
		this(size, false);
	}

	@Override
	public LineoBoard initial() {
		return new LineoBoard(size, lightRules);
	}
}
