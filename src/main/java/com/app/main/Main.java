package com.app.main;
import com.datamodel.trophysystem.*;
import com.exception.GlobalExceptionHandler;
import org.apache.log4j.Logger;

import com.datamodel.gameplayconfig.GamePlayConfigAbstractFactory;
import com.datamodel.gameplayconfig.GamePlayConfigFactory;
import com.datamodel.leaguedatamodel.LeagueDataModelAbstractFactory;
import com.datamodel.leaguedatamodel.LeagueDataModelFactory;
import com.statemachine.IStateMachine;
import com.statemachine.StateMachineAbstractFactory;
import com.statemachine.StateMachineFactory;

public class Main {

	final static Logger logger = Logger.getLogger(Main.class);

	public static boolean isNullOrEmpty(String str) {
		return str == null || str.trim().isEmpty();
	}

	public static void main(String[] args) {
		GlobalExceptionHandler globalExceptionHandler = new GlobalExceptionHandler();
		Thread.setDefaultUncaughtExceptionHandler(globalExceptionHandler);
		String filePath = null;
		try {
			filePath = args[0];
			logger.debug("args were passed");
		} catch (ArrayIndexOutOfBoundsException ae) {
			filePath = null;
			logger.debug("No arguments passed");
		}

		Subject coachStanding = CoachStandingSubject.instance();
		Subject goalsSaved = GoalsSavedSubject.instance();
		Subject goalsScored = GoalsScoredSubject.instance();
		Subject playerPenalty = PlayerPenaltySubject.instance();
		Subject teamStanding = TeamStandingSubject.instance();

		Observer coachStandingObserver = new CoachStandingObserver();
		Observer goalsSavedObserver = new GoalsSavedObserver();
		Observer goalsScoredObserver = new GoalsScoredObserver();
		Observer playerPenaltyObserver = new PlayerPenaltyObserver();
		Observer teamStandingObserver = new TeamStandingObserver();

		coachStanding.attach(coachStandingObserver);
		goalsSaved.attach(goalsSavedObserver);
		goalsScored.attach(goalsScoredObserver);
		playerPenalty.attach(playerPenaltyObserver);
		teamStanding.attach(teamStandingObserver);

		GamePlayConfigAbstractFactory.setFactory(new GamePlayConfigFactory());
		LeagueDataModelAbstractFactory.setFactory(new LeagueDataModelFactory());
		StateMachineAbstractFactory.setFactory(new StateMachineFactory());

		StateMachineAbstractFactory stateFactory = StateMachineAbstractFactory.instance();
		IStateMachine stateMachine = stateFactory.createStateMachine(filePath);
		stateMachine.start();
	}
}