package com.datamodel.leaguedatamodel;
import com.datamodel.gameplayconfig.GamePlayConfigAbstractFactory;
import com.datamodel.gameplayconfig.GamePlayConfigFactory;
import org.apache.log4j.Logger;

import com.statemachine.IStateMachine;
import com.statemachine.StateMachine;

public class Main {

	final static Logger logger = Logger.getLogger(Main.class);
	
	public static boolean isNullOrEmpty(String str) {
		return str == null || str.trim().isEmpty();
	}

	public static void main(String[] args) {
		Handler globalExceptionHandler = new Handler();
		Thread.setDefaultUncaughtExceptionHandler(globalExceptionHandler);
		String filePath = null;
		try {
			filePath = args[0];
			logger.debug("args were passed");
		} catch (ArrayIndexOutOfBoundsException ae) {
			filePath = null;
			logger.debug("No arguments passed");
		}
		GamePlayConfigAbstractFactory.setFactory(new GamePlayConfigFactory());
		LeagueDataModelAbstractFactory.setFactory(new LeagueDataModelFactory());
		IStateMachine stateMachine = StateMachine.getInstance(filePath);
		stateMachine.start();
	}
}