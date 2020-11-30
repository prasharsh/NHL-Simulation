package com.datamodeltest.leaguedatamodeltest;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import com.datamodel.leaguedatamodel.CreateTeam;
import com.datamodel.leaguedatamodel.FreeAgent;
import com.datamodel.leaguedatamodel.IConference;
import com.datamodel.leaguedatamodel.IDivision;
import com.datamodel.leaguedatamodel.ILeague;
import com.datamodel.leaguedatamodel.IPlayer;
import com.datamodel.leaguedatamodel.ITeam;
import com.datamodel.leaguedatamodel.LeagueDataModelAbstractFactory;
import com.datamodel.leaguedatamodel.Team;

public class CreateTeamTest {

    private LeagueDataModelAbstractFactory leagueDataModelAbstractFactory = LeagueDataModelAbstractFactory.instance();
    private ILeague league = leagueDataModelAbstractFactory.createLeague();
    private IConference conference = leagueDataModelAbstractFactory.createConference();
    private IDivision division = leagueDataModelAbstractFactory.createDivision();
    private ITeam team = leagueDataModelAbstractFactory.createTeam();

    @BeforeClass
    public static void setFactory() {
        LeagueDataModelAbstractFactory.setFactory(new LeagueDataModelFactoryTest());
    }


    @Test
    public void getConferencesWhenNoConferencesTest() {
        Assert.assertEquals(new ArrayList<IConference>(), league.getConferences());
    }

    @Test
    public void getDivisionsWhenNoDivisionsTest() {
        Assert.assertEquals(new ArrayList<IDivision>(), conference.getDivisions());
    }

    @Test
    public void getTeamsWhenNoTeamsTest() {
        Assert.assertEquals(new ArrayList<ITeam>(), division.getTeams());
    }

    @Test
    public void addSingleTeamTest() {
        ITeam team = new Team();
        team.setTeamName("Boston");
        division.addTeam(team);
        Assert.assertEquals(team, division.getTeams().get(0));
    }

    @Test
    public void convertFreeAgentToPlayerTest() {
        IPlayer freeAgent = new FreeAgent();
        freeAgent.setPlayerName("player1");
        freeAgent.setPlayerPosition("goalie");
        CreateTeam createteam = new CreateTeam();
        createteam.addFreeAgentToTeam(freeAgent, team);
        Assert.assertEquals(team.getPlayers().get(0).getPlayerName(), "player1");
        Assert.assertEquals(0, team.getPlayers().get(0).getPlayerChecking());
    }

    @Test
    public void getRankedFreeAgentsTest() {
        IPlayer freeAgent1 = leagueDataModelAbstractFactory.createFreeAgent();
        freeAgent1.setPlayerName("player1");
        freeAgent1.setPlayerPosition("goalie");
        freeAgent1.setPlayerAgeYear(23);
        freeAgent1.setPlayerAgeDays(260);
        freeAgent1.setPlayerChecking(10);
        freeAgent1.setPlayerSaving(12);
        freeAgent1.setPlayerShooting(8);
        freeAgent1.setPlayerSkating(4);
        IPlayer freeAgent2 = leagueDataModelAbstractFactory.createFreeAgent();
        freeAgent2.setPlayerName("player1");
        freeAgent2.setPlayerPosition("goalie");
        freeAgent2.setPlayerAgeYear(23);
        freeAgent2.setPlayerAgeDays(260);
        freeAgent2.setPlayerChecking(10);
        freeAgent2.setPlayerSaving(19);
        freeAgent2.setPlayerShooting(18);
        freeAgent2.setPlayerSkating(14);
        List<IPlayer> myAgents = new ArrayList<>();
        myAgents.add(freeAgent1);
        myAgents.add(freeAgent2);
        CreateTeam team = new CreateTeam();
        List<IPlayer> newAgents = team.getRankedFreeAgents(myAgents);
        Assert.assertEquals(freeAgent2.getPlayerName(), newAgents.get(0).getPlayerName());
        Assert.assertEquals(freeAgent1.getPlayerName(), newAgents.get(1).getPlayerName());
    }
}