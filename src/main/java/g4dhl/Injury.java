package g4dhl;

public class Injury implements IInjury {
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
        if(isValidInjuryChance(randomInjuryChance)) {
            this.randomInjuryChance = randomInjuryChance;
            return true;
        } else return false;
    }

    @Override
    public int getInjuryDaysLow() {
        return this.injuryDaysLow;
    }

    @Override
    public boolean setInjuryDaysLow(int injuryDaysLow) {
        if(isValidInjuryDays(injuryDaysLow)) {
            this.injuryDaysLow = injuryDaysLow;
            return true;
        } else return false;
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
        } else return false;
    }

    private boolean isValidInjuryDays(int injuryDays) {
        return injuryDays >= 0;
    }

    private boolean isValidInjuryChance(float injuryChance) {
        return injuryChance >= 0 && injuryChance <= 1;
    }
}
