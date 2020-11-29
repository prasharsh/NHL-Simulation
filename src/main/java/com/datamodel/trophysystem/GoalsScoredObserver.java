package com.datamodel.trophysystem;

import com.datamodel.leaguedatamodel.IPlayer;
import java.util.*;

public class GoalsScoredObserver extends Observer {

    private static final String FORWARD_KEY = "forward";
    private static final String SORT_ASC = "asc";
    private static final String SORT_DESC = "desc";

    private Map<IPlayer, Integer> forwardStandings;

    public GoalsScoredObserver() {
        this.forwardStandings = new HashMap<>();
    }

    @Override
    public void update(Subject subject) {
        IPlayer player = (IPlayer) subject.getValue(FORWARD_KEY);
        if (forwardStandings.containsKey(player)) {
            forwardStandings.put(player, forwardStandings.get(player) + 1);
        } else {
            forwardStandings.put(player, 1);
        }
        IPlayer bestForward = getBestForward(SORT_DESC);
        GoalsScoredSubject.instance().setBestForward(bestForward);
    }

    private IPlayer getBestForward(String sort_order) {
        if (forwardStandings.isEmpty()) {
            return null;
        }

        Set<Map.Entry<IPlayer, Integer>> entrySet = forwardStandings.entrySet();
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
