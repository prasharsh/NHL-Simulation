package com.datamodeltest.leaguedatamodeltest;
import java.util.ArrayList;
import java.util.List;

import com.datamodel.leaguedatamodel.*;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

public class LoadTeamTest {

	private LeagueDataModelAbstractFactory leagueDataModelAbstractFactory = LeagueDataModelAbstractFactory.instance();
	private ITeam team = leagueDataModelAbstractFactory.createTeam();

	@BeforeClass
	public static void createConference(){
		LeagueDataModelAbstractFactory.setFactory(new LeagueDataModelFactoryTest());
	}

	@Test
	public void teamExistTest() {
		LoadTeam mockteam = new LoadTeam();
		List<ITeam> teams = new ArrayList<>();
		String teamName = "HalifaxHeros";
		team.setTeamName(teamName);
		teams.add(team);
		Assert.assertEquals(team, mockteam.teamExist(teamName, teams));
	}

	@Test
	public void teamNotExistTest() {
		LoadTeam mockteam = new LoadTeam();
		List<ITeam> teams = new ArrayList<>();
		String teamName = "HalifaxHeros";
		team.setTeamName(teamName);
		teams.add(team);
		Assert.assertEquals(team, mockteam.teamExist(teamName, teams));
	}
}