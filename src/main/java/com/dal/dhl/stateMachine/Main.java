package com.dal.dhl.stateMachine;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

import g4db.GameDB;
import g4db.IGameDB;
import g4dhl.Game;;

public class Main {

	public String setJsonPath() {
		Scanner pathInput = new Scanner(System.in);
		String path = pathInput.nextLine().trim();
		if (isNullOrEmpty(path)) {
			path = "loadTeam";
		} else {
			while (!(new File(path)).exists()) {
				System.out.println("Please Input a valid FileName: ");
				path = pathInput.nextLine().trim();
			}
		}
		return path;
	}

	public static boolean isNullOrEmpty(String str) {
		if (str != null && !str.isEmpty()) {
			return false;
		}
		return true;
	}

	public String containsKey(JSONObject jsonObj, String key) {
		String str;
		if (jsonObj.containsKey(key)) {
			str = (String) jsonObj.get(key);
			return str;
		}
		str = "Provide " + key + " in JSON file.";
		return str;
	}

	public static void main(String[] args) throws IOException, ParseException {

		Main objMain = new Main();
		Game game = new Game();
		IGameDB gameDB = new GameDB();
		LoadTeam loadTeam = new LoadTeam();
		ImportJson importJson = new ImportJson();
		CreateTeam newTeam = new CreateTeam();
		Scanner userInput = new Scanner(System.in);
		System.out.println("Enter path to JSON file: ");
//		"C:\\Users\\deepd\\OneDrive\\Desktop\\league.json" // path to file
		String filePath = objMain.setJsonPath();

		if (filePath == "loadTeam") {
			loadTeam.loadtTeam();
		} else {
			game.addLeague(importJson.parseJson(filePath));
			System.out.println("JSON Imported Successfully...");
			System.out.println("Do you want to create a new team?");
			System.out.println("1. YES");
			System.out.println("2. NO. Create League");
			int userChoice = userInput.nextInt();
			switch (userChoice) {
			case 1:
				newTeam.createNewTeam(game);
				System.out.println("League Data Saved with new team!!");
//					TO DO: add to db
				game.saveToDb(gameDB);
				break;
			case 2:
//				  TO DO: add to db
				game.saveToDb(gameDB);
				System.out.println("League Data Saved!!");
				break;
			default:
				System.out.println("Invalid Choice");
				break;
			}

		}
		System.out.println("How many seasons you want to simulate");
		int noOfSeason = userInput.nextInt();
		for (int i = 0; i < noOfSeason; i++) {
			System.out.println("Season " + i + " simulated");
		}
	}
}
