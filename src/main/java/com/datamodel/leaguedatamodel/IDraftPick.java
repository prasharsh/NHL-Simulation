package com.datamodel.leaguedatamodel;

import java.util.List;

public interface IDraftPick {
    
    List<ITeam[]> getDraftPick();

    void setDraftPick(ITeam[] teamPick);
}
