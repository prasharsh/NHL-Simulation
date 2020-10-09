package com.dal.dhl.stateMachine;

import java.util.ArrayList;
import java.util.Scanner;

import g4dhl.Game;
import g4dhl.GeneralManager;
import g4dhl.HeadCoach;
import g4dhl.IConference;
import g4dhl.IDivision;
import g4dhl.IGeneralManager;
import g4dhl.IHeadCoach;
import g4dhl.ILeague;
import g4dhl.ITeam;
import g4dhl.Team;

public class CreateTeam {

	public void createNewTeam(Game game) {
		ILeague currentLeague = game.getLeagues().get(0);
		LoadTeam loadTeam = new LoadTeam();
		Scanner teamInput = new Scanner(System.in);

		ArrayList<IConference> conferences = currentLeague.getConferences();
		System.out.println("Enter the confernceName for your team");
		String conferenceName = teamInput.nextLine();
		IConference currentConference = loadTeam.conferenceExist(conferenceName, conferences);
		while (currentConference == null) {
			System.out.println("Conference does not exist");
			System.out.println("Please Input a valid conferenceName: ");
			conferenceName = teamInput.nextLine();
			currentConference = loadTeam.conferenceExist(conferenceName, conferences);
		}

		ArrayList<IDivision> divisions = currentConference.getDivisions();
		System.out.println("Enter the divisionName for your team");
		String divisionName = teamInput.nextLine();
		IDivision currentDivision = loadTeam.divisionExist(divisionName, divisions);
		while (currentDivision == null) {
			System.out.println("Division does not exist");
			System.out.println("Please Input a valid divisionName: ");
			divisionName = teamInput.nextLine();
			currentDivision = loadTeam.divisionExist(divisionName, divisions);
		}

		ArrayList<ITeam> teams = currentDivision.getTeams();
		ITeam currentTeam = new Team();
		System.out.println("Enter the teamName");
		String teamName = teamInput.nextLine();
		ITeam isTeamExist = loadTeam.teamExist(teamName, teams);
		while (isTeamExist != null) {
			System.out.println("Team already exist");
			System.out.println("Please Input a diffrent teamName: ");
			teamName = teamInput.nextLine();
			isTeamExist = loadTeam.teamExist(teamName, teams);
		}

		currentTeam.setTeamName(teamName);
		System.out.println("Enter the name of general manager");
		String generalManagerName = teamInput.nextLine();
		IGeneralManager generalManagerObj = new GeneralManager();
		generalManagerObj.setGeneralManagerName(generalManagerName);
		currentTeam.setGeneralManager(generalManagerObj);
		System.out.println("Enter the name of Head Coach");
		String headCoachName = teamInput.nextLine();
		IHeadCoach headCoachObj = new HeadCoach();
		headCoachObj.setHeadCoachName(headCoachName);
		currentTeam.setHeadCoach(headCoachObj);
		currentDivision.addTeam(currentTeam);
		System.out.println("Team added to league");
	}

}
