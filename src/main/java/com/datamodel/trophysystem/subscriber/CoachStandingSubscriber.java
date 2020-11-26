package com.datamodel.trophysystem.subscriber;

import com.datamodel.leaguedatamodel.IHeadCoach;
import com.datamodel.trophysystem.Constants;
import com.datamodel.trophysystem.publisher.Subject;

import java.util.*;

public class CoachStandingSubscriber extends Observer {

    private HashMap<IHeadCoach, Integer> coachStandings;

    public CoachStandingSubscriber() {
        this.coachStandings = new HashMap<>();
    }

    @Override
    public void update(Subject subject) {
        IHeadCoach coach = (IHeadCoach) subject.getValue(Constants.COACH_KEY);

        if (coachStandings.containsKey(coach)) {
            coachStandings.put(coach, coachStandings.get(coach) + 1);
        } else {
            coachStandings.put(coach, 1);
        }
    }

    public IHeadCoach getBestCoach() {
        return getFirstCoach(Constants.SORT_DESC);
    }

    private IHeadCoach getFirstCoach(String sort_order) {
        if (coachStandings.isEmpty()) {
            return null;
        }

        Set<Map.Entry<IHeadCoach, Integer>> entrySet = coachStandings.entrySet();
        List<Map.Entry<IHeadCoach, Integer>> standingsList = new LinkedList<>(entrySet);
        standingsList.sort((o1, o2) -> {
            if (sort_order.equals(Constants.SORT_ASC)) {
                return o1.getValue().compareTo(o2.getValue());
            } else {
                return o2.getValue().compareTo(o1.getValue());
            }
        });

        return standingsList.get(0).getKey();
    }
}
