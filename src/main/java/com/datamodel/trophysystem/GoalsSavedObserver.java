package com.datamodel.trophysystem;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.datamodel.leaguedatamodel.IPlayer;

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

        if (player == null) {
            this.goalieStandings.clear();
        } else {
            if (this.goalieStandings.containsKey(player)) {
                this.goalieStandings.put(player, this.goalieStandings.get(player) + 1);
            } else {
                this.goalieStandings.put(player, 1);
            }

            IPlayer bestGoalie = getBestGoalie(SORT_DESC);
            GoalsSavedSubject.instance().setBestGoalie(bestGoalie);
        }
    }

    private IPlayer getBestGoalie(String sort_order) {
        if (this.goalieStandings.isEmpty()) {
            return null;
        }

        Set<Map.Entry<IPlayer, Integer>> entrySet = this.goalieStandings.entrySet();
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
