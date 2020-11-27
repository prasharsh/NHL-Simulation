package com.datamodel.leaguedatamodel;

public interface IDrafting {

    void createDraftPick(ITeam team);

    ITeam[] getDraftPick(ITeam team);

    ITeam getDraftPickByRound(ITeam team, int round);

    void setDraftPickByRound(ITeam team, int round);
}
