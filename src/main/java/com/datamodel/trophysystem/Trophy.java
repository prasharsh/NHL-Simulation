package com.datamodel.trophysystem;

import com.datamodel.leaguedatamodel.IHeadCoach;
import com.datamodel.leaguedatamodel.IPlayer;
import com.datamodel.leaguedatamodel.ITeam;

import java.util.Collections;
import java.util.Map;
import java.util.TreeMap;

public class Trophy implements ITrophy {

    private static GoalsSavedSubject goalsSavedSubject = GoalsSavedSubject.instance();
    private static GoalsScoredSubject goalsScoredSubject = GoalsScoredSubject.instance();
    private static PlayerPenaltySubject playerPenaltySubject = PlayerPenaltySubject.instance();
    private static TeamStandingSubject teamStandingSubject = TeamStandingSubject.instance();
    private static CoachStandingSubject coachStandingSubject = CoachStandingSubject.instance();

    private Map<Integer, Object> calderMemorialTrophyHistory = new TreeMap<>(Collections.reverseOrder());
    private Map<Integer, Object> presidentTrophyHistory = new TreeMap<>(Collections.reverseOrder());
    private Map<Integer, Object> vezinaTrophyHistory = new TreeMap<>(Collections.reverseOrder());
    private Map<Integer, Object> jackAdamTrophyHistory = new TreeMap<>(Collections.reverseOrder());
    private Map<Integer, Object> mauriceRichardTrophyHistory = new TreeMap<>(Collections.reverseOrder());
    private Map<Integer, Object> robHawkeyMemorialCupHistory = new TreeMap<>(Collections.reverseOrder());
    private Map<Integer, Object> participationAwardHistory = new TreeMap<>(Collections.reverseOrder());

    @Override
    public void awardSeasonalTrophies(int currentYear) {
        IPlayer bestGoalie = goalsSavedSubject.getBestGoalie();
        IPlayer bestForward = goalsScoredSubject.getBestForward();
        IHeadCoach bestCoach = coachStandingSubject.getBestCoach();
        IPlayer bestDefense = playerPenaltySubject.getBestDefense();
        System.out.println("best forward====" + bestForward.getPlayerName());
        System.out.println("best defense====" + bestDefense.getPlayerName());
        System.out.println("best goalie====" + bestGoalie.getPlayerName());
        System.out.println("best coach====" + bestCoach.getHeadCoachName());
        vezinaTrophyHistory.put(currentYear, bestGoalie);
        jackAdamTrophyHistory.put(currentYear, bestCoach);
        mauriceRichardTrophyHistory.put(currentYear, bestForward);
        robHawkeyMemorialCupHistory.put(currentYear, bestDefense);
        goalsSavedSubject.reset();
        goalsScoredSubject.reset();
        coachStandingSubject.reset();
        playerPenaltySubject.reset();
    }

    @Override
    public void awardFirstYearTrophies(int currentYear) {
        IPlayer player = goalsSavedSubject.getBestGoalie();
        System.out.println("Calder Memorial Trophy-------------" + player.getPlayerName() + "----------------");
        calderMemorialTrophyHistory.put(currentYear, player);
    }

    @Override
    public void displayHistoricalTrophies(int currentYear) {
        System.out.println("---------------------all awards------------");
        System.out.println(calderMemorialTrophyHistory);
        System.out.println(presidentTrophyHistory);
        System.out.println(vezinaTrophyHistory);
        System.out.println(jackAdamTrophyHistory);
        System.out.println(mauriceRichardTrophyHistory);
        System.out.println(robHawkeyMemorialCupHistory);
        System.out.println(participationAwardHistory);
    }

    @Override
    public void awardRegularSeasonAwards(int currentYear) {
        ITeam bestTeam = teamStandingSubject.getBestTeam();
        ITeam lowestTeam = teamStandingSubject.getLeastTeam();
        System.out.println("best team----" + bestTeam.getTeamName() + "--");
        System.out.println("lowest team----" + lowestTeam.getTeamName() + "--");
        presidentTrophyHistory.put(currentYear, bestTeam);
        participationAwardHistory.put(currentYear, bestTeam);
        teamStandingSubject.resetStandings();
    }
}
