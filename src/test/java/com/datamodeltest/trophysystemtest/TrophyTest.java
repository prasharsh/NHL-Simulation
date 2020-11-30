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

	private static final CoachStandingSubject coachStandingPublisher = CoachStandingSubject.instance();
	private static final GoalsScoredSubject goalsScoredPublisher = GoalsScoredSubject.instance();
	private static final GoalsSavedSubject goalsSavedPublisher = GoalsSavedSubject.instance();
	private static final PlayerPenaltySubject penaltyPublisher = PlayerPenaltySubject.instance();
	private static final TeamStandingSubject teamStandingPublisher = TeamStandingSubject.instance();

	@BeforeClass
	public static void attachObservers() {
		coachStanding.attach(coachStandingObserver);
		goalsSaved.attach(goalsSavedObserver);
		goalsScored.attach(goalsScoredObserver);
		playerPenalty.attach(playerPenaltyObserver);
		teamStanding.attach(teamStandingObserver);
		IHeadCoach coach1 = new HeadCoach();
		IHeadCoach coach2 = new HeadCoach();
		coach1.setHeadCoachName("Henry");
		coach2.setHeadCoachName("Phil");
		coachStandingPublisher.notifyCoachStanding(coach1);
		coachStandingPublisher.notifyCoachStanding(coach2);
		coachStandingPublisher.notifyCoachStanding(coach1);
		IPlayer player1 = new Player();
		IPlayer player2 = new Player();
		player1.setPlayerName("player1");
		player1.setPlayerName("player2");
		goalsScoredPublisher.notifyGoalsScoredPublisher(player2);
		goalsScoredPublisher.notifyGoalsScoredPublisher(player2);
		goalsScoredPublisher.notifyGoalsScoredPublisher(player1);
		IPlayer player3 = new Player();
		IPlayer player4 = new Player();
		player1.setPlayerName("player3");
		player1.setPlayerName("player4");
		goalsSavedPublisher.notifyGoalsSavedPublisher(player3);
		goalsSavedPublisher.notifyGoalsSavedPublisher(player3);
		goalsSavedPublisher.notifyGoalsSavedPublisher(player4);
		IPlayer player5 = new Player();
		IPlayer player6 = new Player();
		player1.setPlayerName("player5");
		player1.setPlayerName("player6");
		penaltyPublisher.notifyPenaltyPublisher(player6);
		penaltyPublisher.notifyPenaltyPublisher(player6);
		penaltyPublisher.notifyPenaltyPublisher(player5);
		ITeam team1 = new Team();
		ITeam team2 = new Team();
		ITeam team3 = new Team();
		ITeam team4 = new Team();
		team1.setTeamName("team1");
		team2.setTeamName("team2");
		team3.setTeamName("team3");
		team4.setTeamName("team4");
		teamStandingPublisher.notifyTeamStandingPublisher(team3, team1);
		teamStandingPublisher.notifyTeamStandingPublisher(team3, team2);
		teamStandingPublisher.notifyTeamStandingPublisher(team2, team1);
		teamStandingPublisher.notifyTeamStandingPublisher(team3, team1);
	}

	@Test
	public void resetRegularSeasonAwardsTest() {
		ITrophy trophy = new Trophy();
		trophy.resetRegularSeasonAwards();
		ITeam bestTeam = teamStandingPublisher.getBestTeam();
		ITeam leastTeam = teamStandingPublisher.getLeastTeam();
		Assert.assertNull(bestTeam);
		Assert.assertNull(leastTeam);
	}

	@Test
	public void resetSeasonalAwardsTest() {
		ITrophy trophy = new Trophy();
		trophy.resetSeasonalAwards();
		IPlayer bestGoalie = goalsSavedPublisher.getBestGoalie();
		IPlayer bestForward = goalsScoredPublisher.getBestForward();
		IPlayer bestDefense = penaltyPublisher.getBestDefense();
		IHeadCoach bestCoach = coachStandingPublisher.getBestCoach();
		Assert.assertNull(bestGoalie);
		Assert.assertNull(bestForward);
		Assert.assertNull(bestDefense);
		Assert.assertNull(bestCoach);
	}
}
