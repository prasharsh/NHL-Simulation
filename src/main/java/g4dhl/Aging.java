package g4dhl;

public class Aging implements IAging {
    private int agingId;
    private int averageRetirementAge;
    private int maximumAge;

    @Override
    public int getAverageRetirementAge() {
        return this.averageRetirementAge;
    }

    @Override
    public boolean setAverageRetirementAge(int averageRetirementAge) {
        if (isValidAge(averageRetirementAge)) {
            this.averageRetirementAge = averageRetirementAge;
            return true;
        } else return false;
    }

    @Override
    public int getMaximumAge() {
        return this.maximumAge;
    }

    @Override
    public boolean setMaximumAge(int maximumAge) {
        if (isValidAge(maximumAge)) {
            this.maximumAge = maximumAge;
            return true;
        } else return false;
    }

    @Override
    public int getAgingId() {
        return this.agingId;
    }

    @Override
    public void setAgingId(int agingId) {
        this.agingId = agingId;
    }

    private boolean isValidAge(int givenAge) {
        return givenAge > 0;
    }
}
