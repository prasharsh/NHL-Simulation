package com.persistencemodel;

import com.datamodel.gameplayconfig.IGamePlayConfig;
import org.json.simple.JSONObject;

public interface ITrainingDB {
    void loadTraining(JSONObject trainingObject, IGamePlayConfig gameplayConfig);
}
