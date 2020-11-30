package com.datamodel.leaguedatamodel;

import com.persistencemodel.ILeagueDB;

import java.util.List;

public interface IGame {

	List<ILeague> getLeagues();

	boolean saveToDb(ILeagueDB leagueDB);

	boolean addLeague(ILeague league);
}