package games.nim;

import minimax.Game;

/**
 * Game starts with starting matches. Each turn each player may take anywhere from 1 to maxTake matches. Whoever removes the last match loses.
 * Trivial game for testing.
 *
 */
public class NimGame implements Game<NimBoard, Integer> {
	private int starting;
	private int maxTake;

	public NimGame(int starting, int maxTake) {
		this.starting = starting;
		this.maxTake = maxTake;
	}

	@Override
	public NimBoard initial() {
		return new NimBoard(starting, maxTake);
	}
}
