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
		System.out.println("Enter the confernceName for your team: ");
		String conferenceName = teamInput.nextLine().trim();
		IConference currentConference = loadTeam.conferenceExist(conferenceName, conferences);
		while (currentConference == null) {
			System.out.println("Conference does not exist!");
			System.out.println("Please Input a valid conferenceName: ");
			conferenceName = teamInput.nextLine().trim();
			currentConference = loadTeam.conferenceExist(conferenceName, conferences);
		}

		ArrayList<IDivision> divisions = currentConference.getDivisions();
		System.out.println("Enter the divisionName for your team");
		String divisionName = teamInput.nextLine().trim();
		IDivision currentDivision = loadTeam.divisionExist(divisionName, divisions);
		while (currentDivision == null) {
			System.out.println("Division does not exist!");
			System.out.println("Please Input a valid divisionName: ");
			divisionName = teamInput.nextLine().trim();
			currentDivision = loadTeam.divisionExist(divisionName, divisions);
		}

		ArrayList<ITeam> teams = currentDivision.getTeams();
		ITeam currentTeam = new Team();
		System.out.println("Enter the teamName: ");
		String teamName = teamInput.nextLine().trim();
		boolean check = false;
		while (!check) {
			ITeam isTeamExist = loadTeam.teamExist(teamName, teams);
			if (isTeamExist != null) {
				System.out.println("Team already exist!");
				System.out.println("Please Input a diffrent teamName: ");
				teamName = teamInput.nextLine().trim();
				isTeamExist = loadTeam.teamExist(teamName, teams);
			} else if (teamName.isEmpty()) {
				System.out.println("Team Name is required!");
				System.out.println("Please Input a teamName: ");
				teamName = teamInput.nextLine().trim();
			} else {
				check = true;
			}
		}

		currentTeam.setTeamName(teamName);
		System.out.println("Enter the name of general manager: ");
		String generalManagerName = teamInput.nextLine().trim();
		IGeneralManager generalManagerObj = new GeneralManager();
		generalManagerObj.setGeneralManagerName(generalManagerName);
		currentTeam.setGeneralManager(generalManagerObj);
		System.out.println("Enter the name of Head Coach: ");
		String headCoachName = teamInput.nextLine().trim();
		IHeadCoach headCoachObj = new HeadCoach();
		headCoachObj.setHeadCoachName(headCoachName);
		currentTeam.setHeadCoach(headCoachObj);
		currentDivision.addTeam(currentTeam);
		System.out.println("New Team added to league!!");
	}

}
