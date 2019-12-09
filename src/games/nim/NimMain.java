package games.nim;

import minimax.ConsolePlayer;
import minimax.Minimax;

public class NimMain {

	public static void main(String[] args) {
		var player = new ConsolePlayer<NimBoard, Integer>(Integer::parseInt);
		var bot = new Minimax<NimBoard, Integer>(6, (b, i) -> 0);
		var game = new NimGame(6, 3);
		game.matchVerbose(player, bot);
		player.close();
	}

}
