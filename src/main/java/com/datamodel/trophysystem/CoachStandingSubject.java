package com.datamodel.trophysystem;

import com.datamodel.leaguedatamodel.IHeadCoach;

public class CoachStandingSubject extends Subject {

    private static final String COACH_KEY = "coach";

    private static CoachStandingSubject instance = new CoachStandingSubject();
    private IHeadCoach bestCoach = null;

    private CoachStandingSubject() {

    }

    public static CoachStandingSubject instance() {
        return instance;
    }

    public void notifyCoachStanding(IHeadCoach coach) {
        subjectMap.put(COACH_KEY, coach);
        notifyObservers();
    }

    public IHeadCoach getBestCoach() {
        return this.bestCoach;
    }

    public void setBestCoach(IHeadCoach bestCoach) {
        this.bestCoach = bestCoach;
    }
}
