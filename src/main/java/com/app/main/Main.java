package com.app.main;

import com.datamodel.gameplayconfig.GamePlayConfigAbstractFactory;
import com.datamodel.gameplayconfig.GamePlayConfigFactory;
import com.datamodel.leaguedatamodel.LeagueDataModelAbstractFactory;
import com.datamodel.leaguedatamodel.LeagueDataModelFactory;
import com.datamodel.trophysystem.*;
import com.exception.GlobalExceptionHandler;
import com.inputoutputmodel.InputOutputModelAbstractFactory;
import com.inputoutputmodel.InputOutputModelFactory;
import com.persistencemodel.PersistenceAbstractFactory;
import com.persistencemodel.PersistenceFactory;
import com.statemachine.IStateMachine;
import com.statemachine.StateMachineAbstractFactory;
import com.statemachine.StateMachineFactory;
import org.apache.log4j.Logger;

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
		} catch(ArrayIndexOutOfBoundsException ae) {
			filePath = null;
			logger.debug("No arguments passed to the application");
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

		PersistenceAbstractFactory.setFactory(new PersistenceFactory());
		GamePlayConfigAbstractFactory.setFactory(new GamePlayConfigFactory());
		LeagueDataModelAbstractFactory.setFactory(new LeagueDataModelFactory());
		StateMachineAbstractFactory.setFactory(new StateMachineFactory());
		InputOutputModelAbstractFactory.setFactory(new InputOutputModelFactory());

		StateMachineAbstractFactory stateFactory = StateMachineAbstractFactory.instance();
		IStateMachine stateMachine = stateFactory.createStateMachine(filePath);
		stateMachine.start();
	}
}