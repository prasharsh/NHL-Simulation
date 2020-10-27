package g4dhl;

public interface ITraining {
    int getTrainingId();
    void setTrainingId(int trainingId);

    int getDaysUntilStatIncreaseCheck();
    boolean setDaysUntilStatIncreaseCheck(int daysUntilStatIncrease);

    int getNoOfDaysTrained();
    boolean setNoOfDaysTrained(int NoOfDaysTrained);

    void increaseStatOrInjurePlayer(IGame game);
}
