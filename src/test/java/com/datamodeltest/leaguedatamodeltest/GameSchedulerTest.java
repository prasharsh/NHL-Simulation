package com.datamodeltest.leaguedatamodeltest;
import static org.junit.Assert.assertEquals;

import org.junit.BeforeClass;
import org.junit.Test;

import com.datamodel.leaguedatamodel.LeagueDataModelFactory;
import com.datamodel.gameplayconfig.GamePlayConfigAbstractFactory;
import com.datamodel.gameplayconfig.GamePlayConfigFactory;
import com.datamodel.leaguedatamodel.Game;
import com.datamodel.leaguedatamodel.IConference;
import com.datamodel.leaguedatamodel.IDivision;
import com.datamodel.leaguedatamodel.IGame;
import com.datamodel.leaguedatamodel.LeagueDataModelAbstractFactory;
import com.datamodel.leaguedatamodel.IGameSchedule;
import com.datamodel.leaguedatamodel.ILeague;
import com.datamodel.leaguedatamodel.ITeam;
import com.statemachine.IStateMachine;
import com.statemachine.StateMachine;
import com.statemachine.StateMachineAbstractFactory;
import com.statemachine.StateMachineFactory;

public class GameSchedulerTest {

	static IGame game = null;
	static ILeague league;
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
	public void scheduleRegularSeasonNHLTeamsTest() {
		scheduleRegularSeasonTest();
	}


	public void scheduleRegularSeasonTest() {
		stateMachine = stateFactory.createStateMachine(null);
		LeagueDataModelAbstractFactory dataModelFactory = LeagueDataModelFactory.getNewInstance();
		IGameSchedule schedule = dataModelFactory.createGameSchedule();
		int teamsCount = 0;
		for (IConference conference : league.getConferences()) {
			for (IDivision division : conference.getDivisions()) {
				for (ITeam team : division.getTeams()) {
					teamsCount++;
					
				}
			}
		}
		assertEquals(schedule.scheduleRegularSeason(game, stateMachine).size(), teamsCount*82);
	}
}
