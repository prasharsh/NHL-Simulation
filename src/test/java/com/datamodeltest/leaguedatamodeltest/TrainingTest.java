package com.datamodeltest.leaguedatamodeltest;
import static org.mockito.Mockito.when;

import java.sql.Date;
import java.util.Calendar;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

import com.datamodel.gameplayconfig.GamePlayConfig;
import com.datamodel.gameplayconfig.IGamePlayConfig;
import com.datamodel.gameplayconfig.IInjuryConfig;
import com.datamodel.gameplayconfig.InjuryConfig;
import com.datamodel.leaguedatamodel.Conference;
import com.datamodel.leaguedatamodel.Division;
import com.datamodel.leaguedatamodel.Game;
import com.datamodel.leaguedatamodel.HeadCoach;
import com.datamodel.leaguedatamodel.IConference;
import com.datamodel.leaguedatamodel.IDivision;
import com.datamodel.leaguedatamodel.IGame;
import com.datamodel.leaguedatamodel.IHeadCoach;
import com.datamodel.leaguedatamodel.ILeague;
import com.datamodel.leaguedatamodel.IPlayer;
import com.datamodel.leaguedatamodel.ITeam;
import com.datamodel.leaguedatamodel.ITraining;
import com.datamodel.leaguedatamodel.League;
import com.datamodel.leaguedatamodel.Player;
import com.datamodel.leaguedatamodel.Team;
import com.datamodel.leaguedatamodel.Training;

public class TrainingTest {

    @Test
    public void getRandomInjuryChanceTest() {
        ITraining training = new Training();
        float x = training.getRandomStatIncreaseProbability();
        Assert.assertTrue(x >= 0 && x <= 1);
    }

    @Test
    public void trainPlayersTest() {
        IGame game = initializeSampleGameWithOnePlayer();
        ITraining trainingMock = Mockito.spy(Training.class);
        when(trainingMock.getRandomStatIncreaseProbability()).thenReturn((float) 0.4);
        trainingMock.trainPlayers(game);
        IPlayer player = game.getLeagues().get(0).getConferences().get(0).getDivisions().get(0).getTeams().get(0).getPlayers().get(0);
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
        IInjuryConfig injury = new InjuryConfig();
        injury.setRandomInjuryChance((float) 0.09);
        injury.setInjuryDaysLow(2);
        injury.setInjuryDaysHigh(10);
        ITeam team = new Team();
        team.setTeamName("team1");
        IDivision division = new Division();
        division.setDivisionName("div1");
        IConference conference = new Conference();
        conference.setConferenceName("conf1");
        IGamePlayConfig config = new GamePlayConfig();
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
        league.setGamePlayConfig(config);
        league.addConference(conference);
        IGame game = new Game();
        game.addLeague(league);
        return game;
    }
}