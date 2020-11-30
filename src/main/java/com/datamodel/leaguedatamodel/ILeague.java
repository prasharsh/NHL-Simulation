package com.datamodel.leaguedatamodel;

import com.datamodel.gameplayconfig.IGamePlayConfig;

import java.sql.Date;
import java.util.HashSet;
import java.util.List;

public interface ILeague {

	int getLeagueId();

	String getLeagueName();

	Date getCurrentDate();

	boolean setCurrentDate(Date currentDate);

	boolean setLeagueId(int leagueId);

	boolean setLeagueName(String leagueName);

	List<IConference> getConferences();

	boolean addConference(IConference conference);

	void setTeamStandings(List<ITeamStanding> teamStanding);

	List<ITeamStanding> getTeamStandings();

	List<IPlayer> getFreeAgents();

	boolean addFreeAgent(IPlayer freeAgent);

	IPlayer removeFreeAgent(IPlayer freeAgent);

	List<IGeneralManager> getManagers();

	boolean setManager(IGeneralManager manager);

	List<IHeadCoach> getCoaches();

	boolean setCoach(IHeadCoach coach);

	List<IGameSchedule> getGameSchedules();

	void setGameSchedules(List<IGameSchedule> gameSchedules);

	IGamePlayConfig getGamePlayConfig();

	boolean setGamePlayConfig(IGamePlayConfig gameplayConfig);

	Date getSimulationStartDate();

	void setSimulationStartDate(Date simulationStartDate);

	int getSeason();

	void setSeason(int season);

	int getSeasonToSimulate();

	void setSeasonToSimulate(int seasonToSimulate);

	List<ITeam> getAllTeams();

	List<IPlayer> getActiveStrongestFreeAgents(String position);

	ITeam getStrongestTeam();

	List<IPlayer> getActiveFreeAgentsWithPosition(List<IPlayer> freeAgents, String position);

	IPlayer getStrongestFreeAgent(List<IPlayer> freeAgents);

	HashSet<ITeam> getGameDayTeams();

	void setGameDayTeams(HashSet<ITeam> gameDayTeams);
}
