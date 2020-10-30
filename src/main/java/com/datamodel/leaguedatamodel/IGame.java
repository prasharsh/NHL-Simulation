package com.datamodel.leaguedatamodel;

import java.util.ArrayList;

import com.persistencemodel.IGameDB;

public interface IGame {

    ArrayList<ILeague> getLeagues();

    void getLeagueByName(IGameDB gameDB);

    boolean addLeague(ILeague league);
}