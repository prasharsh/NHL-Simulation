package com.datamodeltest.trophysystemtest;

import com.datamodel.leaguedatamodel.*;
import com.datamodel.trophysystem.publisher.*;
import com.datamodel.trophysystem.subscriber.*;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

public class TrophyTest {

    private static final Subject coachStanding = CoachStandingPublisher.instance();
    private static final Subject goalsSaved = GoalsSavedPublisher.instance();
    private static final Subject goalsScored = GoalsScoredPublisher.instance();
    private static final Subject playerPenalty = PlayerPenaltyPublisher.instance();
    private static final Subject teamStanding = TeamStandingPublisher.instance();

    private static final Observer coachStandingObserver = new CoachStandingSubscriber();
    private static final Observer goalsSavedObserver = new GoalsSavedSubscriber();
    private static final Observer goalsScoredObserver = new GoalsScoredSubscriber();
    private static final Observer playerPenaltyObserver = new PlayerPenaltySubscriber();
    private static final Observer teamStandingObserver = new TeamStandingSubscriber();

    @BeforeClass
    public static void attachObservers() {
        coachStanding.attach(coachStandingObserver);
        goalsSaved.attach(goalsSavedObserver);
        goalsScored.attach(goalsScoredObserver);
        playerPenalty.attach(playerPenaltyObserver);
        teamStanding.attach(teamStandingObserver);
    }

    @Test
    public void getBestCoachTest() {
        IHeadCoach coach1 = new HeadCoach();
        IHeadCoach coach2 = new HeadCoach();
        coach1.setHeadCoachName("Henry");
        coach2.setHeadCoachName("Phil");
        CoachStandingPublisher coachStandingPublisher = CoachStandingPublisher.instance();
        coachStandingPublisher.notifyCoachStanding(coach1);
        coachStandingPublisher.notifyCoachStanding(coach2);
        coachStandingPublisher.notifyCoachStanding(coach1);
        IHeadCoach bestCoach = coachStandingPublisher.getBestCoach();
        Assert.assertSame(coach1, bestCoach);
    }

    @Test
    public void getBestForwardTest() {
        IPlayer player1 = new Player();
        IPlayer player2 = new Player();
        player1.setPlayerName("player1");
        player1.setPlayerName("player2");
        GoalsScoredPublisher goalsScoredPublisher = GoalsScoredPublisher.instance();
        goalsScoredPublisher.notifyGoalsScoredPublisher(player2);
        goalsScoredPublisher.notifyGoalsScoredPublisher(player2);
        goalsScoredPublisher.notifyGoalsScoredPublisher(player1);
        IPlayer bestForward = goalsScoredPublisher.getBestForward();
        Assert.assertSame(player2, bestForward);
    }

    @Test
    public void getBestGoalieTest() {
        IPlayer player1 = new Player();
        IPlayer player2 = new Player();
        player1.setPlayerName("player1");
        player1.setPlayerName("player2");
        GoalsSavedPublisher goalsSavedPublisher = GoalsSavedPublisher.instance();
        goalsSavedPublisher.notifyGoalsSavedPublisher(player1);
        goalsSavedPublisher.notifyGoalsSavedPublisher(player1);
        goalsSavedPublisher.notifyGoalsSavedPublisher(player2);
        IPlayer bestGoalie = goalsSavedPublisher.getBestGoalie();
        Assert.assertSame(player1, bestGoalie);
    }

    @Test
    public void getBestDefenseTest() {
        IPlayer player1 = new Player();
        IPlayer player2 = new Player();
        player1.setPlayerName("player1");
        player1.setPlayerName("player2");
        PlayerPenaltyPublisher penaltyPublisher = PlayerPenaltyPublisher.instance();
        penaltyPublisher.notifyPenaltyPublisher(player2);
        penaltyPublisher.notifyPenaltyPublisher(player2);
        penaltyPublisher.notifyPenaltyPublisher(player1);
        IPlayer bestDefense = penaltyPublisher.getBestDefense();
        Assert.assertSame(player2, bestDefense);
    }

    @Test
    public void getTeamStandingsTest() {
        ITeam team1 = new Team();
        ITeam team2 = new Team();
        ITeam team3 = new Team();
        ITeam team4 = new Team();
        team1.setTeamName("team1");
        team2.setTeamName("team2");
        team3.setTeamName("team3");
        team4.setTeamName("team4");
        TeamStandingPublisher teamStandingPublisher = TeamStandingPublisher.instance();
        teamStandingPublisher.notifyTeamStandingPublisher(team3, team1);
        teamStandingPublisher.notifyTeamStandingPublisher(team3, team2);
        teamStandingPublisher.notifyTeamStandingPublisher(team2, team1);
        teamStandingPublisher.notifyTeamStandingPublisher(team3, team1);
        ITeam bestTeam = teamStandingPublisher.getBestTeam();
        ITeam leastTeam = teamStandingPublisher.getLeastTeam();
        Assert.assertSame(team3, bestTeam);
        Assert.assertSame(team1, leastTeam);
    }
}
