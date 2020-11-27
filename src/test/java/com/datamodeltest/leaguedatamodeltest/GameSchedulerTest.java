package com.datamodeltest.leaguedatamodeltest;
import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.datamodel.leaguedatamodel.LeagueDataModelFactory;
import com.datamodel.leaguedatamodel.Game;
import com.datamodel.leaguedatamodel.LeagueDataModelAbstractFactory;
import com.datamodel.leaguedatamodel.IGameSchedule;
import com.statemachine.IStateMachine;
import com.statemachine.StateMachine;

public class GameSchedulerTest {
/*
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

	public void scheduleRegularSeasonTest(int conferenceSize, int divisionSize, int teamSize) {
		IStateMachine stateMachine = StateMachine.getInstance(null);
		Game game = MockGame.mockGame(conferenceSize, divisionSize, teamSize);
		LeagueDataModelAbstractFactory dataModelFactory = LeagueDataModelFactory.getNewInstance();
		IGameSchedule schedule = dataModelFactory.createGameSchedule();
		int totalGameScheduled = (conferenceSize * divisionSize * teamSize * 82);
		assertEquals(schedule.scheduleRegularSeason(game, stateMachine).size(), totalGameScheduled);
	}*/
}
