package com.datamodel.trophysystem.publisher;

import com.datamodel.leaguedatamodel.IPlayer;
import com.datamodel.trophysystem.Constants;

public class PlayerPenaltyPublisher extends Subject {

    private static PlayerPenaltyPublisher instance = new PlayerPenaltyPublisher();
    private IPlayer bestDefense = null;

    private PlayerPenaltyPublisher() {

    }

    public static PlayerPenaltyPublisher instance() {
        return instance;
    }

    public void notifyPenaltyPublisher(IPlayer player) {
        subjectMap.put(Constants.PLAYER_KEY, player);
        notifyObservers();
    }

    public IPlayer getBestDefense() {
        return this.bestDefense;
    }

    public void setBestDefense(IPlayer bestDefense) {
        this.bestDefense = bestDefense;
    }
}
