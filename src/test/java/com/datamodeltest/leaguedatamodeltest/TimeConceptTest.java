package com.datamodeltest.leaguedatamodeltest;

import com.datamodel.leaguedatamodel.Game;
import com.datamodel.leaguedatamodel.ILeague;
import com.datamodel.leaguedatamodel.League;
import com.datamodel.leaguedatamodel.TimeConcept;
import org.junit.Test;

import java.sql.Date;

import static org.junit.Assert.assertEquals;

public class TimeConceptTest {

	@Test
	public void getNextDateTest() {
		Game game = mockGame();
		TimeConcept timeConcept = new TimeConcept();
		assertEquals(timeConcept.getNextDate(game.getLeagues().get(0).getCurrentDate()), Date.valueOf("2020-10-01"));
	}

	private Game mockGame() {
		Game game = new Game();
		ILeague league = new League();
		league.setLeagueName("mock");
		String str = "2020-09-30";
		league.setCurrentDate(Date.valueOf(str));
		game.addLeague(league);
		return game;
	}
}
