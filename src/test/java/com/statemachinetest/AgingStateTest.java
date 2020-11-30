package com.statemachinetest;

import com.datamodel.gameplayconfig.GamePlayConfigAbstractFactory;
import com.datamodel.gameplayconfig.GamePlayConfigFactory;
import com.datamodel.leaguedatamodel.IGame;
import com.datamodel.leaguedatamodel.ILeague;
import com.datamodel.leaguedatamodel.LeagueDataModelAbstractFactory;
import com.datamodeltest.leaguedatamodeltest.LeagueDataModelFactoryTest;
import com.inputoutputmodel.InputOutputModelAbstractFactory;
import com.inputoutputmodel.InputOutputModelFactory;
import com.statemachine.IStateMachine;
import com.statemachine.StateMachineAbstractFactory;
import com.statemachine.StateMachineFactory;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;

public class AgingStateTest {

	static ILeague league;
	static IStateMachine stateMachine = null;
	static StateMachineAbstractFactory stateFactory = null;
	static LeagueDataModelAbstractFactory factory = null;

	@BeforeClass
	public static void loadMockLeague() {
		GamePlayConfigAbstractFactory.setFactory(new GamePlayConfigFactory());
		InputOutputModelAbstractFactory.setFactory(new InputOutputModelFactory());
		LeagueDataModelAbstractFactory.setFactory(new LeagueDataModelFactoryTest());
		ILeague league = StateMachineLeagueMock.getLeague();
		StateMachineAbstractFactory.setFactory(new StateMachineFactory());
		factory = LeagueDataModelAbstractFactory.instance();
		stateFactory = StateMachineAbstractFactory.instance();
		IGame game = factory.createGame();
		league.setSeasonToSimulate(1);
		game.addLeague(league);
		factory.createGameSchedule().scheduleRegularSeason(game, stateFactory.createStateMachine(null));
	}

	@Test
	public void entryTest() {
		stateFactory.createAgingState().entry();
		assertNotNull(stateFactory);
	}

	@Test
	public void doTaskTest() {
		stateFactory.createAgingState().doTask();
		assertNotNull(stateFactory);
	}

}