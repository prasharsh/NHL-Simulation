package com.inputoutputmodel;

import java.util.ArrayList;
import java.util.Scanner;

import com.datamodel.leaguedatamodel.*;

public class CreateTeamUI implements ICreateTeamUI {
	@Override
	public void displayConferences(ArrayList<IConference> conferences) {
		System.out.printf("%-10s %s\n", "S.NO", "CONFERENCE NAME");
		for (int i = 0; i < conferences.size(); i++) {
			System.out.printf("%-10d %s\n", (i + 1), conferences.get(i).getConferenceName());
		}
		System.out.println("\nSelect a conference for your team from the above list: ");
	}

	@Override
	public void displayDivisions(ArrayList<IDivision> divisions) {
		System.out.printf("%-10s %s\n", "S.NO", "DIVISION NAME");
		for (int i = 0; i < divisions.size(); i++) {
			System.out.printf("%-10d %s\n", (i + 1), divisions.get(i).getDivisionName());
		}
		System.out.println("\nSelect a division for your team from the above list: ");
	}

	@Override
	public void displayHeadCoaches(ArrayList<IHeadCoach> headCoaches) {
		System.out.printf("%-10s %-20s %-10s %-10s %-10s %-10s\n", "S.NO", "COACH NAME", "SKATING", "SHOOTING",
				"CHECKING", "SAVING");
		for (int i = 0; i < headCoaches.size(); i++) {
			IHeadCoach currentCoach = headCoaches.get(i);
			System.out.printf("%-10d %-20s %-10s %-10s %-10s %-10s\n", (i + 1), currentCoach.getHeadCoachName(),
					currentCoach.getHeadCoachSkating(), currentCoach.getHeadCoachShooting(),
					currentCoach.getHeadCoachChecking(), currentCoach.getHeadCoachSaving());
		}
		System.out.println("\nSelect a Head Coach for your team from the above list: ");
	}

	@Override
	public void displayGeneralManagers(ArrayList<IGeneralManager> generalManagers) {
		System.out.printf("%-10s %s\n", "S.NO", "MANAGER NAME");
		for (int i = 0; i < generalManagers.size(); i++) {
			System.out.printf("%-10d %s\n", (i + 1), generalManagers.get(i).getGeneralManagerName());
		}
		System.out.println("\nSelect a general manager for your team from the above list: ");
	}

	@Override
	public void displayFreeAgents(ArrayList<IPlayer> freeAgents, int skaterCount, int goalieCount) {
		System.out.printf("%-10s %-20s %-10s %-10s %-10s %-10s %-10s %-10s\n", "S.NO", "PLAYER NAME", "POSITION", "AGE",
				"SKATING", "SHOOTING", "CHECKING", "SAVING");
		for (int i = 0; i < freeAgents.size(); i++) {
			IPlayer currentFreeAgent = freeAgents.get(i);
			System.out.printf("%-10d %-20s %-10s %-10d %-10s %-10s %-10s %-10s\n", (i + 1),
					currentFreeAgent.getPlayerName(), currentFreeAgent.getPlayerPosition(),
					currentFreeAgent.getPlayerAgeYear(), currentFreeAgent.getPlayerSkating(),
					currentFreeAgent.getPlayerShooting(), currentFreeAgent.getPlayerChecking(),
					currentFreeAgent.getPlayerSaving());
		}
		System.out.println("\nSelect a player for your team from the above list (" + skaterCount + "/18 skaters and "
				+ goalieCount + "/2 goalies hired): ");
	}

	@Override
	public void displayPlayers(ArrayList<IPlayer> players) {
		System.out.printf("%-10s %-20s %-10s %-10s %-10s %-10s %-10s %-10s\n", "S.NO", "PLAYER NAME", "POSITION", "AGE",
				"SKATING", "SHOOTING", "CHECKING", "SAVING");
		for (int i = 0; i < players.size(); i++) {
			IPlayer currentPlayer = players.get(i);
			System.out.printf("%-10d %-20s %-10s %-10d %-10s %-10s %-10s %-10s\n", (i + 1),
					currentPlayer.getPlayerName(), currentPlayer.getPlayerPosition(), currentPlayer.getPlayerAgeYear(),
					currentPlayer.getPlayerSkating(), currentPlayer.getPlayerShooting(),
					currentPlayer.getPlayerChecking(), currentPlayer.getPlayerSaving());
		}
		System.out.println("\nSelect a captain for your team from the above list: ");
	}

	@Override
	public void displayError(String errorMessage) {
		System.out.println("\nERROR: " + errorMessage + "\n");
	}

	@Override
	public void displayMessage(String message) {
		System.out.println("\n" + message + "\n");
	}

	@Override
	public void displaySuccess(String successMessage) {
		System.out.println("\n_____________________________________________________");
		System.out.println(successMessage);
		System.out.println("_____________________________________________________\n\n");
	}

	@Override
	public int getUserChoiceFromList(Scanner scanner) {
		try {
			int userInput = Integer.parseInt(scanner.nextLine().trim());
			return (userInput - 1); // list starts from 1 for user readability
		} catch (Exception e) {
			return -1;
		}
	}
}
