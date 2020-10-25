package com.dal.dhl.stateMachine;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.sql.Date;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import g4dhl.Aging;
import g4dhl.Conference;
import g4dhl.Division;
import g4dhl.FreeAgent;
import g4dhl.GameResolver;
import g4dhl.GameplayConfig;
import g4dhl.GeneralManager;
import g4dhl.HeadCoach;
import g4dhl.IAging;
import g4dhl.IConference;
import g4dhl.IDivision;
import g4dhl.IFreeAgent;
import g4dhl.IGameResolver;
import g4dhl.IGameplayConfig;
import g4dhl.IGeneralManager;
import g4dhl.IHeadCoach;
import g4dhl.IInjury;
import g4dhl.ILeague;
import g4dhl.IPlayer;
import g4dhl.ITeam;
import g4dhl.ITrading;
import g4dhl.ITraining;
import g4dhl.Injury;
import g4dhl.League;
import g4dhl.Player;
import g4dhl.Team;
import g4dhl.Trading;
import g4dhl.Training;

public class ImportJson {

	public ILeague parseJson(String filePath) {
		Object jsonObj = null;
		try {
			JSONParser parser = new JSONParser();

			Reader reader = new FileReader(filePath);
			jsonObj = parser.parse(reader);
			Reader readerValidation = new FileReader(filePath);

			StringBuilder buffer = new StringBuilder();
			char[] arr = new char[8 * 1024];
			int numCharsRead;
			while ((numCharsRead = readerValidation.read(arr, 0, arr.length)) != -1) {
				buffer.append(arr, 0, numCharsRead);
			}
			String jsonString = buffer.toString();

			Gson gson = new Gson();
			gson.fromJson(jsonString, Object.class);

		} catch (JsonSyntaxException ej) {
			String errorMsg = ej.getLocalizedMessage();
			System.out.println("Error in json:" + errorMsg.substring(errorMsg.indexOf(":") + 1, errorMsg.length()));
			System.exit(1);

		} catch (IOException e) {
			System.out.println(e.getMessage());
			System.exit(1);
		} catch (ParseException e) {
			System.out.println(e.getMessage());
			System.exit(1);
		}
		JSONObject jsonObject = (JSONObject) jsonObj;

		JSONObject gameplayConfigObj = containObjectKey(jsonObject, "gameplayConfig");

		JSONObject agingObj = containObjectKey(gameplayConfigObj, "aging");
		int averageRetirementAge = containIntKey(agingObj, "averageRetirementAge");
		int maximumAge = containIntKey(agingObj, "maximumAge");
		IAging aging = new Aging();
		aging.setAverageRetirementAge(averageRetirementAge);
		aging.setMaximumAge(maximumAge);

		JSONObject gameResolverObj = containObjectKey(gameplayConfigObj, "gameResolver");
		float randomWinChance = containFloatKey(gameResolverObj, "randomWinChance");
		IGameResolver gameResolver = new GameResolver();
		gameResolver.setRandomWinChance(randomWinChance);

		JSONObject injuriesObj = containObjectKey(gameplayConfigObj, "injuries");
		float randomInjuryChance = containFloatKey(injuriesObj, "randomInjuryChance");
		int injuryDaysLow = containIntKey(injuriesObj, "injuryDaysLow");
		int injuryDaysHigh = containIntKey(injuriesObj, "injuryDaysHigh");
		IInjury injuries = new Injury();
		injuries.setRandomInjuryChance(randomInjuryChance);
		injuries.setInjuryDaysLow(injuryDaysLow);
		injuries.setInjuryDaysHigh(injuryDaysHigh);

		JSONObject trainingObj = containObjectKey(gameplayConfigObj, "training");
		int daysUntilStatIncreaseCheck = containIntKey(trainingObj, "daysUntilStatIncreaseCheck");
		ITraining training = new Training();
		training.setDaysUntilStatIncreaseCheck(daysUntilStatIncreaseCheck);

		JSONObject tradingObj = containObjectKey(gameplayConfigObj, "trading");
		int lossPoint = containIntKey(tradingObj, "lossPoint");
		float randomTradeOfferChance = containFloatKey(tradingObj, "randomTradeOfferChance");
		int maxPlayersPerTrade = containIntKey(tradingObj, "maxPlayersPerTrade");
		float randomAcceptanceChance = containFloatKey(tradingObj, "randomAcceptanceChance");
		ITrading trading = new Trading();
		trading.setLossPoint(lossPoint);
		trading.setRandomTradeOfferChance(randomTradeOfferChance);
		trading.setMaxPlayersPerTrade(maxPlayersPerTrade);
		trading.setRandomAcceptanceChance(randomAcceptanceChance);

		IGameplayConfig gameplayConfig = new GameplayConfig();
		gameplayConfig.setAging(aging);
		gameplayConfig.setGameResolver(gameResolver);
		gameplayConfig.setInjury(injuries);
		gameplayConfig.setTraining(training);
		gameplayConfig.setTrading(trading);

		ILeague leagueObj = new League();
		String leagueName = containStringKey(jsonObject, "leagueName");
		leagueObj.setLeagueName(leagueName);
		String str = "2020-09-30";
		leagueObj.setCurrentDate(Date.valueOf(str));
		JSONArray conferencesArray = containArray(jsonObject, "conferences");
		for (int a = 0; a < conferencesArray.size(); a++) {
			JSONObject conference = (JSONObject) conferencesArray.get(a);
			String conferenceName = containStringKey(conference, "conferenceName");
			JSONArray divisionsArray = containArray(conference, "divisions");
			IConference conferenceObj = new Conference();
			conferenceObj.setConferenceName(conferenceName);
			for (int b = 0; b < divisionsArray.size(); b++) {
				JSONObject division = (JSONObject) divisionsArray.get(b);
				String divisionName = containStringKey(division, "divisionName");
				JSONArray teamsArray = containArray(division, "teams");
				IDivision divisionObj = new Division();
				divisionObj.setDivisionName(divisionName);
				for (int c = 0; c < teamsArray.size(); c++) {
					JSONObject team = (JSONObject) teamsArray.get(c);
					String generalManagerName = containStringKey(team, "generalManager");
					JSONObject headCoach = containObjectKey(team, "headCoach");
					String headCoachName = containStringKey(headCoach, "name");
					float headCoachSkating = containFloatKey(headCoach, "skating");
					float headCoachShooting = containFloatKey(headCoach, "shooting");
					float headCoachChecking = containFloatKey(headCoach, "checking");
					float headCoachSaving = containFloatKey(headCoach, "saving");
					String teamName = containStringKey(team, "teamName");
					IGeneralManager generalManagerObj = new GeneralManager();
					generalManagerObj.setGeneralManagerName(generalManagerName);
					IHeadCoach headCoachObj = new HeadCoach();
					headCoachObj.setHeadCoachName(headCoachName);
					headCoachObj.setHeadCoachSkating(headCoachSkating);
					headCoachObj.setHeadCoachShooting(headCoachShooting);
					headCoachObj.setHeadCoachChecking(headCoachChecking);
					headCoachObj.setHeadCoachSaving(headCoachSaving);
					JSONArray playersArray = containArray(team, "players");
					ITeam teamObj = new Team();
					teamObj.setTeamName(teamName);
					teamObj.setGeneralManager(generalManagerObj);
					teamObj.setHeadCoach(headCoachObj);
					int captainCount = 0;
					for (int i = 0; i < playersArray.size(); i++) {
						JSONObject player = (JSONObject) playersArray.get(i);
						String playerName = containStringKey(player, "playerName");
						String playerPosition = containStringKey(player, "position");
						Boolean isPlayerCaptain = containKeyCaptain(player, "captain");
						if (isPlayerCaptain) {
							captainCount = captainCount + 1;
							if (captainCount > 1) {
								System.out.println("There is more than one caption in team: " + teamName);
								System.exit(1);
							}
						}
						int playerAge = containIntKey(player, "age");
						int playerSkating = containIntKey(player, "skating");
						int playerShooting = containIntKey(player, "shooting");
						int playerChecking = containIntKey(player, "checking");
						int playerSaving = containIntKey(player, "saving");
						IPlayer playerObj = new Player();
						playerObj.setPlayerName(playerName);
						playerObj.setPlayerPosition(playerPosition);
						playerObj.setPlayerCaptain(isPlayerCaptain);
						playerObj.setPlayerAge(playerAge);
						playerObj.setPlayerSkating(playerSkating);
						playerObj.setPlayerShooting(playerShooting);
						playerObj.setPlayerChecking(playerChecking);
						playerObj.setPlayerSaving(playerSaving);
						double strength = playerObj.getPlayerStrength();
						teamObj.addPlayer(playerObj);
					}
					divisionObj.addTeam(teamObj);
				}
				conferenceObj.addDivision(divisionObj);
			}
			leagueObj.addConference(conferenceObj);
		}
		JSONArray freeAgentsArray = containArray(jsonObject, "freeAgents");
		for (int x = 0; x < freeAgentsArray.size(); x++) {
			JSONObject freeAgent = (JSONObject) freeAgentsArray.get(x);
			String freeAgentName = containStringKey(freeAgent, "playerName");
			String freeAgentPosition = containStringKey(freeAgent, "position");
			int freeAgentAge = containIntKey(freeAgent, "age");
			int freeAgentSkating = containIntKey(freeAgent, "skating");
			int freeAgentShooting = containIntKey(freeAgent, "shooting");
			int freeAgentChecking = containIntKey(freeAgent, "checking");
			int freeAgentSaving = containIntKey(freeAgent, "saving");
			IFreeAgent freeAgentObj = new FreeAgent();
			freeAgentObj.setFreeAgentName(freeAgentName);
			freeAgentObj.setFreeAgentPosition(freeAgentPosition);
			freeAgentObj.setFreeAgentAge(freeAgentAge);
			freeAgentObj.setFreeAgentSkating(freeAgentSkating);
			freeAgentObj.setFreeAgentShooting(freeAgentShooting);
			freeAgentObj.setFreeAgentChecking(freeAgentChecking);
			freeAgentObj.setFreeAgentSaving(freeAgentSaving);
			leagueObj.addFreeAgent(freeAgentObj);
		}
		JSONArray managersArray = containArray(jsonObject, "generalManagers");
		for (int y = 0; y < managersArray.size(); y++) {
			String managerName = (String) managersArray.get(y);
			IGeneralManager managerObj = new GeneralManager();
			managerObj.setGeneralManagerName(managerName);
			leagueObj.setManager(managerObj);
		}

		JSONArray coachesArray = containArray(jsonObject, "coaches");
		for (int z = 0; z < coachesArray.size(); z++) {
			JSONObject coaches = (JSONObject) coachesArray.get(z);
			String coachName = containStringKey(coaches, "name");
			float coachSkating = containFloatKey(coaches, "skating");
			float coachShooting = containFloatKey(coaches, "shooting");
			float coachChecking = containFloatKey(coaches, "checking");
			float coachSaving = containFloatKey(coaches, "saving");
			IHeadCoach coachObj = new HeadCoach();
			coachObj.setHeadCoachName(coachName);
			coachObj.setHeadCoachSkating(coachSkating);
			coachObj.setHeadCoachShooting(coachShooting);
			coachObj.setHeadCoachChecking(coachChecking);
			coachObj.setHeadCoachSaving(coachSaving);
			leagueObj.setCoach(coachObj);
		}

		return leagueObj;
	}

