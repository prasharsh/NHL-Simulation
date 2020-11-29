package com.datamodeltest.leaguedatamodeltest;
import java.util.ArrayList;
import java.util.List;

import com.datamodel.leaguedatamodel.*;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

public class ConferenceTest {

	private LeagueDataModelAbstractFactory leagueDataModelAbstractFactory = LeagueDataModelAbstractFactory.instance();
	private IConference conference = leagueDataModelAbstractFactory.createConference();

	@BeforeClass
	public static void createConference(){
		LeagueDataModelAbstractFactory.setFactory(new LeagueDataModelFactoryTest());
	}

	@Test
	public void getConferenceIdTest() {
		conference.setConferenceId(10);
		Assert.assertEquals(10,conference.getConferenceId());
	}

	@Test
	public void getConferenceNameTest() {
		conference.setConferenceName("Eastern Conference");
		Assert.assertEquals("Eastern Conference", conference.getConferenceName());
	}

	@Test
	public void getDivisionsWhenNoDivisionsTest() {
		Assert.assertEquals(new ArrayList<IDivision>(), conference.getDivisions());
	}

	@Test
	public void addNullDivisionTest() {
		Assert.assertFalse("Division cannot be null", conference.addDivision(null));
	}

	@Test
	public void addDivisionWithNullDivisionNameTest() {
		IDivision division = new Division();
		division.setDivisionName(null);
		Assert.assertFalse("Division with null division name cannot not be inserted", conference.addDivision(division));
	}

	@Test
	public void addDivisionWithEmptyDivisionNameTest() {
		IDivision division = new Division();
		division.setDivisionName("");
		Assert.assertFalse("Division with empty division name cannot not be inserted",
				conference.addDivision(division));
	}

	@Test
	public void addSingleDivisionTest() {
		IDivision division = new Division();
		division.setDivisionName("Atlantic");
		conference.addDivision(division);
		Assert.assertEquals(division, conference.getDivisions().get(0));
	}

	@Test
	public void addDivisionWithExistingDivisionNameTest() {
		IDivision division1 = new Division();
		division1.setDivisionName("Atlantic");
		conference.addDivision(division1);
		IDivision division2 = new Division();
		division2.setDivisionName("Atlantic");
		Assert.assertFalse("New division with same division name cannot not be inserted",
				conference.addDivision(division2));
	}

	@Test
	public void addMultipleDivisionsTest() {
		IDivision division1 = new Division();
		division1.setDivisionName("Atlantic");
		IDivision division2 = new Division();
		division2.setDivisionName("Pacific");
		List<IDivision> divisions = new ArrayList<>();
		divisions.add(division1);
		divisions.add(division2);
		conference.addDivision(division1);
		conference.addDivision(division2);
		Assert.assertEquals(divisions, conference.getDivisions());
	}
}