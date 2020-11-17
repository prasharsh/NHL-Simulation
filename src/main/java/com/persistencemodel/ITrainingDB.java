package com.persistencemodel;

import com.datamodel.gameplayconfig.IGameplayConfig;
import org.json.simple.JSONObject;

public interface ITrainingDB {
    void loadTraining(JSONObject trainingObject, IGameplayConfig gameplayConfig);
}
