package g4dhl;

import g4db.IGameDB;
import g4db.GameDB;


public class HelloWorld {

	public static void main(String[] args)  {
		Game game = new Game();
		IGameDB gameDB = new GameDB();
		game.loadLeagues(gameDB);
		System.out.println(game.getLeagues().get(0).getLeagueName());
	}

}
