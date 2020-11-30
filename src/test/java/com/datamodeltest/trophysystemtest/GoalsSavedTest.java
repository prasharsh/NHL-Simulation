package com.datamodeltest.trophysystemtest;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import com.datamodel.leaguedatamodel.IPlayer;
import com.datamodel.leaguedatamodel.Player;
import com.datamodel.trophysystem.GoalsSavedObserver;
import com.datamodel.trophysystem.GoalsSavedSubject;
import com.datamodel.trophysystem.Observer;
import com.datamodel.trophysystem.Subject;

public class GoalsSavedTest {

    private static final Subject goalsSaved = GoalsSavedSubject.instance();

    private static final Observer goalsSavedObserver = new GoalsSavedObserver();

    @BeforeClass
    public static void attachObservers() {
        goalsSaved.attach(goalsSavedObserver);
    }

    @Test
    public void setBestGoalieTest() {
        IPlayer player1 = new Player();
        player1.setPlayerName("player1");
        GoalsSavedSubject goalsSavedPublisher = GoalsSavedSubject.instance();
        goalsSavedPublisher.setBestGoalie(player1);
        IPlayer goalie = goalsSavedPublisher.getBestGoalie();
        Assert.assertSame(player1, goalie);
    }

    @Test
    public void getBestGoalieTest() {
        IPlayer player1 = new Player();
        IPlayer player2 = new Player();
        player1.setPlayerName("player1");
        player1.setPlayerName("player2");
        GoalsSavedSubject goalsSavedPublisher = GoalsSavedSubject.instance();
        goalsSavedPublisher.notifyGoalsSavedPublisher(player1);
        goalsSavedPublisher.notifyGoalsSavedPublisher(player1);
        goalsSavedPublisher.notifyGoalsSavedPublisher(player2);
        IPlayer bestGoalie = goalsSavedPublisher.getBestGoalie();
        Assert.assertSame(player1, bestGoalie);
    }

    @Test
    public void resetStandingTest() {
        IPlayer player1 = new Player();
        player1.setPlayerName("player1");
        GoalsSavedSubject goalsSavedPublisher = GoalsSavedSubject.instance();
        goalsSavedPublisher.setBestGoalie(player1);
        goalsSavedPublisher.resetGoalieStandings();
        IPlayer goalie = goalsSavedPublisher.getBestGoalie();
        Assert.assertNull(goalie);
    }
}
