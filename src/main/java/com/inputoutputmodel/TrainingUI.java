package com.inputoutputmodel;

import org.apache.log4j.Logger;

import com.app.main.Main;

public class TrainingUI implements ITrainingUI {
    
	final static Logger logger = Logger.getLogger(Main.class);

	
	@Override
    public void displayHeader(String message) {
        System.out.print("-------------------------------------------- ");
        System.out.print(message);
        System.out.println(" --------------------------------------------");
        
        logger.info("------------"+message+"-------------");
    }

    @Override
    public void displayStatUpdates(String PlayerName, String statName, int statValue) {
        System.out.println(statName + " value of " + PlayerName + " has been updated to " + statValue);
        logger.info(statName + " value of " + PlayerName + " has been updated to " + statValue);
    }
}