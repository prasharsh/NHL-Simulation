package com.datamodel.leaguedatamodel;

import com.datamodel.gameplayconfig.IGameplayConfig;

import java.sql.Date;
import java.util.ArrayList;

public interface ILeague {

    int getLeagueId();

    String getLeagueName();

    Date getCurrentDate();

    boolean setCurrentDate(Date currentDate);

    boolean setLeagueId(int leagueId);

    boolean setLeagueName(String leagueName);

    ArrayList<IConference> getConferences();

    boolean addConference(IConference conference);

    void setTeamStandings(ArrayList<ITeamStanding> teamStanding);

    ArrayList<ITeamStanding> getTeamStandings();

    ArrayList<IPlayer> getFreeAgents();

    boolean addFreeAgent(IPlayer freeAgent);

    IPlayer removeFreeAgent(IPlayer freeAgent);

    ArrayList<IGeneralManager> getManagers();

    boolean setManager(IGeneralManager manager);

    ArrayList<IHeadCoach> getCoaches();

    boolean setCoach(IHeadCoach coach);

    ArrayList<IGameSchedule> getGameSchedules();

    void setGameSchedules(ArrayList<IGameSchedule> gameSchedules);

    IGameplayConfig getGamePlayConfig();

    boolean setGamePlayConfig(IGameplayConfig gameplayConfig);

    Date getSimulationStartDate();

    void setSimulationStartDate(Date simulationStartDate);

    int getSeason();

    void setSeason(int season);

    int getSeasonToSimulate();

    void setSeasonToSimulate(int seasonToSimulate);

    ArrayList<ITeam> getAllTeams();

    ArrayList<IPlayer> getActiveStrongestFreeAgents(String position);

    ITeam getStrongestTeam();

    ArrayList<IPlayer> getActiveFreeAgentsWithPosition(ArrayList<IPlayer> freeAgents, String position);

    IPlayer getStrongestFreeAgent(ArrayList<IPlayer> freeAgents);
}
