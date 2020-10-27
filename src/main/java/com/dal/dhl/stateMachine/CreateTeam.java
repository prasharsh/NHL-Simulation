package com.dal.dhl.stateMachine;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

import g4dhl.Game;
import g4dhl.IConference;
import g4dhl.IDivision;
import g4dhl.IFreeAgent;
import g4dhl.IGeneralManager;
import g4dhl.IHeadCoach;
import g4dhl.ILeague;
import g4dhl.IPlayer;
import g4dhl.ITeam;
import g4dhl.Player;
import g4dhl.Team;

public class CreateTeam {

	public void createNewTeam(Game game) {

		ILeague currentLeague = game.getLeagues().get(0);

		LoadTeam loadTeam = new LoadTeam();
		Scanner teamInput = new Scanner(System.in);

		System.out.println("Initiating team creation flow\n");

		ArrayList<IConference> availableConferences = currentLeague.getConferences();
		IConference currentConference = null;
		boolean isConferenceSelected = false;
		while (!isConferenceSelected) {
			int i = 0;
			System.out.printf("%-10s %s\n", "S.No", "Conference Name");
			while (i < availableConferences.size()) {
				System.out.printf("%-10d %s\n", (i + 1), availableConferences.get(i).getConferenceName());
				i++;
			}
			System.out.println("\nSelect a conference for your team from the above list: ");
			int inputIndex = teamInput.nextInt() - 1;
			if (inputIndex >= 0 && inputIndex < i) {
				currentConference = availableConferences.get(inputIndex);
				isConferenceSelected = true;
				System.out.println("Selected conference '" + currentConference.getConferenceName() + "'\n");
			} else {
				System.out.println("Invalid serial number for conference");
			}
		}

		ArrayList<IDivision> availableDivisions = currentConference.getDivisions();
		IDivision currentDivision = null;
		boolean isDivisionSelected = false;
		while (!isDivisionSelected) {
			int i = 0;
			System.out.printf("%-10s %s\n", "S.No", "Division Name");
			while (i < availableDivisions.size()) {
				System.out.printf("%-10d %s\n", (i + 1), availableDivisions.get(i).getDivisionName());
				i++;
			}
			System.out.println("\nSelect a division for your team from the above list: ");
			int inputIndex = teamInput.nextInt() - 1;
			if (inputIndex >= 0 && inputIndex < i) {
				currentDivision = availableDivisions.get(inputIndex);
				isDivisionSelected = true;
				System.out.println(("Selected division '" + currentDivision.getDivisionName() + "'\n"));
			} else {
				System.out.println("Invalid serial number for division\n");
			}
		}

		ArrayList<ITeam> teams = currentDivision.getTeams();
		ITeam currentTeam = new Team();
		boolean isTeamCreated = false;
		while (!isTeamCreated) {
			System.out.println("\nEnter a name for your team to be created: ");
			String teamName = teamInput.next().trim();
			ITeam isTeamExist = loadTeam.teamExist(teamName, teams);
			if (isTeamExist != null) {
				System.out.println("Oops! A team already exists with this name.");
			} else if (teamName.isEmpty()) {
				System.out.println("Team Name can't be empty!");
			} else {
				currentTeam.setTeamName(teamName);
				System.out.println("Your team created with the name '" + teamName + "'\n");
				isTeamCreated = true;
			}
		}

		ArrayList<IGeneralManager> availableManagers = currentLeague.getManagers();
		boolean isManagerHired = false;
		while (!isManagerHired) {
			int i = 0;
			System.out.printf("%-10s %s\n", "S.No", "Manager Name");
			while (i < availableManagers.size()) {
				System.out.printf("%-10d %s\n", (i + 1), availableManagers.get(i).getGeneralManagerName());
				i++;
			}
			System.out.println("\nSelect a general manager for your team from the above list: ");
			int inputIndex = teamInput.nextInt() - 1;
			if (inputIndex >= 0 && inputIndex < i) {
				currentTeam.setGeneralManager(availableManagers.get(inputIndex));
				System.out.println("General manager '" + availableManagers.get(inputIndex).getGeneralManagerName()
						+ "' hired for your team\n");
				availableManagers.remove(inputIndex);
				isManagerHired = true;
			} else {
				System.out.println("Please select a valid serial number for general manager\n");
			}
		}

		ArrayList<IHeadCoach> availableCoaches = currentLeague.getCoaches();
		boolean isCoachHired = false;
		while (!isCoachHired) {
			int i = 0;
			System.out.printf("%-10s %-20s %-10s %-10s %-10s %-10s\n", "S.No", "Coach Name", "Skating", "Shooting",
					"Checking", "Saving");
			while (i < availableCoaches.size()) {
				IHeadCoach currentCoach = availableCoaches.get(i);
				System.out.printf("%-10d %-20s %-10s %-10s %-10s %-10s\n", (i + 1), currentCoach.getHeadCoachName(),
						currentCoach.getHeadCoachSkating(), currentCoach.getHeadCoachShooting(),
						currentCoach.getHeadCoachChecking(), currentCoach.getHeadCoachSaving());
				i++;
			}
			System.out.println("\nSelect a Head Coach for your team from the above list: ");
			int inputIndex = teamInput.nextInt() - 1;
			if (inputIndex >= 0 && inputIndex < i) {
				currentTeam.setHeadCoach(availableCoaches.get(inputIndex));
				System.out.println("Head coach '" + availableCoaches.get(inputIndex).getHeadCoachName()
						+ "' hired for your team\n");
				availableCoaches.remove(inputIndex);
				isCoachHired = true;
			} else {
				System.out.println("Please select a valid serial number for head coach\n");
			}
		}

		ArrayList<IFreeAgent> availableFreeAgents = getRankedFreeAgents(currentLeague.getFreeAgents());
		int hiredSkaters = 0;
		int hiredGoalies = 0;
		int hiredPlayers = 0;
		while (hiredPlayers != 20) {
			System.out.printf("%-10s %-20s %-10s %-10s %-10s %-10s %-10s %-10s %-10s\n", "S.No", "Player Name",
					"Position", "AgeYear", "AgeDays", "Skating", "Shooting", "Checking", "Saving");
			for (int i = 0; i < availableFreeAgents.size(); i++) {
				IFreeAgent currentFreeAgent = availableFreeAgents.get(i);
				System.out.printf("%-10d %-20s %-10s %-10d %-10d %-10s %-10s %-10s %-10s\n", (i + 1),
						currentFreeAgent.getFreeAgentName(), currentFreeAgent.getFreeAgentPosition(),
						currentFreeAgent.getFreeAgentAgeYear(), currentFreeAgent.getFreeAgentAgeDays(),
						currentFreeAgent.getFreeAgentSkating(), currentFreeAgent.getFreeAgentShooting(),
						currentFreeAgent.getFreeAgentChecking(), currentFreeAgent.getFreeAgentSaving());
			}
			System.out.println("\nSelect a player for your team from the above list: ");
			int inputIndex = teamInput.nextInt() - 1;
			if (inputIndex >= 0 && inputIndex < availableFreeAgents.size()) {
				if (availableFreeAgents.get(inputIndex).getFreeAgentPosition().equals("goalie")) {
					if (hiredGoalies >= 2) {
						System.out.println("There can't be more than 2 goalies in your team\n");
						continue;
					} else {
						addFreeAgentToTeam(availableFreeAgents.get(inputIndex), currentTeam);
						hiredGoalies++;
						hiredPlayers++;
						availableFreeAgents.remove(inputIndex);
					}
				} else {
					if (hiredSkaters >= 18) {
						System.out.println("There can't be more than 18 skaters in your team\n");
						continue;
					} else {
						addFreeAgentToTeam(availableFreeAgents.get(inputIndex), currentTeam);
						hiredSkaters++;
						hiredPlayers++;
						availableFreeAgents.remove(inputIndex);
					}
				}
			} else {
				System.out.println("Please select a valid serial number for player\n");
			}
		}

		System.out.println("\nPlayer hiring completed!!! Here is your list of 20 players");

		boolean isCaptainSelected = false;
		while (!isCaptainSelected) {
			System.out.printf("%-10s %-20s %-10s %-10s %-10s %-10s %-10s %-10s %-10s\n", "S.No", "Player Name",
					"Position", "AgeYear", "AgeDays", "Skating", "Shooting", "Checking", "Saving");
			for (int i = 0; i < currentTeam.getPlayers().size(); i++) {
				IPlayer currentPlayer = currentTeam.getPlayers().get(i);
				System.out.printf("%-10d %-20s %-10s %-10d %-10d %-10s %-10s %-10s %-10s\n", (i + 1),
						currentPlayer.getPlayerName(), currentPlayer.getPlayerPosition(),
						currentPlayer.getPlayerAgeYear(), currentPlayer.getPlayerAgeDays(),
						currentPlayer.getPlayerSkating(), currentPlayer.getPlayerShooting(),
						currentPlayer.getPlayerChecking(), currentPlayer.getPlayerSaving());
			}
			System.out.println("\nSelect a captain for your team from the above list: ");
			int inputIndex = teamInput.nextInt() - 1;
			if (inputIndex >= 0 && inputIndex < currentTeam.getPlayers().size()) {
				currentTeam.getPlayers().get(inputIndex).setPlayerCaptain(true);
				System.out.println(currentTeam.getPlayers().get(inputIndex).getPlayerName() + " selected as captain\n");
				isCaptainSelected = true;
			} else {
				System.out.println("Please select a valid serial number for player\n");
			}
		}
	}

