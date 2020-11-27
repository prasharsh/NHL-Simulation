package com.datamodel.trophysystem.publisher;

import com.datamodel.leaguedatamodel.IPlayer;
import com.datamodel.trophysystem.Constants;

public class PlayerRankingPublisher extends Subject {

    private static PlayerRankingPublisher instance = new PlayerRankingPublisher();

    private PlayerRankingPublisher() {

    }

    public static PlayerRankingPublisher getInstance() {
        return instance;
    }

    public void notifyCoachPublisher(IPlayer player) {
        subjectMap.put(Constants.PLAYER_KEY, player);
        notifyObservers();
    }
}
