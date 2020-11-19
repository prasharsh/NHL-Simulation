package com.datamodel.leaguedatamodel;
import org.apache.log4j.Logger;

import com.statemachine.IStateMachine;
import com.statemachine.StateMachine;

public class Main {

	final static Logger logger = Logger.getLogger(Main.class);
	
	public static boolean isNullOrEmpty(String str) {
		return str == null || str.trim().isEmpty();
	}

	public static void main(String[] args) {
		String filePath = null;
		try {
			filePath = args[0];
			logger.debug("args were passed");
		} catch (ArrayIndexOutOfBoundsException ae) {
			filePath = null;
			logger.debug("No arguments passed");
		}
		IStateMachine stateMachine = new StateMachine(filePath);
		stateMachine.start();
	}
}