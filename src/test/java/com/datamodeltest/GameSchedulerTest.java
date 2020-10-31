package com.datamodeltest;
import static org.junit.Assert.assertEquals;

import java.sql.Date;

import com.datamodel.GameScheduler;
import org.junit.Test;

import com.statemachine.StateMachine;

import com.datamodel.leaguedatamodel.Conference;
import com.datamodel.leaguedatamodel.Division;
import com.datamodel.leaguedatamodel.Game;
import com.datamodel.leaguedatamodel.IConference;
import com.datamodel.leaguedatamodel.IDivision;
import com.datamodel.leaguedatamodel.ILeague;
import com.datamodel.leaguedatamodel.ITeam;
import com.datamodel.leaguedatamodel.League;
import com.datamodel.leaguedatamodel.Team;


public class GameSchedulerTest {

	@Test
	public void scheduleRegularSeasonForLessTeamsTest(){
		scheduleRegularSeasonTest(2,2,2);
	}
	
	@Test
	public void scheduleRegularSeasonNHLTeamsTest(){
		scheduleRegularSeasonTest(2,3,5);
	}

	@Test
	public void scheduleRegularSeasonFor66TeamsTest(){
		scheduleRegularSeasonTest(2,3,11);
	}
	
	public void scheduleRegularSeasonTest(int conferenceSize, int divisionSize,int teamSize){
		StateMachine stateMachine = new StateMachine(null);
		Game game = MockGame.mockGame(conferenceSize, divisionSize, teamSize);
		GameScheduler scheduler = new GameScheduler();
		int totalGameScheduled = (conferenceSize* divisionSize* teamSize*82);
		assertEquals(scheduler.scheduleRegularSeason(game, stateMachine).size(), totalGameScheduled);
	}
}
