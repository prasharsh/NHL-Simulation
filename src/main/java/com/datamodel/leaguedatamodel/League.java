package com.datamodel.leaguedatamodel;

import java.sql.Date;
import java.util.ArrayList;

import com.datamodel.gameplayconfig.IGameplayConfig;
import com.persistencemodel.IGameDB;

public class League implements ILeague {

    private int leagueId;
    private String leagueName;
    private final ArrayList<IConference> conferences;
    private final ArrayList<IPlayer> freeAgents;
    private final ArrayList<IGeneralManager> managers;
    private final ArrayList<IHeadCoach> coaches;
    private Date currentDate;
    private IGameplayConfig gameplayConfig;
    private ArrayList<ITeamStanding> teamStandings;
    private ArrayList<IGameSchedule> gameSchedules;
    private Date simulationStartDate;
    private int season;
    private int seasonToSimulate = 0;

    public League() {
        leagueName = null;
        conferences = new ArrayList<>();
        freeAgents = new ArrayList<>();
        managers = new ArrayList<>();
        coaches = new ArrayList<>();
    }

    private boolean checkIfLeagueNameIsNullOrEmpty(String leagueName) {
        return leagueName == null || leagueName.trim().isEmpty();
    }

    private boolean checkIfConferenceIsNull(IConference conference) {
        return conference == null;
    }

    private boolean checkIfConferenceNameIsNullOrEmpty(String conferenceName) {
        return conferenceName == null || conferenceName.trim().isEmpty();
    }

    private boolean checkIfConferenceNameAlreadyExists(String conferenceName) {
        for (IConference conference : conferences) {
            if (conference.getConferenceName().equals(conferenceName)) {
                return true;
            }
        }
        return false;
    }

    private boolean checkIfFreeAgentIsNull(IPlayer freeAgent) {
        return freeAgent == null;
    }

    private boolean checkIfFreeAgentNameIsNullOrEmpty(String freeAgent) {
        return freeAgent == null || freeAgent.trim().isEmpty();
    }

    @Override
    public int getLeagueId() {
        return leagueId;
    }

    @Override
    public String getLeagueName() {
        return leagueName;
    }

    @Override
    public boolean setLeagueId(int leagueId) {
        this.leagueId = leagueId;
        return true;
    }

    @Override
    public boolean setLeagueName(String leagueName) {
        if (checkIfLeagueNameIsNullOrEmpty(leagueName))
            return false;
        this.leagueName = leagueName;
        return true;
    }

    @Override
    public ArrayList<IConference> getConferences() {
        return conferences;
    }

    @Override
    public boolean addConference(IConference conference) {

        if (checkIfConferenceIsNull(conference))
            return false;
        if (checkIfConferenceNameIsNullOrEmpty(conference.getConferenceName()))
            return false;
        if (checkIfConferenceNameAlreadyExists(conference.getConferenceName()))
            return false;

        conferences.add(conference);
        return true;
    }

    @Override
    public ArrayList<IPlayer> getFreeAgents() {
        return freeAgents;
    }

    @Override
    public boolean addFreeAgent(IPlayer freeAgent) {

        if (checkIfFreeAgentIsNull(freeAgent))
            return false;
        if (checkIfFreeAgentNameIsNullOrEmpty(freeAgent.getPlayerName()))
            return false;

        freeAgents.add(freeAgent);
        return true;
    }

    @Override
    public IPlayer removeFreeAgent(IPlayer freeAgent) {
        if (freeAgents.contains(freeAgent)) {
            freeAgents.remove(freeAgent);
            return freeAgent;
        }
        return null;
    }

    @Override
    public Date getCurrentDate() {
        return currentDate;
    }

    @Override
    public boolean setCurrentDate(Date currentDate) {
        this.currentDate = currentDate;
        return true;
    }

    @Override
    public ArrayList<IGeneralManager> getManagers() {
        return managers;
    }

    @Override
    public boolean setManager(IGeneralManager manager) {
        managers.add(manager);
        return true;
    }

    @Override
    public ArrayList<IHeadCoach> getCoaches() {
        return coaches;
    }

    @Override
    public boolean setCoach(IHeadCoach coach) {
        coaches.add(coach);
        return true;
    }

    @Override
    public IGameplayConfig getGamePlayConfig() {
        return this.gameplayConfig;
    }

    @Override
    public boolean setGamePlayConfig(IGameplayConfig gameplayConfig) {
        this.gameplayConfig = gameplayConfig;
        return true;
    }

    public ArrayList<ITeamStanding> getTeamStandings() {
        return teamStandings;
    }

    public void setTeamStandings(ArrayList<ITeamStanding> teamStandings) {
        this.teamStandings = teamStandings;
    }

    @Override
    public ArrayList<IGameSchedule> getGameSchedules() {
        return gameSchedules;
    }

    @Override
    public void setGameSchedules(ArrayList<IGameSchedule> gameSchedules) {
        this.gameSchedules = gameSchedules;
    }

    @Override
    public Date getSimulationStartDate() {
        return this.simulationStartDate;
    }


    @Override
    public void setSimulationStartDate(Date simulationStartDate) {
        this.simulationStartDate = simulationStartDate;

    }


    @Override
    public int getSeason() {
        return this.season;
    }


    @Override
    public void setSeason(int season) {
        this.season = season;

    }

    @Override
    public int getSeasonToSimulate() {
        return seasonToSimulate;
    }

    @Override
    public void setSeasonToSimulate(int seasonToSimulate) {
        this.seasonToSimulate = seasonToSimulate;
    }

}
