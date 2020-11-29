package com.datamodel.trophysystem;

import com.datamodel.leaguedatamodel.ITeam;

public class TeamStandingSubject extends Subject {

    private static final String TEAM_WON_KEY = "teamWon";
    private static final String TEAM_LOST_KEY = "teamLost";
    private static final String RESET_KEY = "reset";

    private static TeamStandingSubject instance = new TeamStandingSubject();
    private ITeam bestTeam = null;
    private ITeam leastTeam = null;

    private TeamStandingSubject() {

    }

    public static TeamStandingSubject instance() {
        return instance;
    }

    public void notifyTeamStandingPublisher(ITeam teamWon, ITeam teamLost) {
        subjectMap.put(TEAM_WON_KEY, teamWon);
        subjectMap.put(TEAM_LOST_KEY, teamLost);
        notifyObservers();
    }

    public ITeam getBestTeam() {
        return this.bestTeam;
    }

    public void setBestTeam(ITeam bestTeam) {
        this.bestTeam = bestTeam;
    }

    public ITeam getLeastTeam() {
        return this.leastTeam;
    }

    public void setLeastTeam(ITeam leastTeam) {
        this.leastTeam = leastTeam;
    }

    public void resetStandings() {
        this.bestTeam = null;
        this.leastTeam = null;
        notifyTeamStandingPublisher(bestTeam, leastTeam);
    }
}
