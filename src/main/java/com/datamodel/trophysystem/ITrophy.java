package com.datamodel.trophysystem;

public interface ITrophy {
    void awardSeasonalTrophies(int currentYear);
    void awardFirstYearTrophies(int currentYear);
    void displayHistoricalTrophies(int currentYear);
    void awardRegularSeasonAwards(int currentYear);
}
