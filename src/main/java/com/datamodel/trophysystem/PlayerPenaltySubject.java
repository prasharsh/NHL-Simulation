package com.datamodel.trophysystem;

import com.datamodel.leaguedatamodel.IPlayer;

public class PlayerPenaltySubject extends Subject {

    private static final String DEFENSE_KEY = "defense";

    private static PlayerPenaltySubject instance = new PlayerPenaltySubject();
    private IPlayer bestDefense = null;

    private PlayerPenaltySubject() {

    }

    public static PlayerPenaltySubject instance() {
        return instance;
    }

    public void notifyPenaltyPublisher(IPlayer player) {
        subjectMap.put(DEFENSE_KEY, player);
        notifyObservers();
    }

    public IPlayer getBestDefense() {
        return this.bestDefense;
    }

    public void setBestDefense(IPlayer bestDefense) {
        this.bestDefense = bestDefense;
    }

    public void reset() {
        this.bestDefense = null;
        notifyPenaltyPublisher(bestDefense);
    }
}
