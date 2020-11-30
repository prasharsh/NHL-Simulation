package com.datamodeltest.trophysystemtest;

import com.datamodel.leaguedatamodel.IPlayer;
import com.datamodel.leaguedatamodel.Player;
import com.datamodel.trophysystem.GoalsScoredObserver;
import com.datamodel.trophysystem.GoalsScoredSubject;
import com.datamodel.trophysystem.Observer;
import com.datamodel.trophysystem.Subject;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

public class GoalsScoredTest {

	private static final Subject goalsScored = GoalsScoredSubject.instance();

	private static final Observer goalsScoredObserver = new GoalsScoredObserver();

	@BeforeClass
	public static void attachObservers() {
		goalsScored.attach(goalsScoredObserver);
	}

	@Test
	public void setBestForwardTest() {
		IPlayer player1 = new Player();
		player1.setPlayerName("player1");
		GoalsScoredSubject goalsScoredPublisher = GoalsScoredSubject.instance();
		goalsScoredPublisher.setBestForward(player1);
		IPlayer forward = goalsScoredPublisher.getBestForward();
		Assert.assertSame(player1, forward);
	}

	@Test
	public void getBestForwardTest() {
		IPlayer player1 = new Player();
		IPlayer player2 = new Player();
		player1.setPlayerName("player1");
		player1.setPlayerName("player2");
		GoalsScoredSubject goalsScoredPublisher = GoalsScoredSubject.instance();
		goalsScoredPublisher.notifyGoalsScoredPublisher(player2);
		goalsScoredPublisher.notifyGoalsScoredPublisher(player2);
		goalsScoredPublisher.notifyGoalsScoredPublisher(player1);
		IPlayer bestForward = goalsScoredPublisher.getBestForward();
		Assert.assertSame(player2, bestForward);
	}

	@Test
	public void resetStandingTest() {
		IPlayer player1 = new Player();
		player1.setPlayerName("player1");
		GoalsScoredSubject goalsScoredPublisher = GoalsScoredSubject.instance();
		goalsScoredPublisher.notifyGoalsScoredPublisher(player1);
		goalsScoredPublisher.resetForwardStandings();
		IPlayer forward = goalsScoredPublisher.getBestForward();
		Assert.assertNull(forward);
	}
}
