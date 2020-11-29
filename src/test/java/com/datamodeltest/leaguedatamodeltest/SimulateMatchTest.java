package com.datamodeltest.leaguedatamodeltest;

import java.sql.Date;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;

import com.datamodel.gameplayconfig.GamePlayConfigAbstractFactory;
import com.datamodel.gameplayconfig.GamePlayConfigFactory;
import com.datamodel.leaguedatamodel.IGame;
import com.datamodel.leaguedatamodel.IGameSchedule;
import com.datamodel.leaguedatamodel.ILeague;
import com.datamodel.leaguedatamodel.ISimulateMatch;
import com.datamodel.leaguedatamodel.LeagueDataModelAbstractFactory;
import com.datamodel.leaguedatamodel.LeagueDataModelFactory;
import com.statemachine.IStateMachine;
import com.statemachine.StateMachineAbstractFactory;
import com.statemachine.StateMachineFactory;

public class SimulateMatchTest {

	static ILeague league;
	static IGame game;
	static IStateMachine stateMachine = null;
	static StateMachineAbstractFactory stateFactory = null;
	static LeagueDataModelAbstractFactory factory = null; 

	@BeforeClass
	public static void loadMockLeague() {
		GamePlayConfigAbstractFactory.setFactory(new GamePlayConfigFactory());
		LeagueDataModelAbstractFactory.setFactory(new LeagueDataModelFactoryTest());
		LeagueMock leagueMock = new LeagueMock();
		league = leagueMock.getLeague();		
		StateMachineAbstractFactory.setFactory(new StateMachineFactory());
		factory = LeagueDataModelAbstractFactory.instance();
		stateFactory = StateMachineAbstractFactory.instance();
		game = factory.createGame();
		game.addLeague(league);
		factory.createGameSchedule().scheduleRegularSeason(game, stateFactory.createStateMachine(null));
	}

	@Test
	public void simulateNHLMatch() {
		simulateMatchTest();
	}

	public void simulateMatchTest() {
		IGame game = factory.createGame();
		stateMachine = stateFactory.createStateMachine(null);
		IGameSchedule schedule = factory.createGameSchedule();
		List<IGameSchedule> matchSchedules = schedule.scheduleRegularSeason(game, stateMachine);
		String str = "2020-10-12";
		game.getLeagues().get(0).setCurrentDate(Date.valueOf(str));
		ISimulateMatch simulateMatch = factory.createSimulateMatch();
		for (IGameSchedule gameSchedule : matchSchedules) {
			Date curreDate = game.getLeagues().get(0).getCurrentDate();
			Date matchDate = gameSchedule.getMatchDate();
			if (curreDate.compareTo(matchDate) == 0) {
				simulateMatch.simulateMatchResult(gameSchedule, game, 0.5);
			}
		}
		schedule.schedulePlayoff(game, stateMachine);
	}

}
