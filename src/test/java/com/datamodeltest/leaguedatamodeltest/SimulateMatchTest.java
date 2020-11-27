package com.datamodeltest.leaguedatamodeltest;

import com.datamodel.leaguedatamodel.*;
import com.statemachine.IStateMachine;
import com.statemachine.StateMachine;
import org.junit.Test;

import java.sql.Date;
import java.util.ArrayList;

public class SimulateMatchTest {

    @Test
    public void simulateMatchTest() {
        simulateMatchTest(2, 3, 5);
    }

    public void simulateMatchTest(int conferenceSize, int divisionSize, int teamSize) {
        IStateMachine stateMachine = StateMachine.getInstance(null);
        Game game = MockGame.mockGame(conferenceSize, divisionSize, teamSize);
        IDataModelFactory dataModelFactory = AbstractDataModelFactory.getNewInstance();
        IGameSchedule schedule = dataModelFactory.getGameSchedule();
        ArrayList<IGameSchedule> matchSchedules = schedule.scheduleRegularSeason(game, stateMachine);
        String str = "2020-10-12";
        game.getLeagues().get(0).setCurrentDate(Date.valueOf(str));
        ISimulateMatch simulateMatch = dataModelFactory.getSimulateMatch();
        double teamStrength = Math.random();
        double oppositionTeamStrength = Math.random();
        for (IGameSchedule gameSchedule : matchSchedules) {
            Date curreDate = game.getLeagues().get(0).getCurrentDate();
            Date matchDate = gameSchedule.getMatchDate();
            if (curreDate.compareTo(matchDate) == 0) {
     //           simulateMatch.simulateMatchResult(gameSchedule, game, 0.5);
            }
        }
        schedule.schedulePlayoff(game, stateMachine);
    }
}
