package com.datamodel.trophysystem.publisher;

import com.datamodel.leaguedatamodel.IPlayer;
import com.datamodel.leaguedatamodel.ITeam;
import com.datamodel.trophysystem.Constants;

public class TeamStandingPublisher extends Subject {

    private static TeamStandingPublisher instance = new TeamStandingPublisher();
    private ITeam bestTeam = null;
    private ITeam leastTeam = null;

    private TeamStandingPublisher() {

    }

    public static TeamStandingPublisher instance() {
        return instance;
    }

    public void notifyTeamStandingPublisher(ITeam teamWon, ITeam teamLost) {
        subjectMap.put(Constants.TEAM_WON_KEY, teamWon);
        subjectMap.put(Constants.TEAM_LOST_KEY, teamLost);
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
}
