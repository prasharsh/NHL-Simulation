package com.datamodel.trophysystem.publisher;

import com.datamodel.leaguedatamodel.IPlayer;
import com.datamodel.trophysystem.Constants;

public class GoalsSavedPublisher extends Subject {

    private static GoalsSavedPublisher instance = new GoalsSavedPublisher();
    private IPlayer bestGoalie = null;

    private GoalsSavedPublisher() {

    }

    public static GoalsSavedPublisher instance() {
        return instance;
    }

    public void notifyGoalsSavedPublisher(IPlayer player) {
        subjectMap.put(Constants.PLAYER_KEY, player);
        notifyObservers();
    }

    public IPlayer getBestGoalie() {
        return this.bestGoalie;
    }

    public void setBestGoalie(IPlayer player) {
        this.bestGoalie = player;
    }
}
