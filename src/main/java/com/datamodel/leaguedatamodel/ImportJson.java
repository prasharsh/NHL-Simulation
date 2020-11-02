package com.datamodel.leaguedatamodel;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.sql.Date;
import java.util.Calendar;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.datamodel.gameplayconfig.AgingConfig;
import com.datamodel.gameplayconfig.GameResolverConfig;
import com.datamodel.gameplayconfig.GameplayConfig;
import com.datamodel.gameplayconfig.IAgingConfig;
import com.datamodel.gameplayconfig.IGameResolverConfig;
import com.datamodel.gameplayconfig.IGameplayConfig;
import com.datamodel.gameplayconfig.IInjuryConfig;
import com.datamodel.gameplayconfig.ITradingConfig;
import com.datamodel.gameplayconfig.ITrainingConfig;
import com.datamodel.gameplayconfig.InjuryConfig;
import com.datamodel.gameplayconfig.TradingConfig;
import com.datamodel.gameplayconfig.TrainingConfig;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.inputoutputmodel.IPropertyLoader;
import com.inputoutputmodel.PropertyLoader;

public class ImportJson {

	private static final String SEASON_START_DATE = "seasonStartDate";

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
			System.out.println("Error in json:" + errorMsg.substring(errorMsg.indexOf(":") + 1));
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
		IAgingConfig aging = new AgingConfig();
		aging.setAverageRetirementAge(averageRetirementAge);
		aging.setMaximumAge(maximumAge);

		JSONObject gameResolverObj = containObjectKey(gameplayConfigObj, "gameResolver");
		float randomWinChance = containFloatKey(gameResolverObj, "randomWinChance");
		IGameResolverConfig gameResolver = new GameResolverConfig();
		gameResolver.setRandomWinChance(randomWinChance);

		JSONObject injuriesObj = containObjectKey(gameplayConfigObj, "injuries");
		float randomInjuryChance = containFloatKey(injuriesObj, "randomInjuryChance");
		int injuryDaysLow = containIntKey(injuriesObj, "injuryDaysLow");
		int injuryDaysHigh = containIntKey(injuriesObj, "injuryDaysHigh");
		IInjuryConfig injuries = new InjuryConfig();
		injuries.setRandomInjuryChance(randomInjuryChance);
		injuries.setInjuryDaysLow(injuryDaysLow);
		injuries.setInjuryDaysHigh(injuryDaysHigh);

		JSONObject trainingObj = containObjectKey(gameplayConfigObj, "training");
		int daysUntilStatIncreaseCheck = containIntKey(trainingObj, "daysUntilStatIncreaseCheck");
		ITrainingConfig training = new TrainingConfig();
		training.setDaysUntilStatIncreaseCheck(daysUntilStatIncreaseCheck);

		JSONObject tradingObj = containObjectKey(gameplayConfigObj, "trading");
		int lossPoint = containIntKey(tradingObj, "lossPoint");
		float randomTradeOfferChance = containFloatKey(tradingObj, "randomTradeOfferChance");
		int maxPlayersPerTrade = containIntKey(tradingObj, "maxPlayersPerTrade");
		float randomAcceptanceChance = containFloatKey(tradingObj, "randomAcceptanceChance");
		ITradingConfig trading = new TradingConfig();
		trading.setLossPoint(lossPoint);
		trading.setRandomTradeOfferChance(randomTradeOfferChance);
		trading.setMaxPlayersPerTrade(maxPlayersPerTrade);
		trading.setRandomAcceptanceChance(randomAcceptanceChance);

		IGameplayConfig gameplayConfig = new GameplayConfig();
		ILeague leagueObj = new League();
		gameplayConfig.setAging(aging);
		gameplayConfig.setGameResolver(gameResolver);
		gameplayConfig.setInjury(injuries);
		gameplayConfig.setTraining(training);
		gameplayConfig.setTrading(trading);
		leagueObj.setGamePlayConfig(gameplayConfig);