	public String containStringKey(JSONObject obj, String key) {
		if (!obj.containsKey(key)) {
			System.out.println("Invalid JSON, It does not have " + key + " information");
			System.exit(1);
		}
		String hasKey = "";
		try {
			hasKey = (String) obj.get(key);
		} catch (Exception e) {
			System.out.println("Invalid JSON, It has invalid value for" + key);
			System.exit(1);
		}

		if (hasKey == null || hasKey.trim().isEmpty()) {
			System.out.println("Invalid JSON, It does not have value for the " + key);
			System.exit(1);
		}
		return hasKey;
	}

	enum PlayerStats {
		SKATING, SHOOTING, CHECKING, SAVING
	}

	public int containIntKey(JSONObject obj, String key) {

		if (!obj.containsKey(key)) {
			System.out.println("Invalid JSON, It does not have " + key + " information");
			System.exit(1);
		}
		int value = 0;
		try {
			value = (int) (long) obj.get(key);
		} catch (Exception e) {
			System.out.println("Invalid JSON, It has invalid player states value for " + key);
			System.exit(1);
		}
		PlayerStats[] allStats = PlayerStats.values();
		for (PlayerStats PlayerStat : allStats)
			if (PlayerStat.name().equalsIgnoreCase(key) && (value < 1 || value > 20)) {
				System.out.println("Invalid JSON, It has invalid Player stats value for " + key);
				System.exit(1);
			}
		return value;
	}

