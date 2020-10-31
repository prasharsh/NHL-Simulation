package com.datamodeltest;

import com.datamodeltest.leaguedatamodel.ITeam;

import java.sql.Date;

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
}
