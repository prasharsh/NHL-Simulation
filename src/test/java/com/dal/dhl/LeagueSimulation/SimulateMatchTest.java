package com.dal.dhl.LeagueSimulation;
import java.sql.Date;
import java.util.ArrayList;

import org.junit.Test;

import com.dal.dhl.stateMachine.DHLStateMachine;

import g4dhl.Conference;
import g4dhl.Division;
import g4dhl.Game;
import g4dhl.IConference;
import g4dhl.IDivision;
import g4dhl.IGameSchedule;
import g4dhl.ILeague;
import g4dhl.ITeam;
import g4dhl.League;
import g4dhl.Team;


public class SimulateMatchTest {



	@Test
	public void simulateMatchTest(){
		simulateMatchTest(2,3,5);
	}

	//@Test
	public void simulateMatchTest(int conferenceSize, int divisionSize,int teamSize){

		DHLStateMachine stateMachine = new DHLStateMachine(null);
		
		Game game = mockGame(conferenceSize, divisionSize, teamSize);
		GameScheduler scheduler = new GameScheduler();
		ArrayList<IGameSchedule> matchSchedules = scheduler.scheduleRegularSeason(game,
				stateMachine);
		/*
								 * for (IGameSchedule iGameSchedule : matchSchedules) {
								 * System.out.println(iGameSchedule.getTeamA().getTeamName()+ " vs " +
								 * iGameSchedule.getTeamB().getTeamName() + " on "+
								 * iGameSchedule.getMatchDate()); }
								 */
		
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
				System.out.println(gameSchedule.getTeamA().getTeamName()+" vs "+gameSchedule.getTeamB().getTeamName());
				simulateMatch.simulateMatchResult(gameSchedule.getTeamA(),teamStrength, gameSchedule.getTeamB(),oppositionTeamStrength, randomWinChance, game);
		//		System.out.println(game.getLeagues().get(0).getTeamStandings().toString());
			}
		}
	//	System.out.println(game.getLeagues().get(0).getTeamStandings().toString());

	}

	private Game mockGame(int conf, int div, int teams) {
		Game game = new Game();
		ILeague league = new League();
		league.setLeagueName("mock");
		String str="2020-09-30";
		league.setCurrentDate(Date.valueOf(str));
		for(int i = 1; i<=conf; i++) {
			IConference conferenceObj = new Conference();
			conferenceObj.setConferenceId(i);
			conferenceObj.setConferenceName("C"+i);
			for(int j=1; j<=div;j++) {
				IDivision divisionObj = new Division();
				divisionObj.setDivisionName("D"+j);
				divisionObj.setDivisionId(Integer.parseInt(""+i+j));
				for(int k = 1; k<=teams; k++) {
					ITeam teamObj = new Team();
					teamObj.setTeamId(Integer.parseInt(""+i+j+k));
					teamObj.setTeamName("T"+i+j+k);
					divisionObj.addTeam(teamObj);
				}
				conferenceObj.addDivision(divisionObj);
			}
			league.addConference(conferenceObj);
		}
		game.addLeague(league);
		return game;
	}
}
