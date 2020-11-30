package com.datamodeltest.leaguedatamodeltest;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.datamodel.leaguedatamodel.IConference;
import com.datamodel.leaguedatamodel.IDivision;
import com.datamodel.leaguedatamodel.IGeneralManager;
import com.datamodel.leaguedatamodel.IHeadCoach;
import com.datamodel.leaguedatamodel.ILeague;
import com.datamodel.leaguedatamodel.IPlayer;
import com.datamodel.leaguedatamodel.ITeam;
import com.datamodel.leaguedatamodel.LeagueDataModelAbstractFactory;

public class LeagueTest {

    private LeagueDataModelAbstractFactory leagueDataModelAbstractFactory;
    private ILeague league;

    @Before
    public void createLeague() {
        LeagueDataModelAbstractFactory.setFactory(new LeagueDataModelFactoryTest());
        leagueDataModelAbstractFactory = LeagueDataModelAbstractFactory.instance();
        league = leagueDataModelAbstractFactory.createLeague();
    }

    @Test
    public void getLeagueNameTest() {
        league.setLeagueName("DHL");
        Assert.assertEquals("DHL", league.getLeagueName());
    }

    @Test
    public void getConferencesWhenNoConferencesTest() {
        Assert.assertEquals(new ArrayList<IConference>(), league.getConferences());
    }

    @Test
    public void addNullConferenceTest() {
        Assert.assertFalse("Conference cannot be null", league.addConference(null));
    }

    @Test
    public void addConferenceWithNullConferenceNameTest() {
        IConference conference = leagueDataModelAbstractFactory.createConference();
        conference.setConferenceName(null);
        Assert.assertFalse("Conference with null conference name cannot not be inserted",
                league.addConference(conference));
    }

    @Test
    public void addConferenceWithEmptyConferenceNameTest() {
        IConference conference = leagueDataModelAbstractFactory.createConference();
        conference.setConferenceName("");
        Assert.assertFalse("Conference with empty conference name cannot not be inserted",
                league.addConference(conference));
    }

    @Test
    public void addSingleConferenceTest() {
        IConference conference = leagueDataModelAbstractFactory.createConference();
        conference.setConferenceName("DHL");
        league.addConference(conference);
        Assert.assertEquals(conference, league.getConferences().get(0));
    }

    @Test
    public void addConferenceWithExistingConferenceNameTest() {
        IConference conference1 = leagueDataModelAbstractFactory.createConference();
        conference1.setConferenceName("Eastern Conference");
        league.addConference(conference1);
        IConference conference2 = leagueDataModelAbstractFactory.createConference();
        conference2.setConferenceName("Eastern Conference");
        Assert.assertFalse("New conference with same conference name cannot not be inserted", league.addConference(conference2));
    }

    @Test
    public void addMultipleConferencesTest() {
        IConference conference1 = leagueDataModelAbstractFactory.createConference();
        conference1.setConferenceName("Eastern Conference");
        IConference conference2 = leagueDataModelAbstractFactory.createConference();
        conference2.setConferenceName("Western Conference");
        List<IConference> conferences = new ArrayList<>();
        conferences.add(conference1);
        conferences.add(conference2);
        league.addConference(conference1);
        league.addConference(conference2);
        Assert.assertEquals(conferences, league.getConferences());
    }

    @Test
    public void getFreeAgentsWhenNoFreeAgentsTest() {
        Assert.assertEquals(new ArrayList<IPlayer>(), league.getFreeAgents());
    }

    @Test
    public void addNullFreeAgentTest() {
        Assert.assertFalse("Free Agent cannot be null", league.addFreeAgent(null));
    }

    @Test
    public void addFreeAgentWithNullFreeAgentNameTest() {
        IPlayer freeAgent = leagueDataModelAbstractFactory.createFreeAgent();
        freeAgent.setPlayerName(null);
        Assert.assertFalse("Free Agent with null freeAgent name cannot not be inserted",
                league.addFreeAgent(freeAgent));
    }

    @Test
    public void addFreeAgentWithEmptyFreeAgentNameTest() {
        IPlayer freeAgent = leagueDataModelAbstractFactory.createFreeAgent();
        freeAgent.setPlayerName("");
        Assert.assertFalse("Free Agent with empty freeAgent name cannot not be inserted",
                league.addFreeAgent(freeAgent));
    }

    @Test
    public void addSingleFreeAgentTest() {
        IPlayer freeAgent = leagueDataModelAbstractFactory.createFreeAgent();
        freeAgent.setPlayerName("Agent one");
        league.addFreeAgent(freeAgent);
        Assert.assertEquals(freeAgent, league.getFreeAgents().get(0));
    }

    @Test
    public void addMultipleFreeAgentsTest() {
        IPlayer freeAgent1 = leagueDataModelAbstractFactory.createFreeAgent();
        freeAgent1.setPlayerName("Agent One");
        IPlayer freeAgent2 = leagueDataModelAbstractFactory.createFreeAgent();
        freeAgent2.setPlayerName("Agent Two");
        List<IPlayer> freeAgents = new ArrayList<>();
        freeAgents.add(freeAgent1);
        freeAgents.add(freeAgent2);
        league.addFreeAgent(freeAgent1);
        league.addFreeAgent(freeAgent2);
        Assert.assertEquals(freeAgents, league.getFreeAgents());
    }

