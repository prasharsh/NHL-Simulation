package com.inputoutputmodel;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

import com.datamodeltest.leaguedatamodel.IPlayer;

public class DisplayTradingOffers implements IDisplayTradingOffers {

    @Override
    public void displayOfferToUser(ArrayList<IPlayer> offeredPlayers, ArrayList<IPlayer> consideredPlayers) {
        System.out.println("\nPLAYERS OFFERED!!");
        System.out.println("-------------------------------------------------------------------------------------------------------------------------");
        System.out.printf("%-20s %-20s %-20s %-20s %-20s %-20s", "PLAYER NAME", "PLAYER POSITION", "PLAYER SKATING", "PLAYER SHOOTING", "PLAYER CHECKING", "PLAYER SAVING");
        System.out.println();
        System.out.println("-------------------------------------------------------------------------------------------------------------------------");
        for (IPlayer player : offeredPlayers) {
            System.out.format("%-20s %-20s %8d %20d %20d %20d",
                    player.getPlayerName(), player.getPlayerPosition(), player.getPlayerSkating(), player.getPlayerShooting(), player.getPlayerChecking(), player.getPlayerSaving());
            System.out.println();
        }

        System.out.println("\n*************************************************************************************************************************\n");
        System.out.println("PLAYERS REQUESTED!!");
        System.out.println("-------------------------------------------------------------------------------------------------------------------------");
        System.out.printf("%-20s %-20s %-20s %-20s %-20s %-20s", "PLAYER NAME", "PLAYER POSITION", "PLAYER SKATING", "PLAYER SHOOTING", "PLAYER CHECKING", "PLAYER SAVING");
        System.out.println();
        System.out.println("-------------------------------------------------------------------------------------------------------------------------");
        for (IPlayer player : consideredPlayers) {
            System.out.format("%-20s %-20s %8d %20d %20d %20d",
                    player.getPlayerName(), player.getPlayerPosition(), player.getPlayerSkating(), player.getPlayerShooting(), player.getPlayerChecking(), player.getPlayerSaving());
            System.out.println();
        }

        System.out.print("\nTo accept/reject the offer enter (y/n)\n");
    }

    @Override
    public boolean inputTradeAcceptRejectBooleanFromUser() {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            do {
                String s = br.readLine();
                if (s.equalsIgnoreCase("y")) {
                    return true;
                } else if (s.equalsIgnoreCase("n")) {
                    return false;
                } else {
                    System.out.println("Invalid Input! Please try again");
                }
            } while (true);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
