package com.dal.dhl.stateMachine;

import java.util.ArrayList;

import org.junit.Assert;
import org.junit.Test;

import g4dhl.Conference;
import g4dhl.Division;
import g4dhl.FreeAgent;
import g4dhl.IConference;
import g4dhl.IDivision;
import g4dhl.IFreeAgent;
import g4dhl.ILeague;
import g4dhl.ITeam;
import g4dhl.League;
import g4dhl.Team;

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
		Assert.assertEquals(null, leagues.get(0).getLeagueName());
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
		Assert.assertEquals(null, conferences.get(0).getConferenceName());
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
		Assert.assertEquals(null, divisions.get(0).getDivisionName());
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
		Assert.assertEquals(null, teams.get(0).getTeamName());
	}

	@Test
	public void convertFreeAgentToPlayerTest() {
		IFreeAgent freeAgent = new FreeAgent();
		freeAgent.setFreeAgentName("player1");
		freeAgent.setFreeAgentPosition("goalie");
		CreateTeam team = new CreateTeam();
		ITeam myTeam = new Team();
		team.addFreeAgentToTeam(freeAgent, myTeam);
		Assert.assertTrue(myTeam.getPlayers().get(0).getPlayerName().equals("player1"));
		Assert.assertEquals(0, myTeam.getPlayers().get(0).getPlayerChecking());
	}

	@Test
	public void getRankedFreeAgentsTest() {
		IFreeAgent freeAgent1 = new FreeAgent();
		freeAgent1.setFreeAgentName("player1");
		freeAgent1.setFreeAgentPosition("goalie");
		freeAgent1.setFreeAgentAgeYear(23);
		freeAgent1.setFreeAgentAgeDays(260);
		freeAgent1.setFreeAgentChecking(10);
		freeAgent1.setFreeAgentSaving(12);
		freeAgent1.setFreeAgentShooting(8);
		freeAgent1.setFreeAgentSkating(4);
		IFreeAgent freeAgent2 = new FreeAgent();
		freeAgent2.setFreeAgentName("player1");
		freeAgent2.setFreeAgentPosition("goalie");
		freeAgent2.setFreeAgentAgeYear(23);
		freeAgent2.setFreeAgentAgeDays(260);
		freeAgent2.setFreeAgentChecking(10);
		freeAgent2.setFreeAgentSaving(19);
		freeAgent2.setFreeAgentShooting(18);
		freeAgent2.setFreeAgentSkating(14);
		ArrayList<IFreeAgent> myAgents = new ArrayList<>();
		myAgents.add(freeAgent1);
		myAgents.add(freeAgent2);
		CreateTeam team = new CreateTeam();
		ArrayList<IFreeAgent> newAgents = team.getRankedFreeAgents(myAgents);
		Assert.assertEquals(freeAgent2, newAgents.get(0));
		Assert.assertEquals(freeAgent1, newAgents.get(1));
	}
}
