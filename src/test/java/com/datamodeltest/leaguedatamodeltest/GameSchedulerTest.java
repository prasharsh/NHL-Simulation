package com.datamodeltest.leaguedatamodeltest;

import static org.junit.Assert.assertEquals;

import java.sql.Date;

import org.junit.Test;

import com.datamodel.leaguedatamodel.Conference;
import com.datamodel.leaguedatamodel.Division;
import com.datamodel.leaguedatamodel.Game;
import com.datamodel.leaguedatamodel.GameSchedule;
import com.datamodel.leaguedatamodel.IConference;
import com.datamodel.leaguedatamodel.IDivision;
import com.datamodel.leaguedatamodel.IGameSchedule;
import com.datamodel.leaguedatamodel.ILeague;
import com.datamodel.leaguedatamodel.ITeam;
import com.datamodel.leaguedatamodel.League;
import com.datamodel.leaguedatamodel.Team;
import com.statemachine.StateMachine;

public class GameSchedulerTest {

	@Test
	public void scheduleRegularSeasonForLessTeamsTest() {
		scheduleRegularSeasonTest(2, 2, 2);
	}

	@Test
	public void scheduleRegularSeasonNHLTeamsTest() {
		scheduleRegularSeasonTest(2, 3, 5);
	}

	@Test
	public void scheduleRegularSeasonFor66TeamsTest() {
		scheduleRegularSeasonTest(2, 3, 11);
	}

	// @Test
	public void scheduleRegularSeasonTest(int conferenceSize, int divisionSize, int teamSize) {
		/*
		 * int conferenceSize = 2; int divisionSize =2; int teamSize = 6;
		 */
		StateMachine stateMachine = new StateMachine(null);
		Game game = MockGame.mockGame(conferenceSize, divisionSize, teamSize);
		IGameSchedule schedule = new GameSchedule();
		int totalGameScheduled = (conferenceSize * divisionSize * teamSize * 82);
		assertEquals(schedule.scheduleRegularSeason(game, stateMachine).size(), totalGameScheduled);
	}
}
