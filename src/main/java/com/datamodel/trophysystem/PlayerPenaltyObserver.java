package com.datamodel.trophysystem;

import com.datamodel.leaguedatamodel.IPlayer;

import java.util.*;

public class PlayerPenaltyObserver extends Observer {

    private static final String DEFENSE_KEY = "defense";
    private static final String SORT_ASC = "asc";
    private static final String SORT_DESC = "desc";

    private Map<IPlayer, Integer> defenseStandings;

    public PlayerPenaltyObserver() {
        this.defenseStandings = new HashMap<>();
    }

    @Override
    public void update(Subject subject) {
        IPlayer player = (IPlayer) subject.getValue(DEFENSE_KEY);
        if (defenseStandings.containsKey(player)) {
            defenseStandings.put(player, defenseStandings.get(player) + 1);
        } else {
            defenseStandings.put(player, 1);
        }
        IPlayer bestDefense = getBestDefense(SORT_DESC);
        PlayerPenaltySubject.instance().setBestDefense(bestDefense);
    }

    private IPlayer getBestDefense(String sort_order) {
        if (defenseStandings.isEmpty()) {
            return null;
        }

        Set<Map.Entry<IPlayer, Integer>> entrySet = defenseStandings.entrySet();
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
