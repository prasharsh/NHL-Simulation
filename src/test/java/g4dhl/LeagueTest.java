package g4dhl;

import java.util.ArrayList;

import org.junit.Assert;
import org.junit.Test;

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
		Assert.assertEquals(new ArrayList<IFreeAgent>(), league.getFreeAgents());
	}

	@Test
	public void addNullFreeAgentTest() {
		League league = new League();
		Assert.assertFalse("Free Agent cannot be null", league.addFreeAgent(null));
	}

	@Test
	public void addFreeAgentWithNullFreeAgentNameTest() {
		League league = new League();
		IFreeAgent freeAgent = new FreeAgent();
		freeAgent.setFreeAgentName(null);
		Assert.assertFalse("Free Agent with null freeAgent name cannot not be inserted",
				league.addFreeAgent(freeAgent));
	}

	@Test
	public void addFreeAgentWithEmptyFreeAgentNameTest() {
		League league = new League();
		IFreeAgent freeAgent = new FreeAgent();
		freeAgent.setFreeAgentName("");
		Assert.assertFalse("Free Agent with empty freeAgent name cannot not be inserted",
				league.addFreeAgent(freeAgent));
	}

	@Test
	public void addSingleFreeAgentTest() {
		League league = new League();
		IFreeAgent freeAgent = new FreeAgent();
		freeAgent.setFreeAgentName("Agent one");
		league.addFreeAgent(freeAgent);
		Assert.assertEquals(freeAgent, league.getFreeAgents().get(0));
	}

	@Test
	public void addFreeAgentWithExistingFreeAgentNameTest() {
		League league = new League();

		IFreeAgent freeAgent1 = new FreeAgent();
		freeAgent1.setFreeAgentName("Agent One");
		league.addFreeAgent(freeAgent1);

		IFreeAgent freeAgent2 = new FreeAgent();
		freeAgent2.setFreeAgentName("Agent One");

		Assert.assertFalse("New Free Agent with same FreeAgent name cannot not be inserted",
				league.addFreeAgent(freeAgent2));
	}

	@Test
	public void addMultipleFreeAgentsTest() {
		League league = new League();

		IFreeAgent freeAgent1 = new FreeAgent();
		freeAgent1.setFreeAgentName("Agent One");

		IFreeAgent freeAgent2 = new FreeAgent();
		freeAgent2.setFreeAgentName("Agent Two");

		ArrayList<IFreeAgent> freeAgents = new ArrayList<>();

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

	@Test
	public void loadConferencesDataFromDB() {
		League league = new League();
		GameDBMock gameDB = new GameDBMock();
		league.loadFromDB(gameDB);
		Assert.assertTrue("No conferences loaded from DB", league.getConferences().size() > 0);
	}

}
