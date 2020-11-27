package com.datamodel.trophysystem.publisher;

import com.datamodel.leaguedatamodel.IHeadCoach;
import com.datamodel.trophysystem.Constants;

public class CoachStandingPublisher extends Subject {

    private static CoachStandingPublisher instance = new CoachStandingPublisher();
    private IHeadCoach bestCoach = null;

    private CoachStandingPublisher() {

    }

    public static CoachStandingPublisher instance() {
        return instance;
    }

    public void notifyCoachStanding(IHeadCoach coach) {
        subjectMap.put(Constants.COACH_KEY, coach);
        notifyObservers();
    }

    public IHeadCoach getBestCoach() {
        return this.bestCoach;
    }

    public void setBestCoach(IHeadCoach bestCoach) {
        this.bestCoach = bestCoach;
    }
}
