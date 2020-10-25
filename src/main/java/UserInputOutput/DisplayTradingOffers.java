package UserInputOutput;

import g4dhl.IPlayer;
import g4dhl.ITeam;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class DisplayTradingOffers {

    public void displayOfferToUser(ITeam offeringTeam, ArrayList<IPlayer> offeredPlayers, ITeam consideringTeam, ArrayList<IPlayer> consideredPlayers){

        System.out.println("\nTEAM: "+offeringTeam.getTeamName());
        System.out.println("PLAYERS OFFERED!!");
        System.out.println("-------------------------------------------------------------------------------------------------------------------------");
        System.out.printf("%-20s %-20s %-20s %-20s %-20s %-20s", "PLAYER NAME", "PLAYER POSITION", "PLAYER SKATING", "PLAYER SHOOTING", "PLAYER CHECKING", "PLAYER SAVING");
        System.out.println();
        System.out.println("-------------------------------------------------------------------------------------------------------------------------");
        for(IPlayer player: offeredPlayers){
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
        for(IPlayer player: consideredPlayers){
            System.out.format("%-20s %-20s %8d %20d %20d %20d",
                    player.getPlayerName(), player.getPlayerPosition(), player.getPlayerSkating(), player.getPlayerShooting(), player.getPlayerChecking(), player.getPlayerSaving());
            System.out.println();
        }

        System.out.print("\nTo accept/reject the offer enter (y/n)\n");
    }

    public boolean inputTradeAcceptRejectBooleanFromUser(){

        try(BufferedReader br = new BufferedReader(new InputStreamReader(System.in))){
            do {
                String s = br.readLine();
                if (s.equalsIgnoreCase("y")){
                    return true;
                }
                else if (s.equalsIgnoreCase("n")){
                    return false;
                }
                else {
                    System.out.println("Invalid Input! Please try again");
                }
            }while (true);
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }
}
