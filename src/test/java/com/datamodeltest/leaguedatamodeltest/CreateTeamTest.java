package com.datamodeltest.leaguedatamodeltest;
import java.util.ArrayList;
import org.junit.Assert;
import org.junit.Test;
import com.datamodel.leaguedatamodel.Conference;
import com.datamodel.leaguedatamodel.CreateTeam;
import com.datamodel.leaguedatamodel.Division;
import com.datamodel.leaguedatamodel.FreeAgent;
import com.datamodel.leaguedatamodel.IConference;
import com.datamodel.leaguedatamodel.IDivision;
import com.datamodel.leaguedatamodel.IPlayer;
import com.datamodel.leaguedatamodel.ITeam;
import com.datamodel.leaguedatamodel.League;
import com.datamodel.leaguedatamodel.Team;

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
		ArrayList<IPlayer> myAgents = new ArrayList<>();
		myAgents.add(freeAgent1);
		myAgents.add(freeAgent2);
		CreateTeam team = new CreateTeam();
		ArrayList<IPlayer> newAgents = team.getRankedFreeAgents(myAgents);
		Assert.assertEquals(freeAgent2, newAgents.get(0));
		Assert.assertEquals(freeAgent1, newAgents.get(1));
	}
}