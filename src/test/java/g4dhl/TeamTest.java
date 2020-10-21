package g4dhl;

import org.junit.Test;
import org.junit.Assert;

import java.util.ArrayList;

public class TeamTest {

    @Test
    public void getTeamNameTest(){
        Team team = new Team();
        team.setTeamName("Boston");
        Assert.assertEquals("Boston", team.getTeamName());
    }

    @Test
    public void getTeamCreatedByTest(){
        Team team = new Team();
        team.setTeamCreatedBy("user");
        Assert.assertEquals("user", team.getTeamCreatedBy());
    }

    @Test
    public void getPlayersWhenNoPlayersTest()
    {
        Team team = new Team();
        Assert.assertEquals(new ArrayList<IPlayer>(), team.getPlayers());
    }

    @Test
    public void addNullPlayerTest()
    {
        Team team = new Team();
        Assert.assertFalse("Player cannot be null", team.addPlayer(null));
    }

    @Test
    public void addPlayerWithNullPlayerNameTest()
    {
        Team team = new Team();
        IPlayer player = new Player();
        player.setPlayerName(null);
        Assert.assertFalse("Player with null player name cannot not be inserted", team.addPlayer(player));
    }

    @Test
    public void addPlayerWithEmptyPlayerNameTest()
    {
        Team team = new Team();
        IPlayer player = new Player();
        player.setPlayerName("");
        Assert.assertFalse("Player with empty player name cannot not be inserted", team.addPlayer(player));
    }

    @Test
    public void setTeamNameTest(){
        Team team = new Team();
        team.setTeamName("Boston");
        Assert.assertEquals("Boston", team.getTeamName());
    }

    @Test
    public void addSinglePlayerTest()
    {
        Team team = new Team();
        IPlayer player = new Player();
        player.setPlayerName("Rob");
        team.addPlayer(player);
        Assert.assertEquals(player, team.getPlayers().get(0));
    }

    @Test
    public void addPlayerWithExistingPlayerNameTest()
    {
        Team team = new Team();

        IPlayer player1 = new Player();
        player1.setPlayerName("Rob");
        team.addPlayer(player1);

        IPlayer player2 = new Player();
        player2.setPlayerName("Rob");

        Assert.assertFalse("New player with same player name cannot not be inserted", team.addPlayer(player2));
    }

    @Test
    public void addMultiplePlayersTest()
    {
        Team team = new Team();

        IPlayer player1 = new Player();
        player1.setPlayerName("Rob");

        IPlayer player2 = new Player();
        player2.setPlayerName("Hawkey");

        ArrayList<IPlayer> players = new ArrayList<>();

        players.add(player1);
        players.add(player2);

        team.addPlayer(player1);
        team.addPlayer(player2);

        Assert.assertEquals(players, team.getPlayers());
    }

    @Test
    public void removePlayer(){
        Team team = new Team();

        IPlayer player1 = new Player();
        player1.setPlayerName("Rob");
        team.addPlayer(player1);

        IPlayer player2 = team.removePlayer(player1);
        Assert.assertEquals(player1, player2);

    }

    @Test
    public void setGeneralManagerTest(){
        Team team = new Team();
        IGeneralManager generalManager = new GeneralManager();
        team.setGeneralManager(generalManager);
        Assert.assertEquals(generalManager, team.getGeneralManager());
    }

    @Test
    public void setHeadCoachTest(){
        Team team = new Team();
        IHeadCoach headCoach = new HeadCoach();
        team.setHeadCoach(headCoach);
        Assert.assertEquals(headCoach, team.getHeadCoach());
    }

    @Test
    public void loadPlayersDataFromDB(){
        Team team = new Team();
        GameDBMock gameDB = new GameDBMock();
        team.loadFromDB(gameDB);
        Assert.assertTrue("No players loaded from DB", team.getPlayers().size()>0);
    }

    @Test
    public void loadGeneralManagerDataFromDB(){
        Team team = new Team();
        GameDBMock gameDB = new GameDBMock();
        team.loadFromDB(gameDB);
        Assert.assertNotNull("General Manager is null", team.getGeneralManager());
    }

    @Test
    public void loadHeadCoachDataFromDB(){
        Team team = new Team();
        GameDBMock gameDB = new GameDBMock();
        team.loadFromDB(gameDB);
        Assert.assertNotNull("Head Coach is null", team.getHeadCoach());
    }

}
