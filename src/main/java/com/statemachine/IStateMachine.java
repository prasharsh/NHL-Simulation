package com.statemachine;

import com.datamodel.leaguedatamodel.IGame;
import com.datamodel.leaguedatamodel.ITeam;

import java.util.HashSet;
import java.util.List;

public interface IStateMachine {

    void start();

    List<ITeam> getTeamList();

    void setTeamList(List<ITeam> teamList);

    HashSet<ITeam> getGameDayTeams();

    void setGameDayTeams(HashSet<ITeam> gameDayTeams);

}