	public void addFreeAgentToTeam(IFreeAgent freeAgent, ITeam team) {
		IPlayer player = new Player();
		player.setPlayerName(freeAgent.getFreeAgentName());
		player.setPlayerPosition(freeAgent.getFreeAgentPosition());
		player.setPlayerAgeYear(freeAgent.getFreeAgentAgeYear());
		player.setPlayerAgeDays(freeAgent.getFreeAgentAgeDays());
		player.setPlayerSkating(freeAgent.getFreeAgentSkating());
		player.setPlayerShooting(freeAgent.getFreeAgentShooting());
		player.setPlayerChecking(freeAgent.getFreeAgentChecking());
		player.setPlayerSaving(freeAgent.getFreeAgentSaving());
		team.addPlayer(player);
	}

	public ArrayList<IFreeAgent> getRankedFreeAgents(ArrayList<IFreeAgent> freeAgentsList) {
		Collections.sort(freeAgentsList, new Comparator<IFreeAgent>() {
			@Override
			public int compare(IFreeAgent agent1, IFreeAgent agent2) {
				double agent1Score = agent1.getFreeAgentStrength();
				double agent2Score = agent2.getFreeAgentStrength();
				if (agent1Score > agent2Score) {
					return -1;
				} else {
					return 0;
				}
			}
		});
		return freeAgentsList;
	}

}
