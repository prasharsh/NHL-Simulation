package com.datamodeltest.leaguedatamodeltest;

import com.datamodel.leaguedatamodel.*;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class CreateTeamTest {

	@Test
	public void getConferencesWhenNoConferencesTest() {
		League league = new League();
		Assert.assertEquals(new ArrayList<IConference>(), league.getConferences());
	}

	@Test
	public void getDivisionsWhenNoDivisionsTest() {
		Conference conference = new Conference();
		Assert.assertEquals(new ArrayList<IDivision>(), conference.getDivisions());
	}

	@Test
	public void getTeamsWhenNoTeamsTest() {
		Division division = new Division();
		Assert.assertEquals(new ArrayList<ITeam>(), division.getTeams());
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
		List<IPlayer> myAgents = new ArrayList<>();
		myAgents.add(freeAgent1);
		myAgents.add(freeAgent2);
		CreateTeam team = new CreateTeam();
		List<IPlayer> newAgents = team.getRankedFreeAgents(myAgents);
		Assert.assertEquals(freeAgent2.getPlayerName(), newAgents.get(0).getPlayerName());
		Assert.assertEquals(freeAgent1.getPlayerName(), newAgents.get(1).getPlayerName());

	}
}