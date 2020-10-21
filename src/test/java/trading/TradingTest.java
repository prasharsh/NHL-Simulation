package trading;
import g4dhl.IPlayer;
import g4dhl.Player;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;


public class TradingTest {

    @Test
    public void sortPlayersOnStrengthInAscendingOrderTest(){

        Trading trading = new Trading();
        ArrayList<IPlayer> playersToBeSorted =  new ArrayList<>();
        int playersCount = 2;

        IPlayer player1 = new Player();
        player1.setPlayerName("Rob");
        player1.setPlayerSaving(5);
        player1.setPlayerChecking(5);
        player1.setPlayerShooting(5);
        player1.setPlayerSkating(5);

        IPlayer player2 = new Player();
        player2.setPlayerName("Hawkey");
        player2.setPlayerSaving(2);
        player2.setPlayerChecking(2);
        player2.setPlayerShooting(2);
        player2.setPlayerSkating(2);

        playersToBeSorted.add(player1);
        playersToBeSorted.add(player2);

        ArrayList<IPlayer> players = trading.sortPlayersOnStrength(playersToBeSorted, playersCount, true);
        Assert.assertTrue("Players should be sorted in ascending order", players.get(0).getPlayerStrength() <= players.get(1).getPlayerStrength());
    }

    @Test
    public void sortPlayersOnStrengthInDescendingOrderTest(){

        Trading trading = new Trading();
        ArrayList<IPlayer> playersToBeSorted =  new ArrayList<>();
        int playersCount = 2;

        IPlayer player1 = new Player();
        player1.setPlayerName("Rob");
        player1.setPlayerSaving(5);
        player1.setPlayerChecking(5);
        player1.setPlayerShooting(5);
        player1.setPlayerSkating(5);

        IPlayer player2 = new Player();
        player2.setPlayerName("Hawkey");
        player2.setPlayerSaving(2);
        player2.setPlayerChecking(2);
        player2.setPlayerShooting(2);
        player2.setPlayerSkating(2);

        playersToBeSorted.add(player1);
        playersToBeSorted.add(player2);

        ArrayList<IPlayer> players = trading.sortPlayersOnStrength(playersToBeSorted, playersCount, false);
        Assert.assertTrue("Players should be sorted in descending order", players.get(0).getPlayerStrength() >= players.get(1).getPlayerStrength());
    }
}