	public float containFloatKey(JSONObject obj, String key) {
		if (!obj.containsKey(key)) {
			System.out.println("Invalid JSON, It does not have " + key + " information");
			System.exit(1);
		}
		float value = 0;
		try {
			value = (float) (double) obj.get(key);
		} catch (Exception e) {
			System.out.println("Invalid JSON, It has invalid headCoach states value for " + key);
			System.exit(1);
		}
		if (value < 0 || value > 1) {
			System.out.println("Invalid JSON, It has invalid headCoach stats value for " + key);
			System.exit(1);
		}
		return value;
	}

	public Boolean containKeyCaptain(JSONObject obj, String key) {
		if (!obj.containsKey(key)) {
			System.out.println("Invalid JSON, It does not have " + key + " information");
			System.exit(1);
		}
		if (obj.get(key) == null) {
			System.out.println("Invalid JSON, It does not have value for " + key);
			System.exit(1);
		}
		Boolean hasKeyCaptain = false;
		try {
			hasKeyCaptain = (Boolean) obj.get(key);
		} catch (Exception e) {
			System.out.println("Invalid JSON, It has invalid value for Player stats Captain");
			System.exit(1);
		}
		return hasKeyCaptain;
	}

	public JSONArray containArray(JSONObject obj, String arrayKey) {
		if (!obj.containsKey(arrayKey)) {
			System.out.println("Invalid JSON, It does not have " + arrayKey + " information");
			System.exit(1);
		}
		JSONArray hasArray = (JSONArray) obj.get(arrayKey);
		if (hasArray == null || hasArray.size() == 0) {
			System.out.println("Invalid JSON, It does not have value for the " + arrayKey);
			System.exit(1);
		}
		return hasArray;
	}

	public JSONObject containObjectKey(JSONObject obj, String objectKey) {
		if (!obj.containsKey(objectKey)) {
			System.out.println("Invalid JSON, It does not have " + objectKey + " information");
			System.exit(1);
		}
		JSONObject jsonObject = (JSONObject) obj.get(objectKey);

		return jsonObject;
	}

}
