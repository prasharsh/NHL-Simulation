package com.datamodel.leaguedatamodel;
import java.util.List;

import com.persistencemodel.IGameDB;

public interface IGame {

	List<ILeague> getLeagues();
	boolean saveToDb(IGameDB gameDB);
	boolean addLeague(ILeague league);
	int loadGameFromTeamName(String teamName, IGameDB gameDB);
	boolean loadGame(int leagueId, IGameDB gameDB);
}