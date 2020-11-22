package com.datamodel.leaguedatamodel;

import java.util.ArrayList;

public interface ITeam {

    int getTeamId();

    String getTeamName();

    String getTeamCreatedBy();

    int getLossPointCount();

    double getTeamStrength();

    boolean setTeamId(int teamId);

    boolean setTeamName(String teamName);

    boolean setTeamCreatedBy(String teamCreatedBy);

    boolean setLossPointCount(int lossPointCount);

    IGeneralManager getGeneralManager();

    boolean setGeneralManager(IGeneralManager generalManager);

    IHeadCoach getHeadCoach();

    boolean setHeadCoach(IHeadCoach headCoach);

    ArrayList<IPlayer> getPlayers();

    int getPlayersCount();

    int getPlayingGoaliesCount();

    int getPlayingSkatersCount();

    boolean addPlayer(IPlayer player);

    IPlayer removePlayer(IPlayer player);

    ITeam[] getTeamPick();

    ITeam getTeamPickByPosition(int position);

    void initializeTeamPick();

    void setTeamPick(ITeam team, int position);
}