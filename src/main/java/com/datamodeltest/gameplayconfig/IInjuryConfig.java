package com.datamodeltest.gameplayconfig;

import java.sql.Date;

public interface IInjuryConfig {
    int getInjuryId();

    void setInjuryId(int injuryId);

    float getRandomInjuryChance();

    boolean setRandomInjuryChance(float randomInjuryChance);

    int getInjuryDaysLow();

    boolean setInjuryDaysLow(int injuryDaysLow);

    int getInjuryDaysHigh();

    boolean setInjuryDaysHigh(int injuryDaysHigh);

    Date getRecoveryDate(Date currentDate);
}