    @Test
    public void setCoachTest() {
        IHeadCoach coach = leagueDataModelAbstractFactory.createHeadCoach();
        league.setCoach(coach);
        Assert.assertEquals(coach, league.getCoaches().get(0));
    }

    @Test
    public void setManagerTest() {
        IGeneralManager manager = leagueDataModelAbstractFactory.createGeneralManager();
        league.setManager(manager);
        Assert.assertEquals(manager, league.getManagers().get(0));
    }

    @Test
    public void getAllTeamsTest() {
        IConference conference = leagueDataModelAbstractFactory.createConference();
        conference.setConferenceName("Conference1");
        IDivision division = leagueDataModelAbstractFactory.createDivision();
        division.setDivisionName("division1");
        ITeam team = leagueDataModelAbstractFactory.createTeam();
        team.setTeamName("Rob team");
        division.addTeam(team);
        conference.addDivision(division);
        league.addConference(conference);
        Assert.assertEquals(team, league.getAllTeams().get(0));
    }

    @Test
    public void getActiveStrongestFreeAgentsTest() {
        IPlayer freeAgent1 = leagueDataModelAbstractFactory.createFreeAgent();
        freeAgent1.setPlayerName("freeAgent1");
        freeAgent1.setPlayerPosition("forward");
        freeAgent1.setPlayerSaving(15);
        freeAgent1.setPlayerChecking(15);
        freeAgent1.setPlayerShooting(15);
        freeAgent1.setPlayerSkating(15);
        IPlayer freeAgent2 = leagueDataModelAbstractFactory.createFreeAgent();
        freeAgent2.setPlayerName("freeAgent2");
        freeAgent2.setPlayerPosition("forward");
        freeAgent2.setPlayerSaving(10);
        freeAgent2.setPlayerChecking(10);
        freeAgent2.setPlayerShooting(10);
        freeAgent2.setPlayerSkating(10);
        league.addFreeAgent(freeAgent1);
        league.addFreeAgent(freeAgent2);
        Assert.assertEquals(freeAgent1, league.getActiveStrongestFreeAgents("forward").get(0));
        Assert.assertEquals(freeAgent2, league.getActiveStrongestFreeAgents("forward").get(1));
    }

    @Test
    public void getStrongestTeamTest() {
        IConference conference = leagueDataModelAbstractFactory.createConference();
        conference.setConferenceName("Conference1");
        IDivision division = leagueDataModelAbstractFactory.createDivision();
        division.setDivisionName("division1");
        ITeam team = leagueDataModelAbstractFactory.createTeam();
        team.setTeamName("Rob team");
        IPlayer player = leagueDataModelAbstractFactory.createPlayer();
        player.setPlayerName("Player1");
        player.setPlayerPosition("forward");
        player.setPlayerSaving(10);
        player.setPlayerChecking(10);
        player.setPlayerShooting(10);
        player.setPlayerSkating(10);
        team.addPlayer(player);
        division.addTeam(team);
        conference.addDivision(division);
        league.addConference(conference);
        Assert.assertEquals(team, league.getStrongestTeam());
    }

    @Test
    public void getActiveFreeAgentsWithPositionTest() {
        IPlayer freeAgent1 = leagueDataModelAbstractFactory.createFreeAgent();
        freeAgent1.setPlayerName("freeAgent1");
        freeAgent1.setPlayerPosition("forward");
        IPlayer freeAgent2 = leagueDataModelAbstractFactory.createFreeAgent();
        freeAgent2.setPlayerName("freeAgent2");
        freeAgent2.setPlayerPosition("forward");
        List<IPlayer> freeAgents = new ArrayList<>();
        freeAgents.add(freeAgent1);
        freeAgents.add(freeAgent2);
        league.addFreeAgent(freeAgent1);
        league.addFreeAgent(freeAgent2);
        Assert.assertEquals(freeAgents, league.getActiveFreeAgentsWithPosition(freeAgents, "forward"));
    }

    @Test
    public void getStrongestFreeAgentTest() {
        IPlayer freeAgent1 = leagueDataModelAbstractFactory.createFreeAgent();
        freeAgent1.setPlayerName("freeAgent1");
        freeAgent1.setPlayerPosition("forward");
        freeAgent1.setPlayerSaving(15);
        freeAgent1.setPlayerChecking(15);
        freeAgent1.setPlayerShooting(15);
        freeAgent1.setPlayerSkating(15);
        IPlayer freeAgent2 = leagueDataModelAbstractFactory.createFreeAgent();
        freeAgent2.setPlayerName("freeAgent2");
        freeAgent2.setPlayerPosition("forward");
        freeAgent2.setPlayerSaving(10);
        freeAgent2.setPlayerChecking(10);
        freeAgent2.setPlayerShooting(10);
        freeAgent2.setPlayerSkating(10);
        List<IPlayer> freeAgents = new ArrayList<>();
        freeAgents.add(freeAgent1);
        freeAgents.add(freeAgent2);
        league.addFreeAgent(freeAgent1);
        league.addFreeAgent(freeAgent2);
        Assert.assertEquals(freeAgent1, league.getStrongestFreeAgent(freeAgents));
    }
}