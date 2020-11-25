package com.datamodel.leaguedatamodel;

import java.util.Arrays;

import static com.datamodel.leaguedatamodel.Constants.DRAFT_ROUNDS;

public class DraftPick implements IDraftPick {

    private ITeam[][] pickChance;


    public DraftPick(int totalTeams) {
        pickChance = new ITeam[totalTeams][DRAFT_ROUNDS];
        Arrays.fill(pickChance,null);
    }

    @Override
    public ITeam[][] getDraftPick() {
        return pickChance;
    }

    @Override
    public void setDraftPick(ITeam[] teamPick) {
        for (int i = 0; i < pickChance.length; i++) {
            for (int j = 0; j < DRAFT_ROUNDS; j++) {
                if (pickChance[i][j] == null) {
                    pickChance[i][j] = teamPick[j];
                }
            }
        }
    }
}
