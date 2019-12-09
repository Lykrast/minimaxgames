package games.tictactoe;

import minimax.ConsolePlayer;
import minimax.Minimax;
import util.GridMove;
import util.StandardEvaluations;

public class TicTacToeMain {

	public static void main(String[] args) {
		var player = new ConsolePlayer<TicTacToeBoard, GridMove>(GridMove::parse);
		var bot = new Minimax<TicTacToeBoard, GridMove>(9, StandardEvaluations::none);
		var game = new TicTacToeGame(3);
		game.matchVerbose(bot, bot);
		player.close();
	}

}
