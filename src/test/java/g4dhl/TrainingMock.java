package g4dhl;

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
        IGameplayConfig gameplayConfig = currentLeague.getGamePlayConfig();
        ArrayList<IConference> conferencesInLeague = currentLeague.getConferences();
        for (IConference conference : conferencesInLeague) {
            ArrayList<IDivision> divisionsInConference = conference.getDivisions();
            for (IDivision division : divisionsInConference) {
                ArrayList<ITeam> teamsInDivision = division.getTeams();
                for (ITeam team : teamsInDivision) {
                    IHeadCoach headCoach = team.getHeadCoach();
                    ArrayList<IPlayer> playersInTeam= team.getPlayers();
                    for (IPlayer player : playersInTeam) {
                        updatePlayerStatus(player, headCoach, gameplayConfig);
                    }
                }
            }
        }
    }

    private void updatePlayerStatus(IPlayer player, IHeadCoach coach, IGameplayConfig gameplayConfig) {
        IInjury playerInjury = gameplayConfig.getInjury();
        float randomValue = (float) 0.5; // gave static value for testing.
        boolean isPlayerInjured = player.isPlayerInjured();
        float coachSkating = coach.getHeadCoachSkating();
        float coachShooting = coach.getHeadCoachShooting();
        float coachSaving = coach.getHeadCoachSaving();
        float coachChecking = coach.getHeadCoachChecking();
        if (!isPlayerInjured || randomValue <= coachSkating) {
            player.setPlayerSkating(player.getPlayerSkating() + 1);
        } else if (isPlayerInjured || randomValue > coachSkating) {
            isPlayerInjured = true;
            player.checkPlayerInjury(playerInjury.getRandomInjuryChance());
        }
        if (!isPlayerInjured || randomValue <= coachShooting) {
            player.setPlayerShooting(player.getPlayerShooting() + 1);
        } else if (isPlayerInjured || randomValue > coachShooting) {
            isPlayerInjured = true;
            player.checkPlayerInjury(playerInjury.getRandomInjuryChance());
        }
        if (!isPlayerInjured || randomValue <= coachSaving) {
            player.setPlayerSaving(player.getPlayerSaving() + 1);
        } else if (isPlayerInjured || randomValue > coachSaving) {
            isPlayerInjured = true;
            player.checkPlayerInjury(playerInjury.getRandomInjuryChance());
        }
        if (!isPlayerInjured || randomValue <= coachChecking) {
            player.setPlayerChecking(player.getPlayerChecking() + 1);
        } else if (isPlayerInjured || randomValue > coachChecking) {
            isPlayerInjured = true;
            player.checkPlayerInjury(playerInjury.getRandomInjuryChance());
        }
    }
}
