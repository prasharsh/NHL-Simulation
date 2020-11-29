package com.datamodeltest.leaguedatamodeltest;
import java.util.ArrayList;
import java.util.List;

import com.datamodel.leaguedatamodel.*;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

public class DivisionTest {

	private LeagueDataModelAbstractFactory leagueDataModelAbstractFactory = LeagueDataModelAbstractFactory.instance();
	private IDivision division = leagueDataModelAbstractFactory.createDivision();

	@BeforeClass
	public static void createDivision(){
		LeagueDataModelAbstractFactory.setFactory(new LeagueDataModelFactoryTest());
	}

	@Test
	public void getDivisionIdTest() {
		division.setDivisionId(5);
		Assert.assertEquals(5,division.getDivisionId());
	}

	@Test
	public void getDivisionNameTest() {
		division.setDivisionName("Atlantic");
		Assert.assertEquals("Atlantic", division.getDivisionName());
	}

	@Test
	public void getTeamsWhenNoTeamsTest() {
		Assert.assertEquals(new ArrayList<ITeam>(), division.getTeams());
	}

	@Test
	public void addNullTeamTest() {
		Assert.assertFalse("Team cannot be null", division.addTeam(null));
	}

	@Test
	public void addTeamWithNullTeamNameTest() {
		ITeam team = new Team();
		team.setTeamName(null);
		Assert.assertFalse("Team with null team name cannot not be inserted", division.addTeam(team));
	}

	@Test
	public void addTeamWithEmptyTeamNameTest() {
		ITeam team = new Team();
		team.setTeamName("");
		Assert.assertFalse("Team with empty team name cannot not be inserted", division.addTeam(team));
	}

	@Test
	public void addSingleTeamTest() {
		ITeam team = new Team();
		team.setTeamName("Boston");
		division.addTeam(team);
		Assert.assertEquals(team, division.getTeams().get(0));
	}

	@Test
	public void addTeamWithExistingTeamNameTest() {
		ITeam team1 = new Team();
		team1.setTeamName("Boston");
		division.addTeam(team1);
		ITeam team2 = new Team();
		team2.setTeamName("Boston");
		Assert.assertFalse("New team with same team name cannot not be inserted", division.addTeam(team2));
	}

	@Test
	public void addMultipleTeamsTest() {
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