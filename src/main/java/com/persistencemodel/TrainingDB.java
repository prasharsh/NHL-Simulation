package com.persistencemodel;

import com.datamodel.gameplayconfig.IGameplayConfig;
import com.datamodel.gameplayconfig.ITrainingConfig;
import com.datamodel.gameplayconfig.TrainingConfig;
import org.json.simple.JSONObject;

public class TrainingDB implements ITrainingDB {

    @Override
    public void loadTraining(JSONObject trainingObject, IGameplayConfig gameplayConfig) {
        ITrainingConfig training = new TrainingConfig();
        training.setTrainingId((int) (long) trainingObject.get("trainingId"));
        training.setDaysUntilStatIncreaseCheck((int) (long) trainingObject.get("daysUntilStatIncreaseCheck"));
        training.setNoOfDaysTrained((int) (long) trainingObject.get("noOfDaysTrained"));
        gameplayConfig.setTraining(training);
    }
}
