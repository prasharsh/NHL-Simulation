package com.datamodeltest.leaguedatamodeltest;
import static org.junit.Assert.assertEquals;
import org.junit.Test;
import com.datamodel.leaguedatamodel.Game;
import com.datamodel.leaguedatamodel.GameSchedule;
import com.datamodel.leaguedatamodel.IGameSchedule;
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

	public void scheduleRegularSeasonTest(int conferenceSize, int divisionSize, int teamSize) {
		StateMachine stateMachine = new StateMachine(null);
		Game game = MockGame.mockGame(conferenceSize, divisionSize, teamSize);
		IGameSchedule schedule = new GameSchedule();
		int totalGameScheduled = (conferenceSize * divisionSize * teamSize * 82);
		assertEquals(schedule.scheduleRegularSeason(game, stateMachine).size(), totalGameScheduled);
	}
}
