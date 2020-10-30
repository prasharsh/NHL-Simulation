package g4dhl;

import java.sql.Date;
import java.util.Calendar;

import org.junit.Assert;
import org.junit.Test;

import static org.mockito.Mockito.*;

public class TrainingTest {

	@Test
	public void setDaysUntilStatIncreaseTest() {
		Training training = new Training();
		Assert.assertFalse("DaysUntilStatIncrease cannot be negative", training.setDaysUntilStatIncreaseCheck(-1));
	}

	@Test
	public void setValidDaysUntilStatIncreaseTest() {
		Training training = new Training();
		Assert.assertTrue("DaysUntilStatIncrease is saved", training.setDaysUntilStatIncreaseCheck(50));
	}

	@Test
	public void getTrainingIdTest() {
		Training training = new Training();
		training.setTrainingId(10);
		Assert.assertEquals(10, training.getTrainingId());
	}

	@Test
	public void getRandomInjuryChanceTest() {
		Training training = new Training();
		float x = training.getRandomStatIncreaseProbability();
		Assert.assertTrue(x >= 0 && x <= 1);
	}

	@Test
	public void getDaysUntilStatIncreaseTest() {
		Training training = new Training();
		training.setDaysUntilStatIncreaseCheck(30);
		Assert.assertEquals(30, training.getDaysUntilStatIncreaseCheck());
	}

	@Test
	public void trainPlayersTest() {
		IGame game = initializeSampleGameWithOnePlayer();
		ITraining trainingMock = game.getLeagues().get(0).getGamePlayConfig().getTraining();
		trainingMock.trainPlayers(game);
		IPlayer player = game.getLeagues().get(0).getConferences().get(0).getDivisions().get(0).getTeams().get(0)
				.getPlayers().get(0);
		Assert.assertEquals(11, player.getPlayerChecking());
		Assert.assertEquals(20, player.getPlayerSaving());
		Assert.assertEquals(11, player.getPlayerShooting());
		Assert.assertEquals(20, player.getPlayerSkating());
	}

	private IGame initializeSampleGameWithOnePlayer() {
		IPlayer player = new Player();
		player.setPlayerName("player1");
		player.setPlayerAgeYear(25);
		player.setPlayerAgeDays(280);
		player.setPlayerPosition("forward");
		player.setPlayerChecking(10);
		player.setPlayerSaving(20);
		player.setPlayerSkating(19);
		player.setPlayerShooting(10);

		IHeadCoach coach = new HeadCoach();
		coach.setHeadCoachName("coach1");
		coach.setHeadCoachChecking((float) 0.6);
		coach.setHeadCoachSaving((float) 0.6);
		coach.setHeadCoachShooting((float) 0.6);
		coach.setHeadCoachSkating((float) 0.6);
		IInjury injury = new Injury();
		injury.setRandomInjuryChance((float) 0.09);
		injury.setInjuryDaysLow(2);
		injury.setInjuryDaysHigh(10);
		ITraining trainingMock = spy(Training.class);
		when(trainingMock.getRandomStatIncreaseProbability())
				.thenReturn((float) 0.4)
				.thenCallRealMethod();
		ITeam team = new Team();
		team.setTeamName("team1");
		IDivision division = new Division();
		division.setDivisionName("div1");
		IConference conference = new Conference();
		conference.setConferenceName("conf1");
		IGameplayConfig config = new GameplayConfig();
		ILeague league = new League();
		league.setLeagueName("league1");
		int currentYear = Calendar.getInstance().get(Calendar.YEAR);
		String currentDate = currentYear + "-09-30";
		league.setCurrentDate(Date.valueOf(currentDate));
		team.setHeadCoach(coach);
		team.addPlayer(player);
		division.addTeam(team);
		conference.addDivision(division);
		config.setInjury(injury);
		config.setTraining(trainingMock);
		league.setGamePlayConfig(config);
		league.addConference(conference);
		IGame game = new Game();
		game.addLeague(league);
		return game;
	}
}
