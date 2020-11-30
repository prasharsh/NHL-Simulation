package com.datamodeltest.leaguedatamodeltest;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import com.datamodel.leaguedatamodel.Division;
import com.datamodel.leaguedatamodel.IConference;
import com.datamodel.leaguedatamodel.IDivision;
import com.datamodel.leaguedatamodel.LeagueDataModelAbstractFactory;

public class ConferenceTest {

    private LeagueDataModelAbstractFactory leagueDataModelAbstractFactory = LeagueDataModelAbstractFactory.instance();
    private IConference conference = leagueDataModelAbstractFactory.createConference();
    private IDivision division = leagueDataModelAbstractFactory.createDivision();

    @BeforeClass
    public static void setFactory() {
        LeagueDataModelAbstractFactory.setFactory(new LeagueDataModelFactoryTest());
    }

    @Test
    public void getConferenceIdTest() {
        conference.setConferenceId(10);
        Assert.assertEquals(10, conference.getConferenceId());
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
        division.setDivisionName(null);
        Assert.assertFalse("Division with null division name cannot not be inserted", conference.addDivision(division));
    }

    @Test
    public void addDivisionWithEmptyDivisionNameTest() {
        division.setDivisionName("");
        Assert.assertFalse("Division with empty division name cannot not be inserted",
                conference.addDivision(division));
    }

    @Test
    public void addSingleDivisionTest() {
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
        IDivision division1 = leagueDataModelAbstractFactory.createDivision();
        division1.setDivisionName("Atlantic");
        IDivision division2 = leagueDataModelAbstractFactory.createDivision();
        division2.setDivisionName("Pacific");
        List<IDivision> divisions = new ArrayList<>();
        divisions.add(division1);
        divisions.add(division2);
        conference.addDivision(division1);
        conference.addDivision(division2);
        Assert.assertEquals(divisions, conference.getDivisions());
    }
}