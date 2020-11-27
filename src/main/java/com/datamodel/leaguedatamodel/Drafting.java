package com.datamodel.leaguedatamodel;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Drafting implements IDrafting{

    static Map<ITeam, ITeam[]> draftPicks = new HashMap<>();
    int NO_OF_ROUNDS = 7;

    @Override
    public void createDraftPick(ITeam team){
        ITeam[] teamPicks = new ITeam[NO_OF_ROUNDS];
        Arrays.fill(teamPicks, team);
        draftPicks.put(team, teamPicks);
    }

    @Override
    public ITeam[] getDraftPick(ITeam team){
        return draftPicks.get(team);
    }

    @Override
    public ITeam getDraftPickByRound(ITeam team, int round){
        return draftPicks.get(team)[round];
    }

    @Override
    public void setDraftPickByRound(ITeam team, int round){
        draftPicks.get(team)[round] = team;
    }

}
