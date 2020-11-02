package com.datamodel.leaguedatamodel;

import com.datamodel.gameplayconfig.ITrainingConfig;

public interface ITraining {
    void trainPlayers(IGame game);

    float getRandomStatIncreaseProbability();
}
