package com.datamodel.trophysystem.publisher;

import com.datamodel.leaguedatamodel.IPlayer;
import com.datamodel.trophysystem.Constants;

public class PlayerPenaltyPublisher extends Subject {

    private static PlayerPenaltyPublisher instance = new PlayerPenaltyPublisher();

    private PlayerPenaltyPublisher() {

    }

    public static PlayerPenaltyPublisher getInstance() {
        return instance;
    }

    public void notifyCoachPublisher(IPlayer player) {
        subjectMap.put(Constants.PLAYER_KEY, player);
        notifyObservers();
    }
}
