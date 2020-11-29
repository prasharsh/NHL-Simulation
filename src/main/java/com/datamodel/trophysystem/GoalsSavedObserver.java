package com.datamodel.trophysystem;

import com.datamodel.leaguedatamodel.IPlayer;
import java.util.*;

public class GoalsSavedObserver extends Observer {

    private static final String GOALIE_KEY = "goalie";
    private static final String SORT_ASC = "asc";
    private static final String SORT_DESC = "desc";

    private Map<IPlayer, Integer> goalieStandings;

    public GoalsSavedObserver() {
        this.goalieStandings = new HashMap<>();
    }

    @Override
    public void update(Subject subject) {
        IPlayer player = (IPlayer) subject.getValue(GOALIE_KEY);
        if (goalieStandings.containsKey(player)) {
            goalieStandings.put(player, goalieStandings.get(player) + 1);
        } else {
            goalieStandings.put(player, 1);
        }

        IPlayer bestGoalie = getBestGoalie(SORT_DESC);
        GoalsSavedSubject.instance().setBestGoalie(bestGoalie);
    }

    private IPlayer getBestGoalie(String sort_order) {
        if (goalieStandings.isEmpty()) {
            return null;
        }

        Set<Map.Entry<IPlayer, Integer>> entrySet = goalieStandings.entrySet();
        List<Map.Entry<IPlayer, Integer>> standingsList = new LinkedList<>(entrySet);
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
