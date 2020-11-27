package com.datamodel.trophysystem.subscriber;

import com.datamodel.leaguedatamodel.IPlayer;
import com.datamodel.trophysystem.Constants;
import com.datamodel.trophysystem.publisher.GoalsScoredPublisher;
import com.datamodel.trophysystem.publisher.PlayerPenaltyPublisher;
import com.datamodel.trophysystem.publisher.Subject;

import java.util.*;

public class PlayerPenaltySubscriber extends Observer {

    private HashMap<IPlayer, Integer> defenseStandings;

    public PlayerPenaltySubscriber() {
        this.defenseStandings = new HashMap<>();
    }

    @Override
    public void update(Subject subject) {
        IPlayer player = (IPlayer) subject.getValue(Constants.PLAYER_KEY);
        if (defenseStandings.containsKey(player)) {
            defenseStandings.put(player, defenseStandings.get(player) + 1);
        } else {
            defenseStandings.put(player, 1);
        }
        IPlayer bestDefense = getBestDefense(Constants.SORT_DESC);
        PlayerPenaltyPublisher.instance().setBestDefense(bestDefense);
    }

    private IPlayer getBestDefense(String sort_order) {
        if (defenseStandings.isEmpty()) {
            return null;
        }

        Set<Map.Entry<IPlayer, Integer>> entrySet = defenseStandings.entrySet();
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
