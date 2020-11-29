package com.datamodeltest.leaguedatamodeltest;

import com.datamodel.gameplayconfig.GamePlayConfigAbstractFactory;
import com.datamodel.gameplayconfig.GamePlayConfigFactory;
import com.datamodel.leaguedatamodel.*;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class DraftingTest {

    ILeague league;

    @Before
    public void createLeague(){
        GamePlayConfigAbstractFactory.setFactory(new GamePlayConfigFactory());
        LeagueDataModelAbstractFactory.setFactory(new LeagueDataModelFactoryTest());
        LeagueMock leagueMock = new LeagueMock();
        league = leagueMock.getLeague();
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

