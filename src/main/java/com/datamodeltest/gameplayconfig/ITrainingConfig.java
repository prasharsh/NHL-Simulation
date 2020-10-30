package com.datamodeltest.gameplayconfig;

import com.datamodeltest.leaguedatamodel.IGame;

public interface ITrainingConfig {
    int getTrainingId();

    void setTrainingId(int trainingId);

    int getDaysUntilStatIncreaseCheck();

    boolean setDaysUntilStatIncreaseCheck(int daysUntilStatIncrease);

    int getNoOfDaysTrained();

    boolean setNoOfDaysTrained(int NoOfDaysTrained);

    void trainPlayers(IGame game);

    float getRandomStatIncreaseProbability();
}
