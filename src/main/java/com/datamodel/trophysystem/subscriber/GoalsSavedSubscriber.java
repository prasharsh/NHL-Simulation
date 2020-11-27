package com.datamodel.trophysystem.subscriber;

import com.datamodel.leaguedatamodel.IPlayer;
import com.datamodel.trophysystem.Constants;
import com.datamodel.trophysystem.publisher.GoalsSavedPublisher;
import com.datamodel.trophysystem.publisher.Subject;

import java.util.*;

public class GoalsSavedSubscriber extends Observer {

    private HashMap<IPlayer, Integer> goalieStandings;

    public GoalsSavedSubscriber() {
        this.goalieStandings = new HashMap<>();
    }

    @Override
    public void update(Subject subject) {
        IPlayer player = (IPlayer) subject.getValue(Constants.PLAYER_KEY);
        if (goalieStandings.containsKey(player)) {
            goalieStandings.put(player, goalieStandings.get(player) + 1);
        } else {
            goalieStandings.put(player, 1);
        }

        IPlayer bestGoalie = getBestGoalie(Constants.SORT_DESC);
        GoalsSavedPublisher.instance().setBestGoalie(bestGoalie);
    }

    private IPlayer getBestGoalie(String sort_order) {
        if (goalieStandings.isEmpty()) {
            return null;
        }

        Set<Map.Entry<IPlayer, Integer>> entrySet = goalieStandings.entrySet();
        List<Map.Entry<IPlayer, Integer>> standingsList = new LinkedList<>(entrySet);
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
