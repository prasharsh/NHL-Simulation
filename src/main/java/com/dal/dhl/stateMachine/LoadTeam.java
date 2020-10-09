package com.dal.dhl.stateMachine;

import java.util.ArrayList;
import java.util.Scanner;

import g4db.GameDB;
import g4dhl.Conference;
import g4dhl.Division;
import g4dhl.Game;
import g4dhl.IConference;
import g4dhl.IDivision;
import g4dhl.ILeague;
import g4dhl.ITeam;
import g4dhl.League;
import g4dhl.Team;

public class LoadTeam {

	public ITeam loadtTeam() {

		Game game = new Game();
		GameDB gamedb = new GameDB();
		Scanner userInput = new Scanner(System.in);
		game.loadFromDB(gamedb);

		ArrayList<ILeague> leagues = game.getLeagues();
		ILeague currentLeague = null;
		System.out.println("Enter the leagueName to be load");
		String leagueName = userInput.nextLine();
		currentLeague = leagueExist(leagueName, leagues);
		while (currentLeague == null) {
			System.out.println("League does not exist");
			System.out.println("Please Input a valid leagueName: ");
			leagueName = userInput.nextLine();
			currentLeague = leagueExist(leagueName, leagues);
		}
		((League) currentLeague).loadFromDB(gamedb);

		ArrayList<IConference> conferences = currentLeague.getConferences();
		IConference currentConference = null;
		System.out.println("Enter the confernceName for your team");
		String conferenceName = userInput.nextLine();
		currentConference = conferenceExist(conferenceName, conferences);
		while (currentConference == null) {
			System.out.println("Conference does not exist");
			System.out.println("Please Input a valid conferenceName: ");
			conferenceName = userInput.nextLine();
			currentConference = conferenceExist(conferenceName, conferences);
		}
		((Conference) currentConference).loadFromDB(gamedb);

		ArrayList<IDivision> divisions = currentConference.getDivisions();
		IDivision currentDivision = null;
		System.out.println("Enter the divisionName for your team");
		String divisionName = userInput.nextLine();
		currentDivision = divisionExist(divisionName, divisions);
		while (currentDivision == null) {
			System.out.println("Division does not exist");
			System.out.println("Please Input a valid divisionName: ");
			divisionName = userInput.nextLine();
			currentDivision = divisionExist(divisionName, divisions);
		}
		((Division) currentDivision).loadFromDB(gamedb);

		ArrayList<ITeam> teams = currentDivision.getTeams();
		ITeam currentTeam = null;
		System.out.println("Enter the teamName");
		String teamName = userInput.nextLine();
		currentTeam = teamExist(teamName, teams);
		while (currentTeam == null) {
			System.out.println("Team does not exist");
			System.out.println("Please Input a valid teamName: ");
			teamName = userInput.nextLine();
			currentTeam = teamExist(teamName, teams);
		}
		userInput.close();
		((Team) currentTeam).loadFromDB(gamedb);
		System.out.println("Team loded");
		return currentTeam;
	}

	public ILeague leagueExist(String leagueName, ArrayList<ILeague> leagues) {

		ILeague currentLeague = null;
		for (ILeague league : leagues) {
			if (league.getLeagueName().equals(leagueName)) {
				currentLeague = (League) league;
				break;
			} else {
				currentLeague = null;
			}
		}
		return currentLeague;
	}

	public IConference conferenceExist(String conferenceName, ArrayList<IConference> conferences) {

		IConference currentConference = null;
		for (IConference conference : conferences) {
			if (conference.getConferenceName().equals(conferenceName)) {
				currentConference = conference;
				break;
			} else {
				currentConference = null;
			}
		}
		return currentConference;
	}

	public IDivision divisionExist(String divisionName, ArrayList<IDivision> divisions) {

		IDivision currentDivision = null;
		for (IDivision division : divisions) {
			if (division.getDivisionName().equals(divisionName)) {
				currentDivision = division;
				break;
			} else {
				currentDivision = null;
			}
		}
		return currentDivision;
	}

	public ITeam teamExist(String teamName, ArrayList<ITeam> teams) {

		ITeam currentTeam = null;
		for (ITeam team : teams) {
			if (team.getTeamName().equals(teamName)) {
				currentTeam = team;
				break;
			} else {
				currentTeam = null;
			}
		}
		return currentTeam;
	}
}
