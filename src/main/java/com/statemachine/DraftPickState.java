package com.statemachine;

import com.datamodel.leaguedatamodel.*;
import com.inputoutputmodel.IPropertyLoader;
import com.inputoutputmodel.PropertyLoader;

import java.sql.Date;
import java.util.ArrayList;

public class DraftPickState implements IState {
    IStateMachine stateMachine;
    private static final String DRAFT_PICK_DATE = "draftPickDate";


    public DraftPickState(IStateMachine stateMachine) {
        this.stateMachine = stateMachine;
    }

    @Override
    public void entry() {
//        Date currentDate = stateMachine.getGame().getLeagues().get(0).getCurrentDate().toString();
        String currentDate = stateMachine.getGame().getLeagues().get(0).getCurrentDate().toString();
        int year = Integer.parseInt(currentDate.split("-")[1]);
        IPropertyLoader propertyLoader = new PropertyLoader();
        Date draftPickDate = Date.valueOf("" + (year) + propertyLoader.getPropertyValue(DRAFT_PICK_DATE));
        ILeague league = stateMachine.getGame().getLeagues().get(0);
        league.getTeamStandings().sort((standing1,standing2) -> {
            double points1 = standing1.getTotalPoints();
            double points2 = standing2.getTotalPoints();
            if (points1 > points2) {
                return -1;
            } else {
                return 0;
            }
        });
        ArrayList<ITeamStanding> teamStandings = league.getTeamStandings();
        int playersToGenerate = teamStandings.size();
        IGenerateRandomPlayer generateRandomPlayer = new GenerateRandomPlayer();
        ArrayList<IPlayer> draftPlayers = generateRandomPlayer.getRandomPlayers(playersToGenerate,currentDate);
        IDraftPick draftPick = new DraftPick();
//        draftPick.setDraftPick(teamObj);
    }

    @Override
    public IState doTask() {
        return null;
    }

    @Override
    public void exit() {

    }
}
