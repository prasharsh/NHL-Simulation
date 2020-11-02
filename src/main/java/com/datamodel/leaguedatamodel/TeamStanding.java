package com.datamodel.leaguedatamodel;

public class TeamStanding implements ITeamStanding {

    private int teamId;
    private ITeam team;
    private String divisionName;
    private String conferenceName;
    private int gamesPlayed = 0;
    private int gamesWon = 0;
    private int gamesLost = 0;
    private int totalPoints = 0;

    public int getTeamId() {
        return teamId;
    }

    @Override
    public void setTeamId(int teamId) {
        this.teamId = teamId;
    }

    @Override
    public ITeam getTeam() {
        return team;
    }

    @Override
    public void setTeam(ITeam team) {
        this.team = team;
    }

    @Override
    public String getDivisionName() {
        return divisionName;
    }

    @Override
    public void setDivisionName(String divisionName) {
        this.divisionName = divisionName;
    }

    @Override
    public String getConferenceName() {
        return conferenceName;
    }

    @Override
    public void setConferenceName(String conferenceName) {
        this.conferenceName = conferenceName;
    }

    @Override
    public int getGamesPlayed() {
        return gamesPlayed;
    }

    @Override
    public void setGamesPlayed(int gamesPlayed) {
        this.gamesPlayed = gamesPlayed;
    }

    @Override
    public int getGamesWon() {
        return gamesWon;
    }

    @Override
    public void setGamesWon(int gamesWon) {
        this.gamesWon = gamesWon;
    }

    @Override
    public int getGamesLost() {
        return gamesLost;
    }

    @Override
    public void setGamesLost(int gamesLost) {
        this.gamesLost = gamesLost;
    }

    @Override
    public int getTotalPoints() {
        return totalPoints;
    }

    @Override
    public void setTotalPoints(int totalPoints) {
        this.totalPoints = totalPoints;
    }

    @Override
    public String toString() {
        return "TeamStanding [team=" + team.getTeamName() + ", divisionName=" + divisionName
                + ", conferenceName=" + conferenceName + ", gamesPlayed=" + gamesPlayed + ", gamesWon=" + gamesWon
                + ", gamesLost=" + gamesLost + ", totalPoints=" + totalPoints + "] \n";
    }

}
