package util;

import minimax.Board;

public class StandardEvaluations {
	private StandardEvaluations() {}
	
	public static <B extends Board<?>> int none(B board, int player) {
		return 0;
	}
	
	public static <B extends Board<?>> int numberMoves(B board, int player) {
		return board.generateMoves(player).size() - board.generateMoves(player ^ 1).size();
	}
}
