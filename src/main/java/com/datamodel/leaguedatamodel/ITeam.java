package com.datamodel.leaguedatamodel;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

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

    //	*****************************************************************************************************************

    void proposeTrade(ITrading trading);

    double getTradingGain(int differenceInSkatingStat, int differenceInShootingStat,
                          int differenceInCheckingStat, int differenceInSavingStat);

    IPlayer getPlayer(int index);

    void prepareForTrade();
}