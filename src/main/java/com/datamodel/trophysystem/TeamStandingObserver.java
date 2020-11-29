package com.datamodel.trophysystem;

import com.datamodel.leaguedatamodel.ITeam;
import java.util.*;

public class TeamStandingObserver extends Observer {

    private static final String TEAM_WON_KEY = "teamWon";
    private static final String TEAM_LOST_KEY = "teamLost";
    private static final String SORT_ASC = "asc";
    private static final String SORT_DESC = "desc";

    private Map<ITeam, Integer> teamStandings;

    public TeamStandingObserver() {
        this.teamStandings = new HashMap<>();
    }

    @Override
    public void update(Subject subject) {
        ITeam teamWon = (ITeam) subject.getValue(TEAM_WON_KEY);
        ITeam teamLost = (ITeam) subject.getValue(TEAM_LOST_KEY);

        if (teamStandings.containsKey(teamWon)) {
            teamStandings.put(teamWon, teamStandings.get(teamWon) + 1);
        } else {
            teamStandings.put(teamWon, 1);
        }

        teamStandings.put(teamLost, teamStandings.getOrDefault(teamLost, 0));

        ITeam bestTeam = getBestTeam(SORT_DESC);
        ITeam leastTeam = getBestTeam(SORT_ASC);
        TeamStandingSubject.instance().setBestTeam(bestTeam);
        TeamStandingSubject.instance().setLeastTeam(leastTeam);
    }

    private ITeam getBestTeam(String sort_order) {
        if (teamStandings.isEmpty()) {
            return null;
        }

        Set<Map.Entry<ITeam, Integer>> entrySet = teamStandings.entrySet();
        List<Map.Entry<ITeam, Integer>> standingsList = new LinkedList<>(entrySet);
        standingsList.sort((o1, o2) -> {
            if (sort_order.equals(SORT_ASC)) {
                return o1.getValue().compareTo(o2.getValue());
            } else {
                return o2.getValue().compareTo(o1.getValue());
            }
        });

        return standingsList.get(0).getKey();
    }
}
