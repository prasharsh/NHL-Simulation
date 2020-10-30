package com.datamodeltest.leaguedatamodeltest;

import java.util.ArrayList;

import com.datamodel.leaguedatamodel.Game;
import com.datamodel.leaguedatamodel.ILeague;
import com.datamodel.leaguedatamodel.League;
import org.junit.Assert;
import org.junit.Test;

public class GameTest {

    @Test
    public void getLeaguesWhenNoLeaguesTest()
    {
        Game game = new Game();
        Assert.assertEquals(new ArrayList<ILeague>(), game.getLeagues());
    }

    @Test
    public void addNullLeagueTest()
    {
        Game game = new Game();
        Assert.assertFalse("League cannot be null", game.addLeague(null));
    }

    @Test
    public void addLeagueWithNullLeagueNameTest()
    {
        Game game = new Game();
        ILeague league = new League();
        league.setLeagueName(null);
        Assert.assertFalse("League with null league name cannot not be inserted", game.addLeague(league));
    }

    @Test
    public void addLeagueWithEmptyLeagueNameTest()
    {
        Game game = new Game();
        ILeague league = new League();
        league.setLeagueName("");
        Assert.assertFalse("League with empty league name cannot not be inserted", game.addLeague(league));
    }

    @Test
    public void addSingleLeagueTest()
    {
        Game game = new Game();
        ILeague league = new League();
        league.setLeagueName("DHL");
        game.addLeague(league);
        Assert.assertEquals(league, game.getLeagues().get(0));
    }

    @Test
    public void addLeagueWithExistingLeagueNameTest()
    {
        Game game = new Game();

        ILeague league1 = new League();
        league1.setLeagueName("DHL");
        game.addLeague(league1);

        ILeague league2 = new League();
        league2.setLeagueName("DHL");

        Assert.assertFalse("New league with same league name cannot not be inserted", game.addLeague(league2));
    }

    @Test
    public void addMultipleLeaguesTest()
    {
        Game game = new Game();

        ILeague league1 = new League();
        league1.setLeagueName("DHL");

        ILeague league2 = new League();
        league2.setLeagueName("NHL");

        ILeague league3 = new League();
        league3.setLeagueName("PHL");

        ArrayList<ILeague> leagues = new ArrayList<>();

        leagues.add(league1);
        leagues.add(league2);
        leagues.add(league3);

        game.addLeague(league1);
        game.addLeague(league2);
        game.addLeague(league3);

        Assert.assertEquals(leagues, game.getLeagues());
    }

    @Test
    public void loadLeaguesDataFromDB(){
        Game game = new Game();
        GameDBMock gameDB = new GameDBMock();
        game.loadFromDB(gameDB);
        Assert.assertTrue("No leagues loaded from DB", game.getLeagues().size()>0);
    }

}
