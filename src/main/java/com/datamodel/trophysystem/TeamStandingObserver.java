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

        if (teamWon == null && teamLost == null) {
            this.teamStandings.clear();
        } else {
            if (this.teamStandings.containsKey(teamWon)) {
                this.teamStandings.put(teamWon, this.teamStandings.get(teamWon) + 1);
            } else {
                this.teamStandings.put(teamWon, 1);
            }

            this.teamStandings.put(teamLost, this.teamStandings.getOrDefault(teamLost, 0));

            ITeam bestTeam = getBestTeam(SORT_DESC);
            ITeam leastTeam = getBestTeam(SORT_ASC);
            TeamStandingSubject.instance().setBestTeam(bestTeam);
            TeamStandingSubject.instance().setLeastTeam(leastTeam);

        }
    }

    private ITeam getBestTeam(String sort_order) {
        if (this.teamStandings.isEmpty()) {
            return null;
        }

        Set<Map.Entry<ITeam, Integer>> entrySet = this.teamStandings.entrySet();
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
