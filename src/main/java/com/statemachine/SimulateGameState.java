package com.statemachine;

import java.sql.Date;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.util.HashSet;

import org.apache.log4j.Logger;

import com.datamodel.leaguedatamodel.IGame;
import com.datamodel.leaguedatamodel.IGameSchedule;
import com.datamodel.leaguedatamodel.ISimulateMatch;
import com.datamodel.leaguedatamodel.ITeam;
import com.datamodel.leaguedatamodel.LeagueDataModelAbstractFactory;
import com.inputoutputmodel.IPropertyLoader;
import com.inputoutputmodel.PropertyLoader;

public class SimulateGameState implements IState {
	
	final static Logger logger = Logger.getLogger(SimulateGameState.class);

    private static final String TRADE_END_MONTH = "tradeEndMonth";
    private static final String STATUS_SCHEDULED = "scheduled";
    private static final String STATUS_PLAYED = "played";


    @Override
    public IState doTask() {
        StateMachineAbstractFactory stateFactory = StateMachineAbstractFactory.instance();
        IStateMachine stateMachine = stateFactory.createStateMachine(null);
        LeagueDataModelAbstractFactory factory = LeagueDataModelAbstractFactory.instance();
        IGame game = factory.createGame();
        HashSet<ITeam> gameDayTeams = new HashSet<>();
        ISimulateMatch simulateMatch = factory.createSimulateMatch();
        for (IGameSchedule gameSchedule : game.getLeagues().get(0).getGameSchedules()) {
            Date curreDate = game.getLeagues().get(0).getCurrentDate();
            Date matchDate = gameSchedule.getMatchDate();
            if (curreDate.compareTo(matchDate) == 0 && gameSchedule.getStatus().equals(STATUS_SCHEDULED)) {
                simulateMatch.simulateMatchResult(gameSchedule, game, 0.5);
                gameDayTeams.add(gameSchedule.getTeamA());
                gameDayTeams.add(gameSchedule.getTeamB());
                gameSchedule.setStatus(STATUS_PLAYED);
                game.getLeagues().get(0).setGameDayTeams(gameDayTeams);
            }
        }
        if (gameDayTeams.isEmpty()) {
            logger.warn("No games played today.");
        } else {
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
        logger.info("Trade deadline crossed.");
        return stateFactory.createAgingState();
    }
}