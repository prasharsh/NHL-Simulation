package com.datamodeltest.leaguedatamodeltest;
import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import com.datamodel.leaguedatamodel.Conference;
import com.datamodel.leaguedatamodel.Division;
import com.datamodel.leaguedatamodel.IDivision;

public class ConferenceTest {

	@Test
	public void getConferenceNameTest() {
		Conference conference = new Conference();
		conference.setConferenceName("Eastern Conference");
		Assert.assertEquals("Eastern Conference", conference.getConferenceName());
	}

	@Test
	public void getDivisionsWhenNoDivisionsTest() {
		Conference conference = new Conference();
		Assert.assertEquals(new ArrayList<IDivision>(), conference.getDivisions());
	}

	@Test
	public void addNullDivisionTest() {
		Conference conference = new Conference();
		Assert.assertFalse("Division cannot be null", conference.addDivision(null));
	}

	@Test
	public void addDivisionWithNullDivisionNameTest() {
		Conference conference = new Conference();
		IDivision division = new Division();
		division.setDivisionName(null);
		Assert.assertFalse("Division with null division name cannot not be inserted", conference.addDivision(division));
	}

	@Test
	public void addDivisionWithEmptyDivisionNameTest() {
		Conference conference = new Conference();
		IDivision division = new Division();
		division.setDivisionName("");
		Assert.assertFalse("Division with empty division name cannot not be inserted",
				conference.addDivision(division));
	}

	@Test
	public void addSingleDivisionTest() {
		Conference conference = new Conference();
		IDivision division = new Division();
		division.setDivisionName("Atlantic");
		conference.addDivision(division);
		Assert.assertEquals(division, conference.getDivisions().get(0));
	}

	@Test
	public void addDivisionWithExistingDivisionNameTest() {
		Conference conference = new Conference();
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
		Conference conference = new Conference();
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