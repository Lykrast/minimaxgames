package games.nim;

import java.util.ArrayList;
import java.util.List;

import minimax.Board;
import minimax.GameState;

public class NimBoard implements Board<Integer> {
	private int remaining;
	private int maxTake;
	private int toPlay;
	
	public NimBoard(int starting, int maxTake) {
		remaining = starting;
		this.maxTake = maxTake;
		toPlay = 0;
	}

	@Override
	public GameState getGameState() {
		if (remaining > 0) return GameState.IN_PROGRESS;
		else if (toPlay == 0) return GameState.ONE_WIN;
		else return GameState.TWO_WIN;
	}

	@Override
	public List<Integer> generateMoves(int player) {
		if (remaining <= 0) return null;
		List<Integer> moves = new ArrayList<>();
		int max = Math.min(maxTake, remaining);
		for (int i = 1; i <= max; i++) moves.add(i);
		return moves;
	}

	@Override
	public Board<Integer> copy() {
		var copy = new NimBoard(remaining, maxTake);
		copy.toPlay = this.toPlay;
		return copy;
	}

	@Override
	public Board<Integer> move(Integer move) {
		remaining -= move;
		toPlay ^= 1;
		return this;
	}
	
	@Override
	public String toString() {
		if (remaining <= 0) return "<Empty>";
		return "|".repeat(remaining) + " (" + remaining + ")";
	}

}
