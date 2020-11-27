package com.datamodeltest.leaguedatamodeltest;
import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import com.datamodel.leaguedatamodel.ITeam;
import com.datamodel.leaguedatamodel.LoadTeam;
import com.datamodel.leaguedatamodel.Team;

public class LoadTeamTest {

	@Test
	public void teamExistTest() {
		LoadTeam mockteam = new LoadTeam();
		List<ITeam> teams = new ArrayList<>();
		ITeam team = new Team();
		String teamName = "HalifaxHeros";
		team.setTeamName(teamName);
		teams.add(team);
		Assert.assertEquals(team, mockteam.teamExist(teamName, teams));
	}

	@Test
	public void teamNotExistTest() {
		LoadTeam mockteam = new LoadTeam();
		List<ITeam> teams = new ArrayList<>();
		ITeam team = new Team();
		String teamName = "HalifaxHeros";
		team.setTeamName(teamName);
		teams.add(team);
		Assert.assertEquals(team, mockteam.teamExist(teamName, teams));
	}
}