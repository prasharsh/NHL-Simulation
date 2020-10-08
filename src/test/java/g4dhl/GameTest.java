package g4dhl;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;

public class GameTest {

    @Test
    public void getLeaguesWhenNoLeaguesTest()
    {
        IGame game = new Game();
        Assert.assertEquals(new ArrayList<ILeague>(), game.getLeagues());
    }

    @Test
    public void addNullLeagueTest()
    {
        IGame game = new Game();
        Assert.assertFalse("League cannot be null", game.addLeague(null));
    }

    @Test
    public void addLeagueWithNullLeagueNameTest()
    {
        IGame game = new Game();
        ILeague league = new League();
        league.setLeagueName(null);
        Assert.assertFalse("League with null league name cannot not be inserted", game.addLeague(league));
    }

    @Test
    public void addLeagueWithEmptyLeagueNameTest()
    {
        IGame game = new Game();
        ILeague league = new League();
        league.setLeagueName("");
        Assert.assertFalse("League with empty league name cannot not be inserted", game.addLeague(league));
    }

    @Test
    public void addSingleLeagueTest()
    {
        IGame game = new Game();
        ILeague league = new League();
        league.setLeagueName("DHL");
        game.addLeague(league);
        Assert.assertEquals(league, game.getLeagues().get(0));
    }

    @Test
    public void addLeagueWithExistingLeagueNameTest()
    {
        IGame game = new Game();

        ILeague league1 = new League();
        league1.setLeagueName("DHL");
        game.addLeague(league1);

        ILeague league2 = new League();
        league2.setLeagueName("DHL");

        Assert.assertFalse("New league with same league name should not be inserted", game.addLeague(league2));
    }

    @Test
    public void addMultipleLeaguesTest()
    {
        IGame game = new Game();

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

}
