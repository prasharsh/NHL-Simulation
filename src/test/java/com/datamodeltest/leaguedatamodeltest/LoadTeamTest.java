package com.datamodeltest.leaguedatamodeltest;

import com.datamodel.leaguedatamodel.ITeam;
import com.datamodel.leaguedatamodel.LeagueDataModelAbstractFactory;
import com.datamodel.leaguedatamodel.LoadTeam;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class LoadTeamTest {

	private final LeagueDataModelAbstractFactory leagueDataModelAbstractFactory =
			LeagueDataModelAbstractFactory.instance();
	private final ITeam team = leagueDataModelAbstractFactory.createTeam();

	@BeforeClass
	public static void createConference() {
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