		String leagueName = containStringKey(jsonObject, "leagueName");
		leagueObj.setLeagueName(leagueName);
		int currentYear = Calendar.getInstance().get(Calendar.YEAR);
		IPropertyLoader propertyLoader = new PropertyLoader();
		String currentDate = currentYear + propertyLoader.getPropertyValue(SEASON_START_DATE);
		leagueObj.setCurrentDate(Date.valueOf(currentDate));
		leagueObj.setSimulationStartDate(Date.valueOf(currentDate));
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
					teamObj.setTeamCreatedBy("import");
					teamObj.setGeneralManager(generalManagerObj);
					teamObj.setHeadCoach(headCoachObj);
					int captainCount = 0;
					int skaterCount = 0;
					int goalieCount = 0;
					for (int i = 0; i < playersArray.size(); i++) {
						JSONObject player = (JSONObject) playersArray.get(i);
						String playerName = containStringKey(player, "playerName");
						String playerPosition = containStringKey(player, "position");
						Boolean isPlayerCaptain = containKeyCaptain(player, "captain");
						if (playerPosition.equals("goalie")) {
							goalieCount++;
						} else {
							skaterCount++;
						}
						if (isPlayerCaptain) {
							captainCount++;
						}
						int playerAgeYear = containIntKey(player, "age");
						int playerAgeDays = 274;
						int playerSkating = containIntKey(player, "skating");
						int playerShooting = containIntKey(player, "shooting");
						int playerChecking = containIntKey(player, "checking");
						int playerSaving = containIntKey(player, "saving");
						IPlayer playerObj = new Player();
						playerObj.setPlayerName(playerName);
						playerObj.setPlayerPosition(playerPosition);
						playerObj.setPlayerCaptain(isPlayerCaptain);
						playerObj.setPlayerAgeYear(playerAgeYear);
						playerObj.setPlayerAgeDays(367);
						playerObj.setPlayerSkating(playerSkating);
						playerObj.setPlayerShooting(playerShooting);
						playerObj.setPlayerChecking(playerChecking);
						playerObj.setPlayerSaving(playerSaving);
						teamObj.addPlayer(playerObj);
					}
					if (playersArray.size() != 20 || skaterCount != 18 || goalieCount != 2 || captainCount != 1) {
						System.out.println("ERROR: A team should have 18 skaters, 2 goalies and 1 captain.");
						System.out.println(leagueName + " -> " + conferenceName + " -> " + divisionName + " -> "
								+ teamName + " has " + skaterCount + " skaters, " + goalieCount + " goalies and "
								+ captainCount + " captain(s).");
						System.exit(1);
					}
					divisionObj.addTeam(teamObj);
				}
				conferenceObj.addDivision(divisionObj);
			}
			leagueObj.addConference(conferenceObj);
		}
		JSONArray freeAgentsArray = containArray(jsonObject, "freeAgents");
		int skaterCount = 0;
		int goalieCount = 0;
		for (int x = 0; x < freeAgentsArray.size(); x++) {
			JSONObject freeAgent = (JSONObject) freeAgentsArray.get(x);
			String freeAgentName = containStringKey(freeAgent, "playerName");
			String freeAgentPosition = containStringKey(freeAgent, "position");
			int freeAgentAgeYear = containIntKey(freeAgent, "age");
			int freeAgentAgeDays = 274;
			int freeAgentSkating = containIntKey(freeAgent, "skating");
			int freeAgentShooting = containIntKey(freeAgent, "shooting");
			int freeAgentChecking = containIntKey(freeAgent, "checking");
			int freeAgentSaving = containIntKey(freeAgent, "saving");
			IPlayer freeAgentObj = new FreeAgent();
			freeAgentObj.setPlayerName(freeAgentName);
			freeAgentObj.setPlayerPosition(freeAgentPosition);
			freeAgentObj.setPlayerAgeYear(freeAgentAgeYear);
			freeAgentObj.setPlayerAgeDays(freeAgentAgeDays);
			freeAgentObj.setPlayerSkating(freeAgentSkating);
			freeAgentObj.setPlayerShooting(freeAgentShooting);
			freeAgentObj.setPlayerChecking(freeAgentChecking);
			freeAgentObj.setPlayerSaving(freeAgentSaving);
			leagueObj.addFreeAgent(freeAgentObj);
			if (freeAgentPosition.equals("goalie")) {
				goalieCount++;
			} else {
				skaterCount++;
			}
		}
		if (skaterCount < 18) {
			System.out.println(skaterCount + " skater(s) found! At least 18 required to create a team.");
			System.exit(1);
		}
		if (goalieCount < 2) {
			System.out.println(goalieCount + " goalie(s) found! At least 2 goalies required to form a team.");
			System.exit(1);
		}

		JSONArray managersArray = containArray(jsonObject, "generalManagers");
		if (managersArray.size() < 1) {
			System.out.println("At least one manager is required to form a team.");
			System.exit(1);
		}
		for (int y = 0; y < managersArray.size(); y++) {
			String managerName = (String) managersArray.get(y);
			IGeneralManager managerObj = new GeneralManager();
			managerObj.setGeneralManagerName(managerName);
			leagueObj.setManager(managerObj);
		}

		JSONArray coachesArray = containArray(jsonObject, "coaches");
		if (coachesArray.size() < 1) {
			System.out.println("At least one coach is required to form a team.");
			System.exit(1);
		}
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
