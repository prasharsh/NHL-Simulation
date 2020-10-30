package com.datamodeltest;
import java.sql.Date;

import com.datamodeltest.TimeConcept;
import org.junit.Test;

import com.datamodeltest.leaguedatamodel.Game;
import com.datamodeltest.leaguedatamodel.ILeague;
import com.datamodeltest.leaguedatamodel.League;


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
