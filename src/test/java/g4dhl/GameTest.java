package g4dhl;

import java.util.ArrayList;

import org.junit.Assert;
import org.junit.Test;

public class GameTest {

	@Test
	public void setLeaguesTest() {
		Game game = new Game();
		ArrayList<ILeague> leagues = new ArrayList<>();
		game.setLeagues(leagues);
		Assert.assertEquals(leagues, game.getLeagues());
	}

	@Test
	public void addLeagueTest() {
		Game game = new Game();
		ILeague league = new League();
		game.addLeague(league);
		Assert.assertEquals(league, game.getLeague(0));
	}
}
