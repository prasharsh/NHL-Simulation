package com.dal.dhl.LeagueSimulation;
import static org.junit.Assert.assertEquals;

import java.sql.Date;

import org.junit.Test;

import com.dal.dhl.stateMachine.StateMachine;

import g4dhl.Conference;
import g4dhl.Division;
import g4dhl.Game;
import g4dhl.IConference;
import g4dhl.IDivision;
import g4dhl.ILeague;
import g4dhl.ITeam;
import g4dhl.League;
import g4dhl.Team;


public class GameSchedulerTest {



	@Test
	public void scheduleRegularSeasonForLessTeamsTest(){
		scheduleRegularSeasonTest(2,2,2);
	}
	
	@Test
	public void scheduleRegularSeasonNHLTeamsTest(){
		scheduleRegularSeasonTest(2,3,5);
	}
	@Test
	public void scheduleRegularSeasonFor66TeamsTest(){
		scheduleRegularSeasonTest(2,3,11);
	}
	
	//@Test
	public void scheduleRegularSeasonTest(int conferenceSize, int divisionSize,int teamSize){
		/*
		 * int conferenceSize = 2; int divisionSize =2; int teamSize = 6;
		 */
		StateMachine stateMachine = new StateMachine(null);
		Game game = mockGame(conferenceSize, divisionSize, teamSize);
		GameScheduler scheduler = new GameScheduler();
		int totalGameScheduled = (conferenceSize* divisionSize* teamSize*82);
		assertEquals(scheduler.scheduleRegularSeason(game, stateMachine).size(), totalGameScheduled);
	}

	private Game mockGame(int conf, int div, int teams) {
		Game game = new Game();
		ILeague league = new League();
		league.setLeagueName("mock");
		String str="2020-09-30";
		league.setCurrentDate(Date.valueOf(str));
		league.setSimulationStartDate(league.getCurrentDate());
		for(int i = 0; i<conf; i++) {
			IConference conferenceObj = new Conference();
			conferenceObj.setConferenceId(i);
			conferenceObj.setConferenceName("C"+i);
			for(int j=0; j<div;j++) {
				IDivision divisionObj = new Division();
				divisionObj.setDivisionName("D"+j);
				divisionObj.setDivisionId(Integer.parseInt(""+i+j));
				for(int k = 0; k<teams; k++) {
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
