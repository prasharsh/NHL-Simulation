package com.inputoutputmodel;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import com.datamodel.leaguedatamodel.IPlayer;

public class DisplayRoster implements IDisplayRoaster {

	@Override
	public int takeNumberInputFromUser() {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int id = 0;
		try {
			while (true) {
				String s = br.readLine();
				if (s.matches("\\d+")) {
					id = Integer.parseInt(s);
					break;
				} else {
					System.out.println("Invalid format! Please enter again");
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return id - 1;
	}

	@Override
	public void displayMessageToUser(String s) {
		System.out.println(s);
	}

	@Override
	public void displayPlayersToBeDropped(ArrayList<IPlayer> players, int count) {
		System.out.println(
				"-----------------------------------------------------------------------------------------------------------------------------");
		System.out.printf("%-5s %-20s %-20s %-20s %-20s %-20s %-20s", "ID", "PLAYER NAME", "PLAYER POSITION",
				"PLAYER SKATING", "PLAYER SHOOTING", "PLAYER CHECKING", "PLAYER SAVING");
		System.out.println();
		System.out.println(
				"-----------------------------------------------------------------------------------------------------------------------------");
		for (int i = 0; i < players.size(); i++) {
			System.out.format("%-5d %-20s %-20s %8d %20d %20d %20d", i + 1, players.get(i).getPlayerName(),
					players.get(i).getPlayerPosition(), players.get(i).getPlayerSkating(),
					players.get(i).getPlayerShooting(), players.get(i).getPlayerChecking(),
					players.get(i).getPlayerSaving());
			System.out.println();
		}
		System.out.println("\nPLAYERS TO BE DROPPED: " + count);
		System.out.println("Enter the player id's one by one to drop them");
	}

	@Override
	public int inputPlayerIndexToDrop() {
		return takeNumberInputFromUser();
	}

	@Override
	public void displayFreeAgentsToBeHired(ArrayList<IPlayer> freeAgents, int count) {
		System.out.println(
				"---------------------------------------------------------------------------------------------------------------------------------------------------");
		System.out.printf("%-5s %-20s %-20s %-20s %-20s %-20s %-20s", "ID", "FREE AGENT NAME", "FREE AGENT POSITION",
				"FREE AGENT SKATING", "FREE AGENT SHOOTING", "FREE AGENT CHECKING", "FREE AGENT SAVING");
		System.out.println();
		System.out.println(
				"---------------------------------------------------------------------------------------------------------------------------------------------------");
		for (int i = 0; i < freeAgents.size(); i++) {
			System.out.format("%-5d %-20s %-20s %8d %20d %20d %20d", i + 1, freeAgents.get(i).getPlayerName(),
					freeAgents.get(i).getPlayerPosition(), freeAgents.get(i).getPlayerSkating(),
					freeAgents.get(i).getPlayerShooting(), freeAgents.get(i).getPlayerChecking(),
					freeAgents.get(i).getPlayerSaving());
			System.out.println();
		}

		System.out.println("\nFREE AGENTS TO BE HIRED: " + count);
		System.out.println("Enter the free agents id's one by one to hire them");
	}

	@Override
	public int inputFreeAgentIndexToHire() {
		return takeNumberInputFromUser();
	}
}