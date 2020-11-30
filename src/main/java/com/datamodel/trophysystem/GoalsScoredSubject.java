package com.datamodel.trophysystem;

import com.datamodel.leaguedatamodel.IPlayer;

public class GoalsScoredSubject extends Subject {

	private static final String FORWARD_KEY = "forward";

	private static final GoalsScoredSubject instance = new GoalsScoredSubject();
	private IPlayer bestForward = null;

	private GoalsScoredSubject() {

	}

	public static GoalsScoredSubject instance() {
		return instance;
	}

	public void notifyGoalsScoredPublisher(IPlayer player) {
		subjectMap.put(FORWARD_KEY, player);
		notifyObservers();
	}

	public IPlayer getBestForward() {
		return this.bestForward;
	}

	public void setBestForward(IPlayer bestForward) {
		this.bestForward = bestForward;
	}

	public void resetForwardStandings() {
		this.bestForward = null;
		notifyGoalsScoredPublisher(this.bestForward);
	}
}
