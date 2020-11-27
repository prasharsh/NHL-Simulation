package com.datamodel.trophysystem.subscriber;

import com.datamodel.leaguedatamodel.IPlayer;
import com.datamodel.trophysystem.Constants;
import com.datamodel.trophysystem.publisher.GoalsScoredPublisher;
import com.datamodel.trophysystem.publisher.Subject;

import java.util.*;

public class GoalsScoredSubscriber extends Observer {

    private HashMap<IPlayer, Integer> forwardStandings;

    public GoalsScoredSubscriber() {
        this.forwardStandings = new HashMap<>();
    }

    @Override
    public void update(Subject subject) {
        IPlayer player = (IPlayer) subject.getValue(Constants.PLAYER_KEY);
        if (forwardStandings.containsKey(player)) {
            forwardStandings.put(player, forwardStandings.get(player) + 1);
        } else {
            forwardStandings.put(player, 1);
        }
        IPlayer bestForward = getBestForward(Constants.SORT_DESC);
        GoalsScoredPublisher.instance().setBestForward(bestForward);
    }

    private IPlayer getBestForward(String sort_order) {
        if (forwardStandings.isEmpty()) {
            return null;
        }

        Set<Map.Entry<IPlayer, Integer>> entrySet = forwardStandings.entrySet();
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
