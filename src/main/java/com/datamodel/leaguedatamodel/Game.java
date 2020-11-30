package com.datamodel.leaguedatamodel;
import java.util.ArrayList;
import java.util.List;

import com.persistencemodel.ILeagueDB;

public class Game implements IGame {

	private final List<ILeague> leagues;

	public Game() {
		this.leagues = new ArrayList<>();
	}

	private boolean checkIfLeagueIsNull(ILeague league) {
		return league == null;
	}

	private boolean checkIfLeagueNameIsEmptyOrNull(String leagueName) {
		return leagueName == null || leagueName.trim().isEmpty();
	}

	private boolean checkIfLeagueNameAlreadyExists(String leagueName) {
		for (ILeague league : leagues) {
			if (league.getLeagueName().equals(leagueName)) {
				return true;
			}
		}
		return false;
	}

	@Override
	public List<ILeague> getLeagues() {
		return leagues;
	}

	@Override
	public boolean addLeague(ILeague league) {
		if (checkIfLeagueIsNull(league)) {
			return false;
		}
		if (checkIfLeagueNameIsEmptyOrNull(league.getLeagueName())) {
			return false;
		}
		if (checkIfLeagueNameAlreadyExists(league.getLeagueName())) {
			return false;
		}
		leagues.add(league);
		return true;
	}

	@Override
	public boolean saveToDb(ILeagueDB leagueDB) {
		return leagueDB.exportGameToJSON(this);
	}
}