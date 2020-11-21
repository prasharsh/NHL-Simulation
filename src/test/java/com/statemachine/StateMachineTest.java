package com.statemachine;
import static org.junit.Assert.assertNotNull;
import org.junit.Test;

public class StateMachineTest {

	IStateMachine stateMachine = StateMachine.getInstance(null);

	@Test
	public void getCurrentStateTest() {
		assertNotNull(stateMachine.getCurrentState());
	}

	@Test
	public void getCreateTeamTest() {
		assertNotNull(stateMachine.getCreateTeam());
	}

	@Test
	public void getLoadTeamTest() {
		assertNotNull(stateMachine.getLoadTeam());
	}

	@Test
	public void getPlayerChoiceTest() {
		assertNotNull(stateMachine.getPlayerChoice());
	}

	@Test
	public void getPlayerSimulationChoiceTest() {
		assertNotNull(stateMachine.getPlayerSimulationChoice());
	}

	@Test
	public void getAdvanceNextSeasonTest() {
		assertNotNull(stateMachine.getAdvanceNextSeason());
	}

	@Test
	public void getAdvanceTimeTest() {
		assertNotNull(stateMachine.getAdvanceTime());
	}

	@Test
	public void getAgingTest() {
		assertNotNull(stateMachine.getAging());
	}

	@Test
	public void getExecuteTradesTest() {
		assertNotNull(stateMachine.getExecuteTrades());
	}

	@Test
	public void getGeneratePlayoffScheduleTest() {
		assertNotNull(stateMachine.getGeneratePlayoffSchedule());
	}

	@Test
	public void getInitializeSeasonTest() {
		assertNotNull(stateMachine.getInitializeSeason());
	}

	@Test
	public void getInjuryCheckTest() {
		assertNotNull(stateMachine.getInjuryCheck());
	}

	@Test
	public void getPersistTest() {
		assertNotNull(stateMachine.getPersist());
	}

	@Test
	public void getSimulateGameTest() {
		assertNotNull(stateMachine.getSimulateGame());
	}

	@Test
	public void getTrainingTest() {} {
		assertNotNull(stateMachine.getTraining());
	}

	@Test
	public void getJsonImportTest() {} {
		assertNotNull(stateMachine.getJsonImport());
	}
}