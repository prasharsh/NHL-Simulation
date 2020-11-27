package com.datamodel.trophysystem.publisher;

import com.datamodel.leaguedatamodel.IHeadCoach;
import com.datamodel.trophysystem.Constants;

public class CoachStandingPublisher extends Subject {

    private static CoachStandingPublisher instance = new CoachStandingPublisher();

    private CoachStandingPublisher() {

    }

    public static CoachStandingPublisher getInstance() {
        return instance;
    }

    public void notifyCoachPublisher(IHeadCoach coach) {
        subjectMap.put(Constants.COACH_KEY, coach);
        notifyObservers();
    }
}
