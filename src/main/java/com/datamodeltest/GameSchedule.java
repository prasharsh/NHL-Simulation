package com.datamodeltest;

import com.datamodeltest.leaguedatamodel.ITeam;

import java.sql.Date;

public class GameSchedule implements IGameSchedule {

    private int gameScheduleId;
    private int leagueId;
    private int season;
    private ITeam teamA;
    private ITeam teamB;
    private Date matchDate;
    private int winningTeam;
    private int lossingTeam;
    private String gameType;
    private String status;

    @Override
    public int getGameScheduleId() {
        return gameScheduleId;
    }

    @Override
    public void setGameScheduleId(int gameScheduleId) {
        this.gameScheduleId = gameScheduleId;
    }

    public GameSchedule() {
        super();
    }

    @Override
    public int getLeagueId() {
        return leagueId;
    }

    @Override
    public void setLeagueId(int leagueId) {
        this.leagueId = leagueId;
    }

    @Override
    public int getSeason() {
        return season;
    }

    @Override
    public void setSeason(int season) {
        this.season = season;
    }

    @Override
    public ITeam getTeamA() {
        return teamA;
    }

    @Override
    public void setTeamA(ITeam teamA) {
        this.teamA = teamA;
    }

    @Override
    public ITeam getTeamB() {
        return teamB;
    }

    @Override
    public void setTeamB(ITeam teamB) {
        this.teamB = teamB;
    }

    @Override
    public Date getMatchDate() {
        return matchDate;
    }

    @Override
    public void setMatchDate(Date matchDate) {
        this.matchDate = matchDate;
    }

    @Override
    public int getWinningTeam() {
        return winningTeam;
    }

    @Override
    public void setWinningTeam(int winningTeam) {
        this.winningTeam = winningTeam;
    }

    @Override
    public int getLossingTeam() {
        return lossingTeam;
    }

    @Override
    public void setLossingTeam(int lossingTeam) {
        this.lossingTeam = lossingTeam;
    }

    @Override
    public String getGameType() {
        return gameType;
    }

    @Override
    public void setGameType(String gameType) {
        this.gameType = gameType;
    }


    @Override
    public String toString() {
        return "GameSchedule [gameScheduleId=" + gameScheduleId + ", leagueId=" + leagueId + ", season=" + season
                + ", teamA=" + teamA + ", teamB=" + teamB + ", matchDate=" + matchDate + ", winningTeam=" + winningTeam
                + ", lossingTeam=" + lossingTeam + ", gameType=" + gameType + "]";
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }


}
