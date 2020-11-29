package com.statemachine;

import com.datamodel.leaguedatamodel.*;
import com.inputoutputmodel.IPropertyLoader;
import com.inputoutputmodel.PropertyLoader;

import java.sql.Date;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.util.HashSet;

public class SimulateGameState implements IState {

    private static final String TRADE_END_MONTH = "tradeEndMonth";
    private static final String STATUS_SCHEDULED = "scheduled";
    private static final String STATUS_PLAYED = "played";

    @Override
    public void entry() {
    }

	/*
	 * // @Override public void exit() { StateMachineAbstractFactory stateFactory =
	 * StateMachineAbstractFactory.instance(); IStateMachine stateMachine =
	 * stateFactory.createStateMachine(null);
	 * 
	 * stateMachine.setCurrentState(stateMachine.getAging()); }
	 */

    @Override
    public IState doTask() {
    	StateMachineAbstractFactory stateFactory = StateMachineAbstractFactory.instance();
		IStateMachine stateMachine = stateFactory.createStateMachine(null);
		LeagueDataModelAbstractFactory factory = LeagueDataModelAbstractFactory.instance();
		IGame game = factory.createGame();
        HashSet<ITeam> gameDayTeams = new HashSet<>();
        LeagueDataModelAbstractFactory dataModelFactory = LeagueDataModelFactory.getNewInstance();
        ISimulateMatch simulateMatch = dataModelFactory.createSimulateMatch();
        for (IGameSchedule gameSchedule : game.getLeagues().get(0).getGameSchedules()) {
            Date curreDate = game.getLeagues().get(0).getCurrentDate();
            Date matchDate = gameSchedule.getMatchDate();
            if (curreDate.compareTo(matchDate) == 0 && gameSchedule.getStatus().equals(STATUS_SCHEDULED)) {
                simulateMatch.simulateMatchResult(gameSchedule, game, 0.5);
                gameDayTeams.add(gameSchedule.getTeamA());
                gameDayTeams.add(gameSchedule.getTeamB());
                gameSchedule.setStatus(STATUS_PLAYED);
                stateMachine.setGameDayTeams(gameDayTeams);
            }
        }
        if (gameDayTeams != null) {
           // stateMachine.setCurrentState(stateMachine.getInjuryCheck());
            //stateMachine.getCurrentState().entry();
        	IState injuryCheckState = stateFactory.createInjuryCheckState();
        	injuryCheckState.entry();
        }
        String[] date = game.getLeagues().get(0).getSimulationStartDate().toString().split("-");
        int year = Integer.parseInt(date[0]);
        IPropertyLoader propertyLoader = new PropertyLoader();
        Date tradeEndMonth = Date.valueOf("" + (year + 1) + propertyLoader.getPropertyValue(TRADE_END_MONTH));
        LocalDate tradeEndDate = tradeEndMonth.toLocalDate().with(TemporalAdjusters.previous(DayOfWeek.MONDAY));
        Date lastTradeDate = Date.valueOf(tradeEndDate);
        Date currDate = game.getLeagues().get(0).getCurrentDate();
        if (currDate.compareTo(lastTradeDate) < 0) {
            return stateFactory.createExecuteTradesState();
        }
        return stateFactory.createAgingState();
    }
}