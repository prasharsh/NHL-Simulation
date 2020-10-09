package g4dhl;

import java.util.ArrayList;

import org.junit.Assert;
import org.junit.Test;

public class DivisionTest {

	@Test
	public void setDivisionNameTest() {
		Division division = new Division();
		division.setDivisionName("Atlantic");
		Assert.assertEquals("Atlantic", division.getDivisionName());
	}

	@Test
	public void setTeamsTest() {
		Division division = new Division();
		ArrayList<ITeam> teams = new ArrayList<>();
		division.setTeams(teams);
		Assert.assertEquals(teams, division.getTeams());
	}
}
