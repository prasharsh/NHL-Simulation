package g4dhl;

public interface IInjury {
    int getInjuryId();
    void setInjuryId(int injuryId);

    float getRandomInjuryChance();
    boolean setRandomInjuryChance(float randomInjuryChance);

    int getInjuryDaysLow();
    boolean setInjuryDaysLow(int injuryDaysLow);

    int getInjuryDaysHigh();
    boolean setInjuryDaysHigh(int injuryDaysHigh);
}
