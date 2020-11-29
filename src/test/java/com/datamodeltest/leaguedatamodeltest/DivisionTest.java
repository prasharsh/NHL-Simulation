package com.datamodeltest.leaguedatamodeltest;

import com.datamodel.leaguedatamodel.Division;
import com.datamodel.leaguedatamodel.ITeam;
import com.datamodel.leaguedatamodel.Team;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class DivisionTest {

	@Test
	public void getDivisionIdTest() {
		Division division = new Division();
		division.setDivisionId(5);
		Assert.assertEquals(5,division.getDivisionId());
	}

	@Test
	public void getDivisionNameTest() {
		Division division = new Division();
		division.setDivisionName("Atlantic");
		Assert.assertEquals("Atlantic",division.getDivisionName());
	}

	@Test
	public void getTeamsWhenNoTeamsTest() {
		Division division = new Division();
		Assert.assertEquals(new ArrayList<ITeam>(), division.getTeams());
	}

	@Test
	public void addNullTeamTest() {
		Division division = new Division();
		Assert.assertFalse("Team cannot be null", division.addTeam(null));
	}

	@Test
	public void addTeamWithNullTeamNameTest() {
		Division division = new Division();
		ITeam team = new Team();
		team.setTeamName(null);
		Assert.assertFalse("Team with null team name cannot not be inserted", division.addTeam(team));
	}

	@Test
	public void addTeamWithEmptyTeamNameTest() {
		Division division = new Division();
		ITeam team = new Team();
		team.setTeamName("");
		Assert.assertFalse("Team with empty team name cannot not be inserted", division.addTeam(team));
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
	public void addTeamWithExistingTeamNameTest() {
		Division division = new Division();
		ITeam team1 = new Team();
		team1.setTeamName("Boston");
		division.addTeam(team1);
		ITeam team2 = new Team();
		team2.setTeamName("Boston");
		Assert.assertFalse("New team with same team name cannot not be inserted", division.addTeam(team2));
	}

	@Test
	public void addMultipleTeamsTest() {
		Division division = new Division();
		ITeam team1 = new Team();
		team1.setTeamName("Boston");
		ITeam team2 = new Team();
		team2.setTeamName("Lightning");
		List<ITeam> teams = new ArrayList<>();
		teams.add(team1);
		teams.add(team2);
		division.addTeam(team1);
		division.addTeam(team2);
		Assert.assertEquals(teams, division.getTeams());
	}
}