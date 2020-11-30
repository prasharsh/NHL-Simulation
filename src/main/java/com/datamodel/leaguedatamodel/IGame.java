package com.datamodel.leaguedatamodel;

import java.util.List;

import com.persistencemodel.ILeagueDB;

public interface IGame {

	List<ILeague> getLeagues();
	boolean saveToDb(ILeagueDB leagueDB);
	boolean addLeague(ILeague league);
}