package com.datamodel.trophysystem.publisher;

import com.datamodel.leaguedatamodel.IPlayer;
import com.datamodel.trophysystem.Constants;

public class GoalsScoredPublisher extends Subject {

    private static GoalsScoredPublisher instance = new GoalsScoredPublisher();

    private GoalsScoredPublisher() {

    }

    public static GoalsScoredPublisher getInstance() {
        return instance;
    }

    public void notifyGoalsSavedPublisher(IPlayer player) {
        subjectMap.put(Constants.PLAYER_KEY, player);
        notifyObservers();
    }
}
