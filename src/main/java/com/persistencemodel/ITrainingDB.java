package com.persistencemodel;

import org.json.simple.JSONObject;

import com.datamodel.gameplayconfig.IGamePlayConfig;

public interface ITrainingDB {
    void loadTraining(JSONObject trainingObject, IGamePlayConfig gameplayConfig);
}
