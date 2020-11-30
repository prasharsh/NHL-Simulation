package com.datamodeltest.trophysystemtest;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import com.datamodel.leaguedatamodel.IPlayer;
import com.datamodel.leaguedatamodel.Player;
import com.datamodel.trophysystem.Observer;
import com.datamodel.trophysystem.PlayerPenaltyObserver;
import com.datamodel.trophysystem.PlayerPenaltySubject;
import com.datamodel.trophysystem.Subject;

public class PlayerPenaltyTest {

    private static final Subject playerPenalty = PlayerPenaltySubject.instance();

    private static final Observer playerPenaltyObserver = new PlayerPenaltyObserver();

    @BeforeClass
    public static void attachObservers() {
        playerPenalty.attach(playerPenaltyObserver);
    }

    @Test
    public void setBestDefenseTest() {
        IPlayer player1 = new Player();
        player1.setPlayerName("player1");
        PlayerPenaltySubject penaltyPublisher = PlayerPenaltySubject.instance();
        penaltyPublisher.setBestDefense(player1);
        IPlayer bestDefense = penaltyPublisher.getBestDefense();
        Assert.assertSame(player1, bestDefense);
    }

    @Test
    public void getBestDefenseTest() {
        IPlayer player1 = new Player();
        IPlayer player2 = new Player();
        player1.setPlayerName("player1");
        player1.setPlayerName("player2");
        PlayerPenaltySubject penaltyPublisher = PlayerPenaltySubject.instance();
        penaltyPublisher.notifyPenaltyPublisher(player2);
        penaltyPublisher.notifyPenaltyPublisher(player2);
        penaltyPublisher.notifyPenaltyPublisher(player1);
        IPlayer bestDefense = penaltyPublisher.getBestDefense();
        Assert.assertSame(player2, bestDefense);
    }

    @Test
    public void resetStandingTest() {
        IPlayer player1 = new Player();
        player1.setPlayerName("player1");
        PlayerPenaltySubject penaltyPublisher = PlayerPenaltySubject.instance();
        penaltyPublisher.setBestDefense(player1);
        penaltyPublisher.resetDefenseStandings();
        IPlayer defense = penaltyPublisher.getBestDefense();
        Assert.assertNull(defense);
    }
}
