package com.datamodeltest.trophysystemtest;

import com.datamodel.leaguedatamodel.*;
import com.datamodel.trophysystem.*;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

public class TrophyTest {

    private static final Subject coachStanding = CoachStandingSubject.instance();
    private static final Subject goalsSaved = GoalsSavedSubject.instance();
    private static final Subject goalsScored = GoalsScoredSubject.instance();
    private static final Subject playerPenalty = PlayerPenaltySubject.instance();
    private static final Subject teamStanding = TeamStandingSubject.instance();

    private static final Observer coachStandingObserver = new CoachStandingObserver();
    private static final Observer goalsSavedObserver = new GoalsSavedObserver();
    private static final Observer goalsScoredObserver = new GoalsScoredObserver();
    private static final Observer playerPenaltyObserver = new PlayerPenaltyObserver();
    private static final Observer teamStandingObserver = new TeamStandingObserver();

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
        CoachStandingSubject coachStandingPublisher = CoachStandingSubject.instance();
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
        GoalsScoredSubject goalsScoredPublisher = GoalsScoredSubject.instance();
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
        GoalsSavedSubject goalsSavedPublisher = GoalsSavedSubject.instance();
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
        PlayerPenaltySubject penaltyPublisher = PlayerPenaltySubject.instance();
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
        TeamStandingSubject teamStandingPublisher = TeamStandingSubject.instance();
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
