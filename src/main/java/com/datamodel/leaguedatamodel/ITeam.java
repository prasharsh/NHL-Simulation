package com.datamodel.leaguedatamodel;

import java.util.ArrayList;
import java.util.List;

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

    void proposeTrade(ITrading trading);

    double getTradingGain(int differenceInSkatingStat, int differenceInShootingStat,
                          int differenceInCheckingStat, int differenceInSavingStat);

    void setActiveRoster();

    IPlayer getPlayer(int index);

    void prepareForTrade();

    double getTeamGainByStat(int differenceInStat, int teamCurrentStat, int minStat);

    ArrayList<IPlayer> getFreeAgentsHiredAfterTrade(ArrayList<IPlayer> myPlayers, ILeague league) throws Exception;

    void completeRoster(ILeague league);

    void hireStrongestPlayersFromFreeAgentList(ILeague league, String position, int count);

    void dropWeakestPlayersToFreeAgentList(ILeague league, String position, int count);

    ArrayList<IPlayer> getActiveWeakestPlayers(String position);

    int getActivePlayersCountWithPosition(ArrayList<IPlayer> players, String position);

    ArrayList<IPlayer> getStrongestPlayersByStrength(ArrayList<IPlayer> players);

    ArrayList<IPlayer> getActivePlayersWithPosition(ArrayList<IPlayer> players, String position);
    
    List<IPlayer> getPlayingSix();
    
    void resetTeamPlayingStatus();
}