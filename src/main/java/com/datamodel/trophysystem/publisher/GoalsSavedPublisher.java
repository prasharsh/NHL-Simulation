package com.datamodel.trophysystem.publisher;

import com.datamodel.leaguedatamodel.IPlayer;
import com.datamodel.trophysystem.Constants;

public class GoalsSavedPublisher extends Subject {

    private static GoalsSavedPublisher instance = new GoalsSavedPublisher();

    private GoalsSavedPublisher() {

    }

    public static GoalsSavedPublisher getInstance() {
        return instance;
    }

    public void notifyGoalsSavedPublisher(IPlayer player) {
        subjectMap.put(Constants.PLAYER_KEY, player);
        notifyObservers();
    }
}
