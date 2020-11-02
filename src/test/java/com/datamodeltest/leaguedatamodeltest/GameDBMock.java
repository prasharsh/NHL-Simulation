package com.datamodeltest.leaguedatamodeltest;

import com.datamodel.leaguedatamodel.*;
import com.persistencemodel.IGameDB;

public class GameDBMock implements IGameDB {

	IGame game;
	String teamName = "DHL";

	@Override
	public boolean saveGame(IGame game) {
		this.game = game;
		return true;
	}

	@Override
	public int getLeagueIdFromTeamName(String teamName) {
		if (teamName.equals(this.teamName)){
			return 1;
		}
		return 0;
	}

	@Override
	public boolean loadGame(int leagueId, IGame game) {
		for (ILeague league: this.game.getLeagues()){
			if (league.getLeagueId() == leagueId){
				return true;
			}
		}
		return false;
	}
}