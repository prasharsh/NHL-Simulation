package com.datamodel.trophysystem;

import com.datamodel.leaguedatamodel.IHeadCoach;
import com.datamodel.leaguedatamodel.IPlayer;
import com.datamodel.leaguedatamodel.ITeam;

import java.util.Collections;
import java.util.Iterator;
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
        try {

            System.out.println("Maurice Richard Trophy " + currentYear + " is awarded to: " + bestForward.getPlayerName());
            System.out.println("Rob Hawkey Memorial Cup " + currentYear + " is awarded to: " + bestDefense.getPlayerName());
            System.out.println("Vezina Trophy " + currentYear + " is awarded to: " + bestGoalie.getPlayerName());
            System.out.println("Jack Adam's Award " + currentYear + " is awarded to: " + bestCoach.getHeadCoachName());

            vezinaTrophyHistory.put(currentYear, bestGoalie);
            jackAdamTrophyHistory.put(currentYear, bestCoach);
            mauriceRichardTrophyHistory.put(currentYear, bestForward);
            robHawkeyMemorialCupHistory.put(currentYear, bestDefense);
        } catch (Exception e) {
            System.out.println("No observations found");
        }
    }

    @Override
    public void awardFirstYearTrophies(int currentYear) {
        IPlayer bestGoalie = goalsSavedSubject.getBestGoalie();
        IPlayer bestForward = goalsScoredSubject.getBestForward();
        IPlayer bestDefense = playerPenaltySubject.getBestDefense();
        int randomChance = (int) (Math.random() * 4) + 1;
        IPlayer bestPlayer;
        if (randomChance == 1) {
            bestPlayer = bestGoalie;
        } else if (randomChance == 2) {
            bestPlayer = bestDefense;
        } else {
            bestPlayer = bestForward;
        }

        System.out.println("Calder Memorial Trophy " + currentYear + " is awarded to: " + bestPlayer.getPlayerName());

        calderMemorialTrophyHistory.put(currentYear, bestPlayer);
    }

    @Override
    public void displayHistoricalTrophies(int currentYear) {
        System.out.println("--------------------- Awards History till " + currentYear + " ---------------------");

        System.out.println("--------- Calder Memorial Trophy History ---------");
        Iterator bestPlayerAwards = calderMemorialTrophyHistory.entrySet().iterator();
        while(bestPlayerAwards.hasNext()) {
            Map.Entry award = (Map.Entry) bestPlayerAwards.next();
            IPlayer player = (IPlayer) award.getValue();
            System.out.println(award.getKey() + " - " + player.getPlayerName());
        }

        System.out.println("------- President's Trophy History -----------");
        Iterator bestTeamAwards = presidentTrophyHistory.entrySet().iterator();
        while(bestTeamAwards.hasNext()) {
            Map.Entry award = (Map.Entry) bestTeamAwards.next();
            ITeam team = (ITeam) award.getValue();
            System.out.println(award.getKey() + " - " + team.getTeamName());
        }
        System.out.println("----------- Vezina Trophy History -----------");
        Iterator bestGoalieAwards = vezinaTrophyHistory.entrySet().iterator();
        while(bestGoalieAwards.hasNext()) {
            Map.Entry award = (Map.Entry) bestGoalieAwards.next();
            IPlayer player = (IPlayer) award.getValue();
            System.out.println(award.getKey() + " - " + player.getPlayerName());
        }

        System.out.println("------------ Jack Adam's Award History -----------");
        Iterator bestCoachAwards = jackAdamTrophyHistory.entrySet().iterator();
        while(bestCoachAwards.hasNext()) {
            Map.Entry award = (Map.Entry) bestCoachAwards.next();
            IHeadCoach coach = (IHeadCoach) award.getValue();
            System.out.println(award.getKey() + " - " + coach.getHeadCoachName());
        }

        System.out.println("-------- Maurice Richard Trophy History ------");
        Iterator bestForwardAwards = mauriceRichardTrophyHistory.entrySet().iterator();
        while(bestForwardAwards.hasNext()) {
            Map.Entry award = (Map.Entry) bestForwardAwards.next();
            IPlayer player = (IPlayer) award.getValue();
            System.out.println(award.getKey() + " - " + player.getPlayerName());
        }

        System.out.println("----- Rob Hawkey Memorial Cup History --------");
        Iterator bestDefenseAwards = robHawkeyMemorialCupHistory.entrySet().iterator();
        while(bestDefenseAwards.hasNext()) {
            Map.Entry award = (Map.Entry) bestDefenseAwards.next();
            IPlayer player = (IPlayer) award.getValue();
            System.out.println(award.getKey() + " - " + player.getPlayerName());
        }

        System.out.println("--------- Participation Award History ------- ");
        Iterator participationAwards = participationAwardHistory.entrySet().iterator();
        while(participationAwards.hasNext()) {
            Map.Entry award = (Map.Entry) participationAwards.next();
            ITeam team = (ITeam) award.getValue();
            System.out.println(award.getKey() + " - " + team.getTeamName());
        }
    }

    @Override
    public void awardRegularSeasonTrophies(int currentYear) {
        ITeam bestTeam = teamStandingSubject.getBestTeam();
        ITeam lowestTeam = teamStandingSubject.getLeastTeam();
        try {
            System.out.println("President's Trophy " + currentYear + " is awarded to: " + bestTeam.getTeamName());
            System.out.println("Participation Award " + currentYear + " is awarded to: " + lowestTeam.getTeamName());
            presidentTrophyHistory.put(currentYear, bestTeam);
            participationAwardHistory.put(currentYear, bestTeam);
        } catch (Exception e) {
            System.out.println("No observations found");
        }
    }

    @Override
    public void resetRegularSeasonAwards() {
        teamStandingSubject.resetTeamStandings();
    }

    @Override
    public void resetSeasonalAwards() {
        goalsSavedSubject.resetGoalieStandings();
        goalsScoredSubject.resetForwardStandings();
        coachStandingSubject.resetCoachStandings();
        playerPenaltySubject.resetDefenseStandings();
    }
}
