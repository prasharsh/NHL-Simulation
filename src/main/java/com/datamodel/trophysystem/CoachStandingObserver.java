package com.datamodel.trophysystem;

import com.datamodel.leaguedatamodel.IHeadCoach;

import java.util.*;

public class CoachStandingObserver extends Observer {

	private static final String COACH_KEY = "coach";
	private static final String SORT_ASC = "asc";
	private static final String SORT_DESC = "desc";

	private final Map<IHeadCoach, Integer> coachStandings;

	public CoachStandingObserver() {
		this.coachStandings = new HashMap<>();
	}

	@Override
	public void update(Subject subject) {
		IHeadCoach coach = (IHeadCoach) subject.getValue(COACH_KEY);

		if(coach == null) {
			this.coachStandings.clear();
		} else {
			if(this.coachStandings.containsKey(coach)) {
				this.coachStandings.put(coach, this.coachStandings.get(coach) + 1);
			} else {
				this.coachStandings.put(coach, 1);
			}

			IHeadCoach bestCoach = getBestCoach(SORT_DESC);
			CoachStandingSubject.instance().setBestCoach(bestCoach);
		}
	}

	private IHeadCoach getBestCoach(String sort_order) {
		if(this.coachStandings.isEmpty()) {
			return null;
		}

		Set<Map.Entry<IHeadCoach, Integer>> entrySet = this.coachStandings.entrySet();
		List<Map.Entry<IHeadCoach, Integer>> standingsList = new LinkedList<>(entrySet);
		standingsList.sort((o1, o2) -> {
			if(sort_order.equals(SORT_ASC)) {
				return o1.getValue().compareTo(o2.getValue());
			} else {
				return o2.getValue().compareTo(o1.getValue());
			}
		});

		return standingsList.get(0).getKey();
	}
}
