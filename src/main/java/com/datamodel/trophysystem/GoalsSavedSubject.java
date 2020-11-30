package com.datamodel.trophysystem;

import com.datamodel.leaguedatamodel.IPlayer;

public class GoalsSavedSubject extends Subject {

	private static final String GOALIE_KEY = "goalie";

	private static final GoalsSavedSubject instance = new GoalsSavedSubject();
	private IPlayer bestGoalie = null;

	private GoalsSavedSubject() {

	}

	public static GoalsSavedSubject instance() {
		return instance;
	}

	public void notifyGoalsSavedPublisher(IPlayer player) {
		subjectMap.put(GOALIE_KEY, player);
		notifyObservers();
	}

	public IPlayer getBestGoalie() {
		return this.bestGoalie;
	}

	public void setBestGoalie(IPlayer player) {
		this.bestGoalie = player;
	}

	public void resetGoalieStandings() {
		this.bestGoalie = null;
		notifyGoalsSavedPublisher(this.bestGoalie);
	}
}
