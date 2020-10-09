package com.dal.dhl.stateMachine;

import java.util.ArrayList;

import org.junit.Assert;
import org.junit.Test;

import g4dhl.Conference;
import g4dhl.Division;
import g4dhl.Game;
import g4dhl.GameDBMock;
import g4dhl.IConference;
import g4dhl.IDivision;
import g4dhl.ILeague;
import g4dhl.ITeam;
import g4dhl.League;
import g4dhl.Team;

public class LoadTeamTest {

	@Test
	public void leagueExistTest() {
		ILeague league = new League();
		String leagueName = "DHL";
		league.setLeagueName("DHL");
		Assert.assertEquals(leagueName, league.getLeagueName());
	}

	@Test
	public void leagueNotExistTest() {
		ArrayList<ILeague> leagues = new ArrayList<>();
		ILeague league = new League();
		leagues.add(league);
		Assert.assertEquals(null, leagues.get(0).getLeagueName());
	}

	@Test
	public void conferenceExistTest() {
		IConference conference = new Conference();
		String conferenceName = "Western";
		conference.setConferenceName("Western");
		Assert.assertEquals(conferenceName, conference.getConferenceName());
	}

	@Test
	public void conferenceNotExistTest() {
		ArrayList<IConference> conferences = new ArrayList<>();
		IConference conference = new Conference();
		conferences.add(conference);
		Assert.assertEquals(null, conferences.get(0).getConferenceName());
	}

	@Test
	public void DivisionExistTest() {
		IDivision division = new Division();
		String divisionName = "Atlantic";
		division.setDivisionName("Atlantic");
		Assert.assertEquals(divisionName, division.getDivisionName());
	}

	@Test
	public void divisionNotExistTest() {
		ArrayList<IDivision> divisions = new ArrayList<>();
		IDivision division = new Division();
		divisions.add(division);
		Assert.assertEquals(null, divisions.get(0).getDivisionName());
	}

	@Test
	public void teamExistTest() {
		ITeam team = new Team();
		String teamName = "HalifaxHeros";
		team.setTeamName("HalifaxHeros");
		Assert.assertEquals(teamName, team.getTeamName());
	}

	@Test
	public void teamNotExistTest() {
		ArrayList<ITeam> teams = new ArrayList<>();
		ITeam team = new Team();
		teams.add(team);
		Assert.assertEquals(null, teams.get(0).getTeamName());
	}

	@Test
	public void loadLeaguesDataFromDB() {
		Game game = new Game();
		GameDBMock gameDB = new GameDBMock();
		game.loadFromDB(gameDB);
		Assert.assertTrue("No leagues loaded from DB", game.getLeagues().size() > 0);
	}

}
