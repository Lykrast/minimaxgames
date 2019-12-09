package minimax;

import java.util.Scanner;
import java.util.function.Function;

public class ConsolePlayer<B extends Board<M>, M> implements Player<B, M> {
	private Scanner scanner;
	private Function<String, M> parser;
	
	public ConsolePlayer(Function<String, M> parser) {
		this.parser = parser;
		scanner = new Scanner(System.in);
	}
	
	public void close() {
		scanner.close();
	}

	@Override
	public M move(B b, int player) {
        var possible = b.generateMoves(player);
        M move = null;
        do {
            move = parser.apply(scanner.next());
            if (!possible.contains(move)) System.err.println("Mouvement illégal du joueur : " + move);
        } while (!possible.contains(move));
		return move;
	}
	
	@Override
	public String toString() {
		return "Console";
	}

}
