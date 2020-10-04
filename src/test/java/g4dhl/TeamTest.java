package g4dhl;

import org.junit.Test;

import leagueModel.GeneralManager;
import leagueModel.HeadCoach;
import leagueModel.IGeneralManager;
import leagueModel.IHeadCoach;
import leagueModel.IPlayer;
import leagueModel.Team;

import org.junit.Assert;

import java.util.ArrayList;

public class TeamTest {

    @Test
    public void setTeamNameTest(){
        Team team = new Team();
        team.setTeamName("Boston");
        Assert.assertEquals("Boston", team.getTeamName());
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
    public void setPlayersTest(){
        Team team = new Team();
        ArrayList<IPlayer> players = new ArrayList<>();
        team.setPlayers(players);
        Assert.assertEquals(players, team.getPlayers());
    }

}
