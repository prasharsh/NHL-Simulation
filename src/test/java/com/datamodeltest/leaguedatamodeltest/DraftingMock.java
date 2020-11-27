package com.datamodeltest.leaguedatamodeltest;
import com.datamodel.leaguedatamodel.IDrafting;
import com.datamodel.leaguedatamodel.ITeam;

public class DraftingMock implements IDrafting {

    @Override
    public void createDraftPick(ITeam team) {

    }

    @Override
    public ITeam[] getDraftPick(ITeam team) {
        return new ITeam[0];
    }

    @Override
    public ITeam getDraftPickByRound(ITeam team, int round) {
        return team;
    }

    @Override
    public void setDraftPickByRound(ITeam pickOwnerTeam, ITeam tradedToTeam, int round) {

    }
}
