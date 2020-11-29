package com.datamodeltest.leaguedatamodeltest;
import com.datamodel.leaguedatamodel.*;
import com.persistencemodel.ILeagueDB;

import java.util.List;

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