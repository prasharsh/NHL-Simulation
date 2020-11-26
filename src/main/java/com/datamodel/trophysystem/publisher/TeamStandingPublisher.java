package com.datamodel.trophysystem.publisher;

import com.datamodel.leaguedatamodel.ITeam;
import com.datamodel.trophysystem.Constants;

public class TeamStandingPublisher extends Subject {

    private static TeamStandingPublisher instance = new TeamStandingPublisher();

    private TeamStandingPublisher() {

    }

    public static TeamStandingPublisher getInstance() {
        return instance;
    }

    public void notify(ITeam teamWon, ITeam teamLost) {
        subjectMap.put(Constants.TEAM_WON_KEY, teamWon);
        subjectMap.put(Constants.TEAM_LOST_KEY, teamLost);
        notifyObservers();
    }
}
