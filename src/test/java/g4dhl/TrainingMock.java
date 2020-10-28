package g4dhl;

import java.sql.Date;
import java.util.ArrayList;

public class TrainingMock implements ITraining {

    @Override
    public int getTrainingId() {
        return 0;
    }

    @Override
    public void setTrainingId(int trainingId) {

    }

    @Override
    public int getDaysUntilStatIncreaseCheck() {
        return 0;
    }

    @Override
    public boolean setDaysUntilStatIncreaseCheck(int daysUntilStatIncrease) {
        return false;
    }

    @Override
    public int getNoOfDaysTrained() {
        return 0;
    }

    @Override
    public boolean setNoOfDaysTrained(int NoOfDaysTrained) {
        return false;
    }

    @Override
    public void increaseStatOrInjurePlayer(IGame game) {
        ILeague currentLeague = game.getLeagues().get(0);
        Date currentDate = currentLeague.getCurrentDate();
        IGameplayConfig gameplayConfig = currentLeague.getGamePlayConfig();
        ArrayList<IConference> conferencesInLeague = currentLeague.getConferences();
        for (IConference conference : conferencesInLeague) {
            ArrayList<IDivision> divisionsInConference = conference.getDivisions();
            for (IDivision division : divisionsInConference) {
                ArrayList<ITeam> teamsInDivision = division.getTeams();
                for (ITeam team : teamsInDivision) {
                    IHeadCoach headCoach = team.getHeadCoach();
                    ArrayList<IPlayer> playersInTeam = team.getPlayers();
                    for (IPlayer player : playersInTeam) {
                        updatePlayerStatus(player, headCoach, gameplayConfig, currentDate);
                    }
                }
            }
        }
    }

   private void updatePlayerStatus(IPlayer player, IHeadCoach coach, IGameplayConfig gameplayConfig, Date currentDate) {
        IInjury playerInjury = gameplayConfig.getInjury();
        float randomInjuryChance = playerInjury.getRandomInjuryChance();
        Date recoveryDate = playerInjury.getRecoveryDate(currentDate);
        float randomValue = (float) 0.5; // gave static value for testing.
        float coachSkating = coach.getHeadCoachSkating();
        float coachShooting = coach.getHeadCoachShooting();
        float coachSaving = coach.getHeadCoachSaving();
        float coachChecking = coach.getHeadCoachChecking();
        if (randomValue < coachSkating) {
            player.setPlayerSkating(player.getPlayerSkating() + 1);
        } else if (randomValue > coachSkating) {
            player.checkPlayerInjury(randomInjuryChance, recoveryDate, currentDate);
        }
        if (randomValue < coachShooting) {
            player.setPlayerShooting(player.getPlayerShooting() + 1);
        } else if (randomValue > coachShooting) {
            player.checkPlayerInjury(randomInjuryChance, recoveryDate, currentDate);
        }
        if (randomValue < coachSaving) {
            player.setPlayerSaving(player.getPlayerSaving() + 1);
        } else if (randomValue > coachSaving) {
            player.checkPlayerInjury(randomInjuryChance, recoveryDate, currentDate);
        }
        if (randomValue < coachChecking) {
            player.setPlayerChecking(player.getPlayerChecking() + 1);
        } else if (randomValue > coachChecking) {
            player.checkPlayerInjury(randomInjuryChance, recoveryDate, currentDate);
        }
    }

}
