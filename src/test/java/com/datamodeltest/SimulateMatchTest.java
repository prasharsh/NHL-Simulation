package com.datamodeltest;
import static org.junit.Assert.assertTrue;

import java.sql.Date;
import java.util.ArrayList;

import com.datamodel.GameScheduler;
import com.datamodel.IGameSchedule;
import com.datamodel.SimulateMatch;
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


public class SimulateMatchTest {



	@Test
	public void simulateMatchTest(){
		simulateMatchTest(2,3,5);
	}
	
	@Test
	public void simulateLessMatchTest(){
		simulateMatchTest(2,2,5);
	}
	
	@Test
	public void simulateMoreMatchTest(){
		simulateMatchTest(2,5,5);
	}

	public void simulateMatchTest(int conferenceSize, int divisionSize,int teamSize){

		StateMachine stateMachine = new StateMachine(null);
		
		Game game = MockGame.mockGame(conferenceSize, divisionSize, teamSize);
		GameScheduler scheduler = new GameScheduler();
		ArrayList<IGameSchedule> matchSchedules = scheduler.scheduleRegularSeason(game,
				stateMachine);
		String str="2020-10-12";
		game.getLeagues().get(0).setCurrentDate(Date.valueOf(str));
		SimulateMatch simulateMatch = new SimulateMatch();
		double teamStrength = Math.random();
		double oppositionTeamStrength = Math.random();
		float randomWinChance = (float) 0.23;
		for (IGameSchedule gameSchedule : matchSchedules) {
			Date curreDate = game.getLeagues().get(0).getCurrentDate();
			Date matchDate = gameSchedule.getMatchDate();
			if(curreDate.compareTo(matchDate)==0) {
				assertTrue(simulateMatch.simulateMatchResult(gameSchedule.getTeamA(),teamStrength, gameSchedule.getTeamB(),oppositionTeamStrength, randomWinChance, game));
			}
		}
		scheduler.schedulePlayoff(game, stateMachine);
	}

}
