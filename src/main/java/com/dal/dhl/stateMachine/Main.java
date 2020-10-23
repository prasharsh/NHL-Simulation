package com.dal.dhl.stateMachine;

import java.io.IOException;

import org.json.simple.parser.ParseException;;

public class Main {


	public static boolean isNullOrEmpty(String str) {
		if (str != null && !str.isEmpty()) {
			return false;
		}
		return true;
	}

	public static void main(String[] args) throws IOException, ParseException {
		String filePath = null;
		try {
			filePath = args[0];
		} catch (ArrayIndexOutOfBoundsException ae) {
			// do nothing, state machine will take care
		}

		/*
		 * Game game = new Game(); ILeague league = new League();
		 * league.setLeagueName("1dal Hockey League"); game.addLeague(league); IGameDB
		 * gamedb = new GameDB(); game.getLeagueByName(gamedb);
		 */
		DHLStateMachine stateMachine = new DHLStateMachine(filePath);
		stateMachine.start();

	}
}
