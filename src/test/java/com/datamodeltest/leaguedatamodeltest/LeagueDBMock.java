package com.datamodeltest.leaguedatamodeltest;
import java.util.List;

import com.datamodel.leaguedatamodel.IGame;
import com.datamodel.leaguedatamodel.ILeague;
import com.persistencemodel.ILeagueDB;

public class LeagueDBMock implements ILeagueDB {

	IGame game;

	@Override
	public boolean checkIfLeagueExists(String leagueName) {
		return false;
	}

	@Override
	public List<ILeague> loadLeaguesFromTeamName(String teamName) {
		return null;
	}

	@Override
	public boolean exportGameToJSON(IGame game) {
		this.game = game;
		return true;
	}
}