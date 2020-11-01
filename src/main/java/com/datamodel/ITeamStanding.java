package com.datamodel;

import com.datamodel.leaguedatamodel.ITeam;

public interface ITeamStanding {

    void setTeamId(int teamId);

    ITeam getTeam();

    void setTeam(ITeam team);

    String getDivisionName();

    void setDivisionName(String divisionName);

    String getConferenceName();

    void setConferenceName(String conferenceName);

    int getGamesPlayed();

    void setGamesPlayed(int gamesPlayed);

    int getGamesWon();

    void setGamesWon(int gamesWon);

    int getGamesLost();

    void setGamesLost(int gamesLost);

    int getTotalPoints();

    void setTotalPoints(int totalPoints);

}
