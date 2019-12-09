package minimax;

public interface Game<B extends Board<M>, M> {
	B initial();
	
	@SuppressWarnings("unchecked")
	default B match(Player<B, M> first, Player<B, M> second) {
		var b = initial();
		int player = 0;
		while (b.getGameState() == GameState.IN_PROGRESS) {
			var move = player == 0 ? first.move(b, 0) : second.move(b, 1);
			b = (B) b.move(move);
			player ^= 1;
		}
		
		return b;
	}
	
	@SuppressWarnings("unchecked")
	default B matchVerbose(Player<B, M> first, Player<B, M> second) {
		var b = initial();
		int player = 0;
        int moves = 0;
        long totaltF = 0, totaltS = 0;
		while (b.getGameState() == GameState.IN_PROGRESS) {
            moves++;
            System.out.println(b);
            System.out.println("Tour de P" + (player+1) + " (" + (player == 0 ? first : second) + ") - move " + moves);
            
            long t = -System.currentTimeMillis();
			var move = player == 0 ? first.move(b, 0) : second.move(b, 1);
            t += System.currentTimeMillis();
            if (player == 0) totaltF += t;
            else totaltS += t;
            
			System.out.println(move + " (" + t + " ms)");
			b = (B) b.move(move);
			player ^= 1;
		}

        System.out.println(b);
        var state = b.getGameState();
        if (state == GameState.DRAW) System.out.println("Egalité!");
        else if (state == GameState.ONE_WIN) System.out.println("Victoire de P1 (" + first + ")");
        else if (state == GameState.TWO_WIN) System.out.println("Victoire de P2 (" + second + ")");
        System.out.println("Temps total P1 (" + first + ") : " + (totaltF/1000.0) + " s (" + totaltF + " ms)");
        System.out.println("Temps total P2 (" + second + ") : " + (totaltS/1000.0) + " s (" + totaltS + " ms)");
        
		return b;
	}
}
