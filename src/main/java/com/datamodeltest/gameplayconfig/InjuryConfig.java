package com.datamodeltest.gameplayconfig;

import java.sql.Date;
import java.util.Calendar;

public class InjuryConfig implements IInjuryConfig {
    private int injuryId;
    private float randomInjuryChance;
    private int injuryDaysLow;
    private int injuryDaysHigh;

    @Override
    public int getInjuryId() {
        return this.injuryId;
    }

    @Override
    public void setInjuryId(int injuryId) {
        this.injuryId = injuryId;
    }

    @Override
    public float getRandomInjuryChance() {
        return this.randomInjuryChance;
    }

    @Override
    public boolean setRandomInjuryChance(float randomInjuryChance) {
        if (isValidInjuryChance(randomInjuryChance)) {
            this.randomInjuryChance = randomInjuryChance;
            return true;
        } else
            return false;
    }

    @Override
    public int getInjuryDaysLow() {
        return this.injuryDaysLow;
    }

    @Override
    public boolean setInjuryDaysLow(int injuryDaysLow) {
        if (isValidInjuryDays(injuryDaysLow)) {
            this.injuryDaysLow = injuryDaysLow;
            return true;
        } else
            return false;
    }

    @Override
    public int getInjuryDaysHigh() {
        return this.injuryDaysHigh;
    }

    @Override
    public boolean setInjuryDaysHigh(int injuryDaysHigh) {
        if (isValidInjuryDays(injuryDaysHigh)) {
            this.injuryDaysHigh = injuryDaysHigh;
            return true;
        } else
            return false;
    }

    private boolean isValidInjuryDays(int injuryDays) {
        return injuryDays >= 0;
    }

    private boolean isValidInjuryChance(float injuryChance) {
        return injuryChance >= 0 && injuryChance <= 1;
    }

    @Override
    public Date getRecoveryDate(Date currentDate) {
        int injuredDays = (int) ((Math.random() * (injuryDaysHigh - injuryDaysLow)) + injuryDaysLow);
        Calendar cal = Calendar.getInstance();
        cal.setTime(currentDate);
        cal.add(Calendar.DAY_OF_YEAR, injuredDays);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        Date recoveryDate = new java.sql.Date(cal.getTimeInMillis());
        return recoveryDate;
    }
}
