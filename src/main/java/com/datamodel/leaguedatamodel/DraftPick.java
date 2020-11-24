package com.datamodel.leaguedatamodel;

import java.util.ArrayList;
import java.util.List;

public class DraftPick implements IDraftPick {

    private List<ITeam[]> pickChance;

    public DraftPick() {
        pickChance = new ArrayList<>();
    }

    public List<ITeam[]> getDraftPick() {
//        for (ITeam[] arr : getDraftPick()) {
//            int i = 1;
//            for (ITeam team : arr) {
//                System.out.println(" Team pick in round " + i + " " + team);
//                i++;
//            }
//        }
        return pickChance;
    }

    @Override
    public void setDraftPick(ITeam[] teamPick) {
        pickChance.add(teamPick);
    }
}
