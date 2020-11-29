package com.datamodel.leaguedatamodel;
import java.util.List;

public class LoadTeam {

	public ITeam teamExist(String teamName, List<ITeam> teams) {
		ITeam currentTeam = null;
		for (ITeam team : teams) {
			if (team.getTeamName().equalsIgnoreCase(teamName)) {
				currentTeam = team;
				break;
			} else {
				currentTeam = null;
			}
		}
		return currentTeam;
	}
}