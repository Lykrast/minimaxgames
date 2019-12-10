package games.tron;

import minimax.ConsolePlayer;
import minimax.Minimax;
import util.GridMove;
import util.StandardEvaluations;

public class TronMain {

	public static void main(String[] args) {
		var player = new ConsolePlayer<TronBoard, GridMove>(GridMove::parse);
		var bot = new Minimax<TronBoard, GridMove>(36, StandardEvaluations::none);
		var game = new TronGame(6);
		game.matchVerbose(bot, bot);
		player.close();
	}

}
