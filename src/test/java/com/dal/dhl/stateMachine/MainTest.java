package com.dal.dhl.stateMachine;

import org.junit.Assert;
import org.junit.Test;

import g4dhl.Game;
import g4dhl.GameDBMock;

public class MainTest {

	@Test
	public void isNullOrEmptyTest() {
		String str = "";
		Assert.assertTrue(str.isEmpty());
		str = "  ";
		Assert.assertTrue(str.trim().isEmpty());
		str = null;
		Assert.assertTrue(str == null);
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
