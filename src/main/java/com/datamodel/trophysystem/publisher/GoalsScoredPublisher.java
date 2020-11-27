package com.datamodel.trophysystem.publisher;

import com.datamodel.leaguedatamodel.IPlayer;
import com.datamodel.trophysystem.Constants;

public class GoalsScoredPublisher extends Subject {

    private static GoalsScoredPublisher instance = new GoalsScoredPublisher();
    private IPlayer bestForward = null;

    private GoalsScoredPublisher() {

    }

    public static GoalsScoredPublisher instance() {
        return instance;
    }

    public void notifyGoalsScoredPublisher(IPlayer player) {
        subjectMap.put(Constants.PLAYER_KEY, player);
        notifyObservers();
    }

    public IPlayer getBestForward() {
        return this.bestForward;
    }

    public void setBestForward(IPlayer bestForward) {
        this.bestForward = bestForward;
    }
}
