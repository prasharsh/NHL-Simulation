package com.statemachinetest;

import com.datamodel.gameplayconfig.GamePlayConfigAbstractFactory;
import com.datamodel.gameplayconfig.GamePlayConfigFactory;
import com.datamodel.leaguedatamodel.IGame;
import com.datamodel.leaguedatamodel.ILeague;
import com.datamodel.leaguedatamodel.LeagueDataModelAbstractFactory;
import com.datamodeltest.leaguedatamodeltest.LeagueDataModelFactoryTest;
import com.statemachine.IStateMachine;
import com.statemachine.StateMachineAbstractFactory;
import com.statemachine.StateMachineFactory;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;

public class TrainingStateTest {

	static ILeague league;
	static IStateMachine stateMachine = null;
	static StateMachineAbstractFactory stateFactory = null;
	static LeagueDataModelAbstractFactory factory = null;

	@BeforeClass
	public static void loadMockLeague() {
		GamePlayConfigAbstractFactory.setFactory(new GamePlayConfigFactory());
		LeagueDataModelAbstractFactory.setFactory(new LeagueDataModelFactoryTest());
		ILeague league = StateMachineLeagueMock.getLeague();
		StateMachineAbstractFactory.setFactory(new StateMachineFactory());
		factory = LeagueDataModelAbstractFactory.instance();
		stateFactory = StateMachineAbstractFactory.instance();
		IGame game = factory.createGame();
		game.addLeague(league);
	}

	@Test
	public void entryTest() {
		stateFactory.createTrainingState().entry();
		assertNotNull(stateFactory);
	}

	@Test
	public void doTaskTest() {
		stateFactory.createTrainingState().doTask();
		assertNotNull(stateFactory);
	}


}