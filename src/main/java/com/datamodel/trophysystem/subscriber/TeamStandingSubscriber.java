package com.datamodel.trophysystem.subscriber;

import com.datamodel.leaguedatamodel.ITeam;
import com.datamodel.trophysystem.Constants;
import com.datamodel.trophysystem.publisher.Subject;
import com.datamodel.trophysystem.publisher.TeamStandingPublisher;

import java.util.*;

public class TeamStandingSubscriber extends Observer {

    private HashMap<ITeam, Integer> teamStandings;

    public TeamStandingSubscriber() {
        this.teamStandings = new HashMap<>();
    }

    @Override
    public void update(Subject subject) {
        ITeam teamWon = (ITeam) subject.getValue("teamWon");
        ITeam teamLost = (ITeam) subject.getValue("teamLost");

        if (teamStandings.containsKey(teamWon)) {
            teamStandings.put(teamWon, teamStandings.get(teamWon) + 1);
        } else {
            teamStandings.put(teamWon, 1);
        }

        teamStandings.put(teamLost, teamStandings.getOrDefault(teamLost, 0));

        ITeam bestTeam = getBestTeam(Constants.SORT_DESC);
        ITeam leastTeam = getBestTeam(Constants.SORT_ASC);
        TeamStandingPublisher.instance().setBestTeam(bestTeam);
        TeamStandingPublisher.instance().setLeastTeam(leastTeam);
    }

    private ITeam getBestTeam(String sort_order) {
        if (teamStandings.isEmpty()) {
            return null;
        }

        Set<Map.Entry<ITeam, Integer>> entrySet = teamStandings.entrySet();
        List<Map.Entry<ITeam, Integer>> standingsList = new LinkedList<>(entrySet);
        standingsList.sort((o1, o2) -> {
            if (sort_order.equals(Constants.SORT_ASC)) {
                return o1.getValue().compareTo(o2.getValue());
            } else {
                return o2.getValue().compareTo(o1.getValue());
            }
        });

        return standingsList.get(0).getKey();
    }
}
