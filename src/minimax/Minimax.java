package minimax;

import java.util.function.ToIntBiFunction;

public class Minimax<B extends Board<M>, M> implements Player<B, M> {
    private int maxDepth;
    private String name;
    private ToIntBiFunction<B, Integer> evaluation;

    public Minimax(int maxDepth, ToIntBiFunction<B, Integer> evaluation, String name) {
        this.maxDepth = maxDepth;
        this.evaluation = evaluation;
        this.name = name;
    }

    public Minimax(int maxDepth, ToIntBiFunction<B, Integer> evaluation) {
    	this(maxDepth, evaluation, "");
    }

    @Override
	public M move(B b, int player) {
        return move(b, player, player, maxDepth, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }
    
    private int lastscore = 0;
    public M move(B b, int realPlayer, int curPlayer, int depth, int alpha, int beta) {
        var moves = b.generateMoves(curPlayer);
        if (moves == null) return null;
        M picked = null;
        int pscore = 0;
        boolean max = curPlayer == realPlayer;
        for (var m : moves) {
            //Compute
            @SuppressWarnings("unchecked")
			var bc = (B)b.copy().move(m);
            int score;
            if (depth == 0) score = evaluate(bc, realPlayer, depth);
            else {
                var found = move(bc, realPlayer, curPlayer ^ 1, depth - 1, alpha, beta);
                if (found == null) score = evaluate(bc, realPlayer, depth);
                else score = lastscore;
            }
            //Pick
            if (picked == null) {
                picked = m;
                pscore = score;
                if (max) alpha = score;
                else beta = score;
            }
            else {
                if (max) {
                    if (score > pscore) {
                        picked = m;
                        pscore = score;
                    }
                    //beta cut
                    if (pscore >= beta) break;
                    if (pscore > alpha) alpha = pscore;
                }
                else {
                    if (score < pscore) {
                        picked = m;
                        pscore = score;
                    }
                    //alpha cut
                    if (alpha >= pscore) break;
                    if (pscore < beta) beta = pscore;
                }
            }
        }

        lastscore = pscore;
        return picked;
    }

    public int evaluate(B b, int player, int remainingDepth) {
    	var state = b.getGameState();
        if (state != GameState.IN_PROGRESS) {
            //Game end
            if (state == GameState.DRAW) return 0;
            else {
                int score = 1000000 + 100000 * remainingDepth;
                return ((state == GameState.ONE_WIN && player == 0) || (state == GameState.TWO_WIN && player == 1)) ? score : -score;
            }
        }
        else return evaluation.applyAsInt(b, player);
    }

    @Override
    public String toString() {
        return name.isEmpty() ? "Depth " + maxDepth : name + " Depth " + maxDepth;
    }
}
