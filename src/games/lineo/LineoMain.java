package games.lineo;

import minimax.ConsolePlayer;
import minimax.Minimax;
import util.GridMove;
import util.StandardEvaluations;

public class LineoMain {

	public static void main(String[] args) {
		var player = new ConsolePlayer<LineoBoard, GridMove>(GridMove::parse);
		var bot = new Minimax<LineoBoard, GridMove>(4, StandardEvaluations::numberMoves);
		var game = new LineoGame(8, false);
		game.matchVerbose(bot, bot);
		player.close();
	}

}
