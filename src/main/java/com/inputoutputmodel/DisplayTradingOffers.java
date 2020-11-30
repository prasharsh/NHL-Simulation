package com.inputoutputmodel;

import com.datamodel.leaguedatamodel.IPlayer;
import com.datamodel.leaguedatamodel.ITeam;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.List;

public class DisplayTradingOffers implements IDisplayTradingOffers {

	@Override
	public void displayOfferToUser(List<IPlayer> offeredPlayers, List<IPlayer> consideredPlayers) {
		System.out.println("\nPLAYERS OFFERED!!");
		System.out.println(
				"-------------------------------------------------------------------------------------------------------------------------");
		System.out.printf("%-20s %-20s %-20s %-20s %-20s %-20s", "PLAYER NAME", "PLAYER POSITION", "PLAYER SKATING",
				"PLAYER SHOOTING", "PLAYER CHECKING", "PLAYER SAVING");
		System.out.println();
		System.out.println(
				"-------------------------------------------------------------------------------------------------------------------------");
		for(IPlayer player : offeredPlayers) {
			System.out.format("%-20s %-20s %8d %20d %20d %20d", player.getPlayerName(), player.getPlayerPosition(),
					player.getPlayerSkating(), player.getPlayerShooting(), player.getPlayerChecking(),
					player.getPlayerSaving());
			System.out.println();
		}
		System.out.println("\n" +
				"*************************************************************************************************************************\n");
		System.out.println("PLAYERS REQUESTED!!");
		System.out.println(
				"-------------------------------------------------------------------------------------------------------------------------");
		System.out.printf("%-20s %-20s %-20s %-20s %-20s %-20s", "PLAYER NAME", "PLAYER POSITION", "PLAYER SKATING",
				"PLAYER SHOOTING", "PLAYER CHECKING", "PLAYER SAVING");
		System.out.println();
		System.out.println(
				"-------------------------------------------------------------------------------------------------------------------------");
		for(IPlayer player : consideredPlayers) {
			System.out.format("%-20s %-20s %8d %20d %20d %20d", player.getPlayerName(), player.getPlayerPosition(),
					player.getPlayerSkating(), player.getPlayerShooting(), player.getPlayerChecking(),
					player.getPlayerSaving());
			System.out.println();
		}
		System.out.print("\nTo accept/reject the offer enter (y/n)\n");
	}

	@Override
	public boolean inputTradeAcceptRejectBooleanFromUser() {
		InputStreamReader ins = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(ins);
		try {
			do {
				String s = br.readLine();
				if(s.equalsIgnoreCase("y")) {
					return true;
				} else if(s.equalsIgnoreCase("n")) {
					return false;
				} else {
					System.out.println("Invalid Input! Please try again");
				}
			} while(true);
		} catch(Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public void displayDraftOfferToUser(ITeam team, int teamPickRound, List<IPlayer> playersToTrade) {
		System.out.println("\nDRAFT PICK OFFER!!");
		System.out.println(team.getTeamName().toUpperCase() + " would like to offer its " + (teamPickRound + 1) + " " + "Round pick in exchange for the below players");
		System.out.println("\n" +
				"*************************************************************************************************************************\n");
		System.out.println("PLAYERS REQUESTED!!");
		System.out.println(
				"-------------------------------------------------------------------------------------------------------------------------");
		System.out.printf("%-20s %-20s %-20s %-20s %-20s %-20s", "PLAYER NAME", "PLAYER POSITION", "PLAYER SKATING",
				"PLAYER SHOOTING", "PLAYER CHECKING", "PLAYER SAVING");
		System.out.println();
		System.out.println(
				"-------------------------------------------------------------------------------------------------------------------------");
		for(IPlayer player : playersToTrade) {
			System.out.format("%-20s %-20s %8d %20d %20d %20d", player.getPlayerName(), player.getPlayerPosition(),
					player.getPlayerSkating(), player.getPlayerShooting(), player.getPlayerChecking(),
					player.getPlayerSaving());
			System.out.println();
		}
		System.out.print("\nTo accept/reject the offer enter (y/n)\n");
	}
}