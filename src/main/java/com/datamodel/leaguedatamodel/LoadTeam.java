package com.datamodel.leaguedatamodel;
import java.util.ArrayList;
import com.inputoutputmodel.LoadTeamPrompt;
import com.persistencemodel.GameDB;
import com.persistencemodel.IGameDB;

public class LoadTeam {

	public void loadTeam() {
		Game game = new Game();
		IGameDB gamedb = new GameDB();
		LoadTeamPrompt loadTeamPrompt = new LoadTeamPrompt();
		while (true) {
			String teamName = loadTeamPrompt.inputTeamName();
			int leagueID = game.loadGameFromTeamName(teamName, gamedb);
			if (leagueID < 1) {
				loadTeamPrompt.invalidTeamName();
			} else {
				game.loadGame(leagueID, gamedb);
				break;
			}
		}
	}

	public ITeam teamExist(String teamName, ArrayList<ITeam> teams) {
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