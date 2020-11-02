package com.datamodel.leaguedatamodel;

import java.util.ArrayList;

import com.persistencemodel.GameDB;
import com.persistencemodel.IGameDB;

public interface IGame {

	ArrayList<ILeague> getLeagues();
	boolean saveToDb(IGameDB gameDB);
	boolean addLeague(ILeague league);
	int loadGameFromTeamName(String teamName, IGameDB gameDB);
	boolean loadGame(int leagueId, IGameDB gameDB);
}