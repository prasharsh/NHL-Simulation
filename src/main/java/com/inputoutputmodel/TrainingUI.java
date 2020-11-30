package com.inputoutputmodel;

import com.app.main.Main;
import org.apache.log4j.Logger;

public class TrainingUI implements ITrainingUI {

	final static Logger logger = Logger.getLogger(Main.class);

	@Override
	public void displayHeader(String message) {
		logger.info("------------" + message + "-------------");
	}

	@Override
	public void displayStatUpdates(String PlayerName, String statName, int statValue) {
		logger.info(statName + " value of " + PlayerName + " has been updated to " + statValue);
	}
}