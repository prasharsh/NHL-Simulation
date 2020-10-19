package g4dhl;

public class Training implements ITraining {
    private int trainingId;
    private int daysUntilStatIncreaseCheck;

    @Override
    public int getTrainingId() {
        return this.trainingId;
    }

    @Override
    public void setTrainingId(int trainingId) {
        this.trainingId = trainingId;
    }

    @Override
    public int getDaysUntilStatIncreaseCheck() {
        return this.daysUntilStatIncreaseCheck;
    }

    @Override
    public boolean setDaysUntilStatIncreaseCheck(int daysUntilStatIncrease) {
        if(isValidDaysUntilStatIncrease(daysUntilStatIncrease)) {
            this.daysUntilStatIncreaseCheck = daysUntilStatIncrease;
            return true;
        } else return false;
    }

    private boolean isValidDaysUntilStatIncrease(int days) {
        return days >= 0;
    }
}
