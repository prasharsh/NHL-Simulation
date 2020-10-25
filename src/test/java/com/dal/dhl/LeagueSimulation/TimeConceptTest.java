package com.dal.dhl.LeagueSimulation;
import java.sql.Date;

import org.junit.Test;

import g4dhl.Game;
import g4dhl.ILeague;
import g4dhl.League;


public class TimeConceptTest {

    @Test
    public void getNextDateTest(){
       Game game = mockGame();
       TimeConcept timeConcept = new TimeConcept();
    }

	private Game mockGame() {
		Game game = new Game();
		ILeague league = new League();
		league.setLeagueName("mock");
		String str="2020-09-30";
		league.setCurrentDate(Date.valueOf(str));
		game.addLeague(league);
		return game;
	}
}
