package com.datamodeltest;

import java.util.ArrayList;

import com.datamodeltest.CreateTeam;
import com.datamodeltest.leaguedatamodel.*;
import org.junit.Assert;
import org.junit.Test;

public class CreateTeamTest {

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
		Assert.assertNull("League does not exist", leagues.get(0).getLeagueName());
	}

	@Test
	public void getConferencesWhenNoConferencesTest() {
		League league = new League();
		Assert.assertEquals(new ArrayList<IConference>(), league.getConferences());
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
		Assert.assertNull("Conference does not exist", conferences.get(0).getConferenceName());
	}

	@Test
	public void getDivisionsWhenNoDivisionsTest() {
		Conference conference = new Conference();
		Assert.assertEquals(new ArrayList<IDivision>(), conference.getDivisions());
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
		Assert.assertNull("Division does not exist", divisions.get(0).getDivisionName());
	}

	@Test
	public void getTeamsWhenNoTeamsTest() {
		Division division = new Division();
		Assert.assertEquals(new ArrayList<ITeam>(), division.getTeams());
	}

	@Test
	public void teamExistTest() {
		ITeam team = new Team();
		String teamName = "HalifaxHeros";
		team.setTeamName("HalifaxHeros");
		Assert.assertEquals(teamName, team.getTeamName());
	}

	@Test
	public void addSingleTeamTest() {
		Division division = new Division();
		ITeam team = new Team();
		team.setTeamName("Boston");
		division.addTeam(team);
		Assert.assertEquals(team, division.getTeams().get(0));
	}

	@Test
	public void teamNotExistTest() {
		ArrayList<ITeam> teams = new ArrayList<>();
		ITeam team = new Team();
		teams.add(team);
		Assert.assertNull("Team does not exist",teams.get(0).getTeamName());
	}

	@Test
	public void convertFreeAgentToPlayerTest() {
		IPlayer freeAgent = new FreeAgent();
		freeAgent.setPlayerName("player1");
		freeAgent.setPlayerPosition("goalie");
		CreateTeam team = new CreateTeam();
		ITeam myTeam = new Team();
		team.addFreeAgentToTeam(freeAgent, myTeam);
		Assert.assertEquals(myTeam.getPlayers().get(0).getPlayerName(), "player1");
		Assert.assertEquals(0, myTeam.getPlayers().get(0).getPlayerChecking());
	}

	@Test
	public void getRankedFreeAgentsTest() {
		IPlayer freeAgent1 = new FreeAgent();
		freeAgent1.setPlayerName("player1");
		freeAgent1.setPlayerPosition("goalie");
		freeAgent1.setPlayerAgeYear(23);
		freeAgent1.setPlayerAgeDays(260);
		freeAgent1.setPlayerChecking(10);
		freeAgent1.setPlayerSaving(12);
		freeAgent1.setPlayerShooting(8);
		freeAgent1.setPlayerSkating(4);
		IPlayer freeAgent2 = new FreeAgent();
		freeAgent2.setPlayerName("player1");
		freeAgent2.setPlayerPosition("goalie");
		freeAgent2.setPlayerAgeYear(23);
		freeAgent2.setPlayerAgeDays(260);
		freeAgent2.setPlayerChecking(10);
		freeAgent2.setPlayerSaving(19);
		freeAgent2.setPlayerShooting(18);
		freeAgent2.setPlayerSkating(14);
		ArrayList<IPlayer> myAgents = new ArrayList<>();
		myAgents.add(freeAgent1);
		myAgents.add(freeAgent2);
		CreateTeam team = new CreateTeam();
		ArrayList<IPlayer> newAgents = team.getRankedFreeAgents(myAgents);
		Assert.assertEquals(freeAgent2, newAgents.get(0));
		Assert.assertEquals(freeAgent1, newAgents.get(1));
	}
}
