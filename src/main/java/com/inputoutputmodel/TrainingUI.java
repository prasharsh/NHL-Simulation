package com.inputoutputmodel;

public class TrainingUI implements ITrainingUI {
    @Override
    public void displayHeader(String message) {
        System.out.print("-------------------------------------------- ");
        System.out.print(message);
        System.out.println(" --------------------------------------------");
    }

    @Override
    public void displayStatUpdates(String PlayerName, String statName, int statValue) {
        System.out.println(statName + " value of " + PlayerName + " has been updated to " + statValue);
    }
}