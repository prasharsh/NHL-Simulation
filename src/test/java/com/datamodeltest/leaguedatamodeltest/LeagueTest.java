
package com.datamodeltest.leaguedatamodeltest;

import java.util.ArrayList;
import org.junit.Assert;
import org.junit.Test;
import com.datamodel.leaguedatamodel.Conference;
import com.datamodel.leaguedatamodel.GeneralManager;
import com.datamodel.leaguedatamodel.HeadCoach;
import com.datamodel.leaguedatamodel.IConference;
import com.datamodel.leaguedatamodel.IGeneralManager;
import com.datamodel.leaguedatamodel.IHeadCoach;
import com.datamodel.leaguedatamodel.IPlayer;
import com.datamodel.leaguedatamodel.League;
import com.datamodel.leaguedatamodel.Player;

public class LeagueTest {

	@Test
	public void getLeagueNameTest() {
		League league = new League();
		league.setLeagueName("DHL");
		Assert.assertEquals("DHL", league.getLeagueName());
	}

	@Test
	public void getConferencesWhenNoConferencesTest() {
		League league = new League();
		Assert.assertEquals(new ArrayList<IConference>(), league.getConferences());
	}

	@Test
	public void addNullConferenceTest() {
		League league = new League();
		Assert.assertFalse("Conference cannot be null", league.addConference(null));
	}

	@Test
	public void addConferenceWithNullConferenceNameTest() {
		League league = new League();
		IConference conference = new Conference();
		conference.setConferenceName(null);
		Assert.assertFalse("Conference with null conference name cannot not be inserted",
				league.addConference(conference));
	}

	@Test
	public void addConferenceWithEmptyConferenceNameTest() {
		League league = new League();
		IConference conference = new Conference();
		conference.setConferenceName("");
		Assert.assertFalse("Conference with empty conference name cannot not be inserted",
				league.addConference(conference));
	}

	@Test
	public void addSingleConferenceTest() {
		League league = new League();
		IConference conference = new Conference();
		conference.setConferenceName("DHL");
		league.addConference(conference);
		Assert.assertEquals(conference, league.getConferences().get(0));
	}

	@Test
	public void addConferenceWithExistingConferenceNameTest() {
		League league = new League();

		IConference conference1 = new Conference();
		conference1.setConferenceName("Eastern Conference");
		league.addConference(conference1);

		IConference conference2 = new Conference();
		conference2.setConferenceName("Eastern Conference");

		Assert.assertFalse("New conference with same conference name cannot not be inserted",
				league.addConference(conference2));
	}

	@Test
	public void addMultipleConferencesTest() {
		League league = new League();

		IConference conference1 = new Conference();
		conference1.setConferenceName("Eastern Conference");

		IConference conference2 = new Conference();
		conference2.setConferenceName("Western Conference");

		ArrayList<IConference> conferences = new ArrayList<>();

		conferences.add(conference1);
		conferences.add(conference2);

		league.addConference(conference1);
		league.addConference(conference2);

		Assert.assertEquals(conferences, league.getConferences());
	}

	@Test
	public void getFreeAgentsWhenNoFreeAgentsTest() {
		League league = new League();
		Assert.assertEquals(new ArrayList<IPlayer>(), league.getFreeAgents());
	}

	@Test
	public void addNullFreeAgentTest() {
		League league = new League();
		Assert.assertFalse("Free Agent cannot be null", league.addFreeAgent(null));
	}

	@Test
	public void addFreeAgentWithNullFreeAgentNameTest() {
		League league = new League();
		IPlayer freeAgent = new Player();
		freeAgent.setPlayerName(null);
		Assert.assertFalse("Free Agent with null freeAgent name cannot not be inserted",
				league.addFreeAgent(freeAgent));
	}

	@Test
	public void addFreeAgentWithEmptyFreeAgentNameTest() {
		League league = new League();
		IPlayer freeAgent = new Player();
		freeAgent.setPlayerName("");
		Assert.assertFalse("Free Agent with empty freeAgent name cannot not be inserted",
				league.addFreeAgent(freeAgent));
	}

	@Test
	public void addSingleFreeAgentTest() {
		League league = new League();
		IPlayer freeAgent = new Player();
		freeAgent.setPlayerName("Agent one");
		league.addFreeAgent(freeAgent);
		Assert.assertEquals(freeAgent, league.getFreeAgents().get(0));
	}

	@Test
	public void addMultipleFreeAgentsTest() {
		League league = new League();

		IPlayer freeAgent1 = new Player();
		freeAgent1.setPlayerName("Agent One");

		IPlayer freeAgent2 = new Player();
		freeAgent2.setPlayerName("Agent Two");

		ArrayList<IPlayer> freeAgents = new ArrayList<>();

		freeAgents.add(freeAgent1);
		freeAgents.add(freeAgent2);

		league.addFreeAgent(freeAgent1);
		league.addFreeAgent(freeAgent2);

		Assert.assertEquals(freeAgents, league.getFreeAgents());
	}

	@Test
	public void setCoachTest() {
		League league = new League();
		IHeadCoach coach = new HeadCoach();
		league.setCoach(coach);
		Assert.assertEquals(coach, league.getCoaches().get(0));
	}

	@Test
	public void setManagerTest() {
		League league = new League();
		IGeneralManager manager = new GeneralManager();
		league.setManager(manager);
		Assert.assertEquals(manager, league.getManagers().get(0));
	}

}