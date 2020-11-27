package com.datamodel.leaguedatamodel;

import com.datamodel.gameplayconfig.IGamePlayConfig;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class League implements ILeague {

    private int leagueId;
    private String leagueName;
    private final List<IConference> conferences;
    private final List<IPlayer> freeAgents;
    private final List<IGeneralManager> managers;
    private final List<IHeadCoach> coaches;
    private Date currentDate;
    private IGamePlayConfig gameplayConfig;
    private List<ITeamStanding> teamStandings;
    private List<IGameSchedule> gameSchedules;
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
        if (checkIfLeagueNameIsNullOrEmpty(leagueName)) {
            return false;
        }
        this.leagueName = leagueName;
        return true;
    }

    @Override
    public List<IConference> getConferences() {
        return conferences;
    }

    @Override
    public boolean addConference(IConference conference) {
        if (checkIfConferenceIsNull(conference)) {
            return false;
        }
        if (checkIfConferenceNameIsNullOrEmpty(conference.getConferenceName())) {
            return false;
        }
        if (checkIfConferenceNameAlreadyExists(conference.getConferenceName())) {
            return false;
        }
        conferences.add(conference);
        return true;
    }

    @Override
    public List<IPlayer> getFreeAgents() {
        return freeAgents;
    }

    @Override
    public boolean addFreeAgent(IPlayer freeAgent) {
        if (checkIfFreeAgentIsNull(freeAgent)) {
            return false;
        }
        if (checkIfFreeAgentNameIsNullOrEmpty(freeAgent.getPlayerName())) {
            return false;
        }
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
    public List<IGeneralManager> getManagers() {
        return managers;
    }

    @Override
    public boolean setManager(IGeneralManager manager) {
        managers.add(manager);
        return true;
    }

    @Override
    public List<IHeadCoach> getCoaches() {
        return coaches;
    }

    @Override
    public boolean setCoach(IHeadCoach coach) {
        coaches.add(coach);
        return true;
    }

    @Override
    public IGamePlayConfig getGamePlayConfig() {
        return this.gameplayConfig;
    }

    @Override
    public boolean setGamePlayConfig(IGamePlayConfig gameplayConfig) {
        this.gameplayConfig = gameplayConfig;
        return true;
    }

    public List<ITeamStanding> getTeamStandings() {
        return teamStandings;
    }

    public void setTeamStandings(List<ITeamStanding> teamStandings) {
        this.teamStandings = teamStandings;
    }

    @Override
    public List<IGameSchedule> getGameSchedules() {
        return gameSchedules;
    }

    @Override
    public void setGameSchedules(List<IGameSchedule> gameSchedules) {
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

    @Override
    public List<ITeam> getAllTeams() {
        List<ITeam> teams = new ArrayList<>();
        for (IConference conference : conferences) {
            for (IDivision division : conference.getDivisions()) {
                teams.addAll(division.getTeams());
            }
        }
        return teams;
    }

    @Override
    public List<IPlayer> getActiveStrongestFreeAgents(String position) {
        List<IPlayer> freeAgentsWithPosition = new ArrayList<>();
        for (IPlayer freeAgent : freeAgents) {
            if (freeAgent.getPlayerPosition().equals(position)) {
                if (freeAgent.isPlayerRetired()) {
                    continue;
                }
                freeAgentsWithPosition.add(freeAgent);
            }
        }
        freeAgentsWithPosition.sort(Comparator.comparingDouble(IPlayer::getPlayerStrength).reversed());
        return freeAgentsWithPosition;
    }

    @Override
    public ITeam getStrongestTeam() {
        double strongestTeamStrength = 0.0;
        ITeam strongestTeam = null;
        for (IConference conference : conferences) {
            for (IDivision division : conference.getDivisions()) {
                for (ITeam team : division.getTeams()) {
                    double teamStrength = team.getTeamStrength();
                    if (teamStrength > strongestTeamStrength) {
                        strongestTeamStrength = teamStrength;
                        strongestTeam = team;
                    }
                }
            }
        }
        return strongestTeam;
    }

    @Override
    public List<IPlayer> getActiveFreeAgentsWithPosition(List<IPlayer> freeAgents, String position) {

        List<IPlayer> activeFreeAgentsWithPosition = new ArrayList<>();

        for (IPlayer freeAgent : freeAgents) {
            if (freeAgent.getPlayerPosition().equals(position)) {
                if (freeAgent.isPlayerRetired()) {
                    continue;
                }
                activeFreeAgentsWithPosition.add(freeAgent);
            }
        }
        return activeFreeAgentsWithPosition;
    }

    @Override
    public IPlayer getStrongestFreeAgent(List<IPlayer> freeAgents) {
        double strongestFreeAgentStrength = 0.0;
        IPlayer strongestFreeAgent = null;
        for (IPlayer freeAgent : freeAgents) {
            double freeAgentStrength = freeAgent.getPlayerStrength();
            if (freeAgentStrength > strongestFreeAgentStrength) {
                strongestFreeAgentStrength = freeAgentStrength;
                strongestFreeAgent = freeAgent;
            }
        }
        return strongestFreeAgent;
    }
}