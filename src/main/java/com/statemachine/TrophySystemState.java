package com.statemachine;

import com.datamodel.leaguedatamodel.IGame;
import com.datamodel.leaguedatamodel.LeagueDataModelAbstractFactory;
import com.datamodel.trophysystem.ITrophy;
import com.datamodel.trophysystem.Trophy;

public class TrophySystemState implements IState {

	private static final ITrophy trophy = new Trophy();

	@Override
	public void entry() {
		LeagueDataModelAbstractFactory factory = LeagueDataModelAbstractFactory.instance();
		IGame game = factory.createGame();
		String[] date = game.getLeagues().get(0).getSimulationStartDate().toString().split("-");
		int year = Integer.parseInt(date[0]);
		if(game.getLeagues().get(0).getSeason() == 1) {
			trophy.awardFirstYearTrophies(year);
		}
		trophy.awardSeasonalTrophies(year);
		trophy.displayHistoricalTrophies(year);
		trophy.resetSeasonalAwards();
	}


	@Override
	public IState doTask() {
		LeagueDataModelAbstractFactory factory = LeagueDataModelAbstractFactory.instance();
		IGame game = factory.createGame();
		String[] date = game.getLeagues().get(0).getSimulationStartDate().toString().split("-");
		int year = Integer.parseInt(date[0]);
		trophy.awardRegularSeasonTrophies(year);
		trophy.resetRegularSeasonAwards();
		return null;
	}
}