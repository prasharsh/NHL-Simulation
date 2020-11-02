package com.inputoutputmodel;
import java.util.Scanner;

public class LoadTeamPrompt {

    public String inputTeamName() {
        Scanner userInput = new Scanner(System.in);
        System.out.println("Load: Enter the team name");
        return userInput.nextLine().trim();
    }

    public void invalidTeamName() {
        System.out.println("Team Name does not exist! Try again");
    }
}
