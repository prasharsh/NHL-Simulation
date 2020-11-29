package com.datamodeltest.leaguedatamodeltest;

import com.datamodel.leaguedatamodel.IPlayer;
import com.datamodel.leaguedatamodel.RandomPlayer;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class RandomPlayerTest {

    @Test
    public void generatePlayerTest() {
        RandomPlayer randomPlayer = new RandomPlayer();
        String currentDate = "2020-11-21";
        int playersToGenerate = 5;
        List<IPlayer> randomPlayers = randomPlayer.getRandomPlayers(playersToGenerate,currentDate);
        Assert.assertEquals(playersToGenerate,randomPlayers.size());
    }
}