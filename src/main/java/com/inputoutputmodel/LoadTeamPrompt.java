package com.inputoutputmodel;
import java.util.Scanner;

public class LoadTeamPrompt {

    private IDisplayToUser displayToUser;

    public LoadTeamPrompt(){
        displayToUser = new DisplayToUser();
    }

    public String inputTeamName() {
        Scanner userInput = new Scanner(System.in);
        displayToUser.displayMsgToUser("Load: Enter the team name");
        return userInput.nextLine().trim();
    }

    public void invalidTeamName() {
        displayToUser.displayMsgToUser("Team Name does not exist! Try again");
    }
}
