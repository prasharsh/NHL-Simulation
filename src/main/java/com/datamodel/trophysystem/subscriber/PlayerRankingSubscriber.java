package com.datamodel.trophysystem.subscriber;

import com.datamodel.leaguedatamodel.IPlayer;
import com.datamodel.trophysystem.Constants;
import com.datamodel.trophysystem.publisher.Subject;

import java.util.HashMap;

public class PlayerRankingSubscriber extends Observer {

    private HashMap<IPlayer, Integer> playerStandings;

    public PlayerRankingSubscriber() {
        this.playerStandings = new HashMap<>();
    }

    @Override
    public void update(Subject subject) {
        IPlayer player = (IPlayer) subject.getValue(Constants.PLAYER_KEY);
        if (playerStandings.containsKey(player)) {
            playerStandings.put(player, playerStandings.get(player) + 1);
        } else {
            playerStandings.put(player, 1);
        }
    }
}
