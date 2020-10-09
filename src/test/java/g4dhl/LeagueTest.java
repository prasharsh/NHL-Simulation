package g4dhl;

import java.util.ArrayList;

import org.junit.Assert;
import org.junit.Test;

public class LeagueTest {

	@Test
	public void getLeagueNameTest() {
		League league = new League();
		league.setLeagueName("Dalhousie Hockey League");
		Assert.assertEquals("Dalhousie Hockey League", league.getLeagueName());
	}

	@Test
	public void setConferencesTest() {
		League league = new League();
		ArrayList<IConference> conferences = new ArrayList<>();
		league.setConferences(conferences);
		Assert.assertEquals(conferences, league.getConferences());
	}

	@Test
	public void setFreeAgentsTest() {
		League league = new League();
		ArrayList<IFreeAgent> freeAgents = new ArrayList<>();
		league.setFreeAgents(freeAgents);
		Assert.assertEquals(freeAgents, league.getFreeAgents());
	}
}
