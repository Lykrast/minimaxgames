package games.nim;

import minimax.ConsolePlayer;
import minimax.Minimax;
import util.StandardEvaluations;

public class NimMain {

	public static void main(String[] args) {
		var player = new ConsolePlayer<NimBoard, Integer>(Integer::parseInt);
		var bot = new Minimax<NimBoard, Integer>(10, StandardEvaluations::none);
		var game = new NimGame(10, 3);
		game.matchVerbose(player, bot);
		player.close();
	}

}
