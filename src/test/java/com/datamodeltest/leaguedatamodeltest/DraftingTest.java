package com.datamodeltest.leaguedatamodeltest;

import com.datamodel.leaguedatamodel.Drafting;
import com.datamodel.leaguedatamodel.ILeague;
import com.datamodel.leaguedatamodel.ITeam;
import com.datamodel.leaguedatamodel.Team;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class DraftingTest {

    ILeague league;

    @Before
    public void loadMockLeague() {
        LeagueMock leagueMock = new LeagueMock();
        league = leagueMock.league;
    }

    @Test
    public void getDraftPickTest() {
        Drafting drafting = new Drafting();
        for (ITeam team : league.getAllTeams()) {
            drafting.createDraftPick(team);
            Assert.assertEquals(7,drafting.getDraftPick(team).length);
        }
    }

    @Test
    public void getDraftPickByRoundTest() {
        Drafting drafting = new Drafting();
        ITeam pickerTeam = new Team();
        ITeam ownerTeam = new Team();
        drafting.createDraftPick(ownerTeam);
        drafting.setDraftPickByRound(ownerTeam,pickerTeam,2);
        Assert.assertEquals(pickerTeam,drafting.getDraftPickByRound(ownerTeam,2));
    }
}

