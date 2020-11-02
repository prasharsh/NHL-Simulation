package com.datamodel.leaguedatamodel;

import java.sql.Date;
import java.util.ArrayList;

import com.statemachine.StateMachine;

public interface IGameSchedule {

    int getGameScheduleId();

    void setGameScheduleId(int gameScheduleId);

    int getLeagueId();

    void setLeagueId(int leagueId);

    int getSeason();

    void setSeason(int season);

    ITeam getTeamA();

    void setTeamA(ITeam teamA);

    ITeam getTeamB();

    void setTeamB(ITeam teamB);

    Date getMatchDate();

    void setMatchDate(Date matchDate);

    int getWinningTeam();

    void setWinningTeam(int winningTeam);

    int getLossingTeam();

    void setLossingTeam(int lossingTeam);

    String getGameType();

    void setGameType(String gameType);

    String getStatus();

    void setStatus(String status);
    
    public ArrayList<IGameSchedule> schedulePlayoff(Game game, StateMachine stateMachine);
    
	public ArrayList<IGameSchedule> scheduleRegularSeason(Game game, StateMachine stateMachine);

}
