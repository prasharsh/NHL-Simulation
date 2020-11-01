
package com.datamodeltest;

import org.junit.Assert;
import org.junit.Test;

import com.datamodel.Main;
import com.datamodel.leaguedatamodel.Game;
import com.datamodeltest.leaguedatamodeltest.GameDBMock;

public class MainTest {

	@Test
	public void isNullOrEmptyTest() {
		String str = "";
		Assert.assertTrue(Main.isNullOrEmpty(str));
		str = "  ";
		Assert.assertTrue(Main.isNullOrEmpty(str));
		str = null;
		Assert.assertTrue(Main.isNullOrEmpty(str));
	}

	@Test
	public void loadLeaguesDataFromDB() {
		Game game = new Game();
		GameDBMock gameDB = new GameDBMock();
		game.saveToDb(gameDB);
		game.loadFromDB(gameDB);
		Assert.assertTrue("No leagues Saved to DB", game.getLeagues().size() > 0);
	}
}