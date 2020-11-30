package com.datamodel.trophysystem;

import java.util.Collections;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

import org.apache.log4j.Logger;

import com.app.main.Main;
import com.datamodel.leaguedatamodel.IHeadCoach;
import com.datamodel.leaguedatamodel.IPlayer;
import com.datamodel.leaguedatamodel.ITeam;
import com.inputoutputmodel.IDisplayToUser;
import com.inputoutputmodel.InputOutputModelAbstractFactory;

public class Trophy implements ITrophy {

	final static Logger logger = Logger.getLogger(Trophy.class);

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
		InputOutputModelAbstractFactory ioFactory = InputOutputModelAbstractFactory.instance();
		IDisplayToUser displayToUser = ioFactory.createDisplayToUser();
		try {

			logger.info("Maurice Richard Trophy " + currentYear + " is awarded to: " + bestForward.getPlayerName());
			logger.info("Rob Hawkey Memorial Cup " + currentYear + " is awarded to: " + bestDefense.getPlayerName());
			logger.info("Vezina Trophy " + currentYear + " is awarded to: " + bestGoalie.getPlayerName());
			logger.info("Jack Adam's Award " + currentYear + " is awarded to: " + bestCoach.getHeadCoachName());

			displayToUser.displayMsgToUser("Maurice Richard Trophy " + currentYear + " is awarded to: " + bestForward.getPlayerName());
			displayToUser.displayMsgToUser("Rob Hawkey Memorial Cup " + currentYear + " is awarded to: " + bestDefense.getPlayerName());
			displayToUser.displayMsgToUser("Vezina Trophy " + currentYear + " is awarded to: " + bestGoalie.getPlayerName());
			displayToUser.displayMsgToUser("Jack Adam's Award " + currentYear + " is awarded to: " + bestCoach.getHeadCoachName());

			vezinaTrophyHistory.put(currentYear, bestGoalie);
			jackAdamTrophyHistory.put(currentYear, bestCoach);
			mauriceRichardTrophyHistory.put(currentYear, bestForward);
			robHawkeyMemorialCupHistory.put(currentYear, bestDefense);
		} catch (Exception e) {
			logger.warn("No observations found");
		}
	}

	@Override
	public void awardFirstYearTrophies(int currentYear) {
		IPlayer bestGoalie = goalsSavedSubject.getBestGoalie();
		IPlayer bestForward = goalsScoredSubject.getBestForward();
		IPlayer bestDefense = playerPenaltySubject.getBestDefense();
		int randomChance = (int) (Math.random() * 4) + 1;
		IPlayer bestPlayer;
		InputOutputModelAbstractFactory ioFactory = InputOutputModelAbstractFactory.instance();
		IDisplayToUser displayToUser = ioFactory.createDisplayToUser();
		if (randomChance == 1) {
			bestPlayer = bestGoalie;
		} else if (randomChance == 2) {
			bestPlayer = bestDefense;
		} else {
			bestPlayer = bestForward;
		}

		logger.info("Calder Memorial Trophy " + currentYear + " is awarded to: " + bestPlayer.getPlayerName());
		displayToUser.displayMsgToUser("Calder Memorial Trophy " + currentYear + " is awarded to: " + bestPlayer.getPlayerName());
		calderMemorialTrophyHistory.put(currentYear, bestPlayer);
	}

	@Override
	public void displayHistoricalTrophies(int currentYear) {
		InputOutputModelAbstractFactory ioFactory = InputOutputModelAbstractFactory.instance();
		IDisplayToUser displayToUser = ioFactory.createDisplayToUser();
		logger.info("--------------------- Awards History till " + currentYear + " ---------------------");

		logger.info("--------- Calder Memorial Trophy History ---------");
		displayToUser.displayMsgToUser("--------------------- Awards History till " + currentYear + " ---------------------");

		displayToUser.displayMsgToUser("--------- Calder Memorial Trophy History ---------");
		Iterator bestPlayerAwards = calderMemorialTrophyHistory.entrySet().iterator();
		while(bestPlayerAwards.hasNext()) {
			Map.Entry award = (Map.Entry) bestPlayerAwards.next();
			IPlayer player = (IPlayer) award.getValue();
			logger.info(award.getKey() + " - " + player.getPlayerName());
			displayToUser.displayMsgToUser(award.getKey() + " - " + player.getPlayerName());
		}

		logger.info("------- President's Trophy History -----------");
		displayToUser.displayMsgToUser("------- President's Trophy History -----------");
		Iterator bestTeamAwards = presidentTrophyHistory.entrySet().iterator();
		while(bestTeamAwards.hasNext()) {
			Map.Entry award = (Map.Entry) bestTeamAwards.next();
			ITeam team = (ITeam) award.getValue();
			logger.info(award.getKey() + " - " + team.getTeamName());
			displayToUser.displayMsgToUser(award.getKey() + " - " + team.getTeamName());
		}
		logger.info("----------- Vezina Trophy History -----------");
		displayToUser.displayMsgToUser("----------- Vezina Trophy History -----------");
		Iterator bestGoalieAwards = vezinaTrophyHistory.entrySet().iterator();
		while(bestGoalieAwards.hasNext()) {
			Map.Entry award = (Map.Entry) bestGoalieAwards.next();
			IPlayer player = (IPlayer) award.getValue();
			logger.info(award.getKey() + " - " + player.getPlayerName());
			displayToUser.displayMsgToUser(award.getKey() + " - " + player.getPlayerName());
		}

		logger.info("------------ Jack Adam's Award History -----------");
		displayToUser.displayMsgToUser("------------ Jack Adam's Award History -----------");
		Iterator bestCoachAwards = jackAdamTrophyHistory.entrySet().iterator();
		while(bestCoachAwards.hasNext()) {
			Map.Entry award = (Map.Entry) bestCoachAwards.next();
			IHeadCoach coach = (IHeadCoach) award.getValue();
			logger.info(award.getKey() + " - " + coach.getHeadCoachName());
			displayToUser.displayMsgToUser(award.getKey() + " - " + coach.getHeadCoachName());
		}

		logger.info("-------- Maurice Richard Trophy History ------");
		displayToUser.displayMsgToUser("-------- Maurice Richard Trophy History ------");
		Iterator bestForwardAwards = mauriceRichardTrophyHistory.entrySet().iterator();
		while(bestForwardAwards.hasNext()) {
			Map.Entry award = (Map.Entry) bestForwardAwards.next();
			IPlayer player = (IPlayer) award.getValue();
			logger.info(award.getKey() + " - " + player.getPlayerName());
			displayToUser.displayMsgToUser(award.getKey() + " - " + player.getPlayerName());
		}

		logger.info("----- Rob Hawkey Memorial Cup History --------");
		displayToUser.displayMsgToUser("----- Rob Hawkey Memorial Cup History --------");
		Iterator bestDefenseAwards = robHawkeyMemorialCupHistory.entrySet().iterator();
		while(bestDefenseAwards.hasNext()) {
			Map.Entry award = (Map.Entry) bestDefenseAwards.next();
			IPlayer player = (IPlayer) award.getValue();
			logger.info(award.getKey() + " - " + player.getPlayerName());
			displayToUser.displayMsgToUser(award.getKey() + " - " + player.getPlayerName());
		}

		logger.info("--------- Participation Award History ------- ");
		displayToUser.displayMsgToUser("--------- Participation Award History ------- ");
		Iterator participationAwards = participationAwardHistory.entrySet().iterator();
		while(participationAwards.hasNext()) {
			Map.Entry award = (Map.Entry) participationAwards.next();
			ITeam team = (ITeam) award.getValue();
			logger.info(award.getKey() + " - " + team.getTeamName());
			displayToUser.displayMsgToUser(award.getKey() + " - " + team.getTeamName());
		}
	}

	@Override
	public void awardRegularSeasonTrophies(int currentYear) {
		InputOutputModelAbstractFactory ioFactory = InputOutputModelAbstractFactory.instance();
		IDisplayToUser displayToUser = ioFactory.createDisplayToUser();
		ITeam bestTeam = teamStandingSubject.getBestTeam();
		ITeam lowestTeam = teamStandingSubject.getLeastTeam();
		try {
			logger.info("President's Trophy " + currentYear + " is awarded to: " + bestTeam.getTeamName());
			logger.info("Participation Award " + currentYear + " is awarded to: " + lowestTeam.getTeamName());
			displayToUser.displayMsgToUser("President's Trophy " + currentYear + " is awarded to: " + bestTeam.getTeamName());
			displayToUser.displayMsgToUser("Participation Award " + currentYear + " is awarded to: " + lowestTeam.getTeamName());
			presidentTrophyHistory.put(currentYear, bestTeam);
			participationAwardHistory.put(currentYear, bestTeam);
		} catch (Exception e) {
			logger.warn("No observations found");
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
