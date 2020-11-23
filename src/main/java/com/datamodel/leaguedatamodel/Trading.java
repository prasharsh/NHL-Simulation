package com.datamodel.leaguedatamodel;
import static com.datamodel.leaguedatamodel.Constants.DEFENSE;
import static com.datamodel.leaguedatamodel.Constants.FORWARD;
import static com.datamodel.leaguedatamodel.Constants.GOALIE;
import static com.datamodel.leaguedatamodel.Constants.SKATER;
import static com.datamodel.leaguedatamodel.Constants.SHREWD;
import static com.datamodel.leaguedatamodel.Constants.NORMAL;
import static com.datamodel.leaguedatamodel.Constants.GAMBLER;
import static com.datamodel.leaguedatamodel.Constants.IMPORT;
import static com.datamodel.leaguedatamodel.Constants.USER;
import static com.datamodel.leaguedatamodel.Constants.LOSS_POINT_RESET_COUNT;
import static com.datamodel.leaguedatamodel.Constants.PLAYERS_COUNT;
import static com.datamodel.leaguedatamodel.Constants.SKATERS_COUNT;
import static com.datamodel.leaguedatamodel.Constants.GOALIES_COUNT;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;

import com.datamodel.gameplayconfig.ITradingConfig;
import com.inputoutputmodel.DisplayRoster;
import com.inputoutputmodel.DisplayTradingOffers;
import com.inputoutputmodel.IDisplayRoaster;
import com.inputoutputmodel.IDisplayTradingOffers;

public class Trading implements ITrading {

	private ILeague league;
	private ArrayList<ITeam> teams;
	private int lossPoint;
	private double randomTradeOfferChance;
	private int maxPlayersPerTrade;
	private double randomAcceptanceChance;
	private double shrewdValue;
	private double normalValue;
	private double gamblerValue;
	private boolean isInterestedInPlayersTrade;
	private ITeam offeringTeam;
	private ITeam acceptingTeam;
	private ArrayList<IPlayer> offeredPlayers;
	private ArrayList<IPlayer> requestedPlayers;
	private ArrayList<ArrayList<Integer>> tradingCombinations;

	private boolean checkIfLeagueIsNull(ILeague league) {
		return league == null;
	}

	private boolean checkIfTeamsAreEmptyOrNull(ArrayList<ITeam> teams) {
		return teams == null || teams.size() == 0;
	}

	private boolean checkIfPlayersAreEmptyOrNull(ArrayList<IPlayer> players) {
		return players == null || players.size() == 0;
	}

	private boolean checkIfFreeAgentsAreEmptyOrNull(ArrayList<IPlayer> freeAgents) {
		return freeAgents == null || freeAgents.size() == 0;
	}

	public Trading(){
	}

	public Trading(ILeague league){
		this.league = league;
		this.teams = league.getAllTeams();
		lossPoint = league.getGamePlayConfig().getTrading().getLossPoint();
		randomTradeOfferChance = league.getGamePlayConfig().getTrading().getRandomTradeOfferChance();
		shrewdValue = league.getGamePlayConfig().getTrading().getGMTable().getShrewd();
		normalValue = league.getGamePlayConfig().getTrading().getGMTable().getNormal();
		gamblerValue = league.getGamePlayConfig().getTrading().getGMTable().getGambler();
		maxPlayersPerTrade = league.getGamePlayConfig().getTrading().getMaxPlayersPerTrade();
		randomAcceptanceChance = league.getGamePlayConfig().getTrading().getRandomAcceptanceChance();
	}

	@Override
	public boolean isBestOfferGenerated() {
		return isInterestedInPlayersTrade;
	}

	public ArrayList<ArrayList<Integer>> setPossibleTradeCombinations(int totalNoOfPlayers, int maxPlayersAllowedPerTrade,
																	  ArrayList<ArrayList<Integer>> allTradingCombinations){
		if(maxPlayersAllowedPerTrade ==1){
			ArrayList<ArrayList<Integer>> tradingCombinations = new ArrayList<>();
			for(int i = 0; i<= totalNoOfPlayers; i++)
			{
				tradingCombinations.add(new ArrayList<>(Arrays.asList(new Integer[]{i})));
			}
			allTradingCombinations.addAll(tradingCombinations);
			return tradingCombinations;
		}
		ArrayList<ArrayList<Integer>> tradingCombinations = new ArrayList<>();
		ArrayList<ArrayList<Integer>> tradingCombinationsMinusOne = setPossibleTradeCombinations(totalNoOfPlayers, maxPlayersAllowedPerTrade -1, allTradingCombinations);
		for(ArrayList<Integer> tradingCombination : tradingCombinationsMinusOne)
		{
			for(int i = tradingCombination.get(tradingCombination.size()-1)+1; i<= totalNoOfPlayers; i++)
			{
				ArrayList<Integer> newTradingCombination = new ArrayList<>(tradingCombination);
				newTradingCombination.add(i);
				tradingCombinations.add(newTradingCombination);
			}
		}
		allTradingCombinations.addAll(tradingCombinations);
		return tradingCombinations;
	}

//	public ArrayList<ArrayList<Integer>> setPossibleTradeCombinations(ArrayList<IPlayer> players,
//																	  int totalNoOfPlayers,
//																	  int maxPlayersAllowedPerTrade,
//																	  ArrayList<ArrayList<Integer>> allTradingCombinations,
//																	  ArrayList<Integer> sums){
//		if(maxPlayersAllowedPerTrade ==1){
//			ArrayList<ArrayList<Integer>> tradingCombinations = new ArrayList();
//			for(int i = 0; i<= totalNoOfPlayers; i++)
//			{
//				tradingCombinations.add(new ArrayList<Integer>(Arrays.asList(new Integer[]{i})));
//				sums.add(players.get(i).getPlayerSkating());
//			}
//			allTradingCombinations.addAll(tradingCombinations);
//			return tradingCombinations;
//		}
//		ArrayList<ArrayList<Integer>> tradingCombinations = new ArrayList<>();
//		ArrayList<ArrayList<Integer>> tradingCombinationsMinusOne = setPossibleTradeCombinations(totalNoOfPlayers, maxPlayersAllowedPerTrade -1, allTradingCombinations);
//		int idx = allTradingCombinations.size()-tradingCombinationsMinusOne.size();
//		for(ArrayList<Integer> tradingCombination : tradingCombinationsMinusOne)
//		{
//			for(int i = tradingCombination.get(tradingCombination.size()-1)+1; i<= totalNoOfPlayers; i++)
//			{
//				ArrayList<Integer> newTradingCombination = new ArrayList(tradingCombination);
//				newTradingCombination.add(i);
//				sums.add(sums.get(idx)+players.get(i).getPlayerSkating());
//				tradingCombinations.add(newTradingCombination);
//			}
//			idx += 1;
//		}
//		allTradingCombinations.addAll(tradingCombinations);
//		return tradingCombinations;
//	}
//
//	public ArrayList<IPlayer> getOfferedPlayers(){
//		return offeredPlayers;
//	}
//
//	public ArrayList<IPlayer> getRequestedPlayers(){
//		return requestedPlayers;
//	}

	@Override
	public boolean isTradePossible(ITeam team) {
		if (team.getTeamCreatedBy().equals(IMPORT) && team.getLossPointCount() >= lossPoint) {
			if (Math.random() < randomTradeOfferChance) {
				return true;
			}
		}
		return false;
	}

	@Override
	public void generateBestTradeOffer(ITeam generatingTradeTeam){

		isInterestedInPlayersTrade = false;
		offeringTeam = null;
		acceptingTeam = null;
		offeredPlayers = null;
		requestedPlayers = null;
		double bestGainRatio = 1.0;
		ArrayList<Integer>  generatingTradeTeamPlayersIndices = null;
		ArrayList<Integer>  acceptingTradeTeamPlayersIndices = null;
		ITeam acceptingTradeTeam = null;

		if (tradingCombinations == null){
			tradingCombinations = new ArrayList<>();
			setPossibleTradeCombinations(PLAYERS_COUNT-1, maxPlayersPerTrade, tradingCombinations);
		}

		for (ITeam team: teams){
			team.prepareForTrade();
		}

		for (ArrayList<Integer> offeredPlayersIndices: tradingCombinations){
			int generatingTradeTeamSkatingStat = 0;
			int generatingTradeTeamShootingStat = 0;
			int generatingTradeTeamCheckingStat = 0;
			int generatingTradeTeamSavingStat = 0;

			for (int playerIndex: offeredPlayersIndices){
				generatingTradeTeamSkatingStat += generatingTradeTeam.getPlayer(playerIndex).getPlayerSkating();
				generatingTradeTeamShootingStat += generatingTradeTeam.getPlayer(playerIndex).getPlayerShooting();
				generatingTradeTeamCheckingStat += generatingTradeTeam.getPlayer(playerIndex).getPlayerChecking();
				generatingTradeTeamSavingStat += generatingTradeTeam.getPlayer(playerIndex).getPlayerSaving();
			}

			for (ITeam opponentTeam: teams){
				if (opponentTeam == generatingTradeTeam){
					continue;
				}

				for (ArrayList<Integer> requestedPlayersIndices: tradingCombinations){
					int acceptingTradeTeamSkatingStat = 0;
					int acceptingTradeTeamShootingStat = 0;
					int acceptingTradeTeamCheckingStat = 0;
					int acceptingTradeTeamSavingStat = 0;

					for (int playerIndex: requestedPlayersIndices){
						acceptingTradeTeamSkatingStat += opponentTeam.getPlayer(playerIndex).getPlayerSkating();
						acceptingTradeTeamShootingStat += opponentTeam.getPlayer(playerIndex).getPlayerShooting();
						acceptingTradeTeamCheckingStat += opponentTeam.getPlayer(playerIndex).getPlayerChecking();
						acceptingTradeTeamSavingStat += opponentTeam.getPlayer(playerIndex).getPlayerSaving();
					}

					int differenceInSkatingStat = acceptingTradeTeamSkatingStat - generatingTradeTeamSkatingStat;
					int differenceInShootingStat = acceptingTradeTeamShootingStat - generatingTradeTeamShootingStat;
					int differenceInCheckingStat = acceptingTradeTeamCheckingStat - generatingTradeTeamCheckingStat;
					int differenceInSavingStat = acceptingTradeTeamSavingStat - generatingTradeTeamSavingStat;

					double myGain = generatingTradeTeam.getTradingGain(differenceInSkatingStat, differenceInShootingStat,
							differenceInCheckingStat, differenceInSavingStat);
					double theirGain = opponentTeam.getTradingGain(differenceInSkatingStat, differenceInShootingStat,
							differenceInCheckingStat, differenceInSavingStat);

					if (myGain <= 0.0 || theirGain <= 0.0){
						continue;
					}

					double currentGainRatio = myGain/ theirGain;
					if (currentGainRatio > bestGainRatio)
					{
						bestGainRatio = currentGainRatio;
						generatingTradeTeamPlayersIndices = offeredPlayersIndices;
						acceptingTradeTeamPlayersIndices = requestedPlayersIndices;
						acceptingTradeTeam = opponentTeam;
					}
				}
			}
		}

		if (acceptingTradeTeam == null){
			isInterestedInPlayersTrade = false;
		}
		else {
			ArrayList<IPlayer> playersOffered = generatingTradeTeamPlayersIndices.stream().map(generatingTradeTeam::getPlayer).collect(Collectors.toCollection(ArrayList::new));
			ArrayList<IPlayer> playersRequested = acceptingTradeTeamPlayersIndices.stream().map(acceptingTradeTeam::getPlayer).collect(Collectors.toCollection(ArrayList::new));
			ArrayList<IPlayer> hiredFreeAgents;

			try {
				hiredFreeAgents = generatingTradeTeam.getFreeAgentsHiredAfterTrade(playersOffered, league);
			} catch (Exception e) {
				System.out.println("Trading can't happen as not enough free agents to hire");
				e.printStackTrace();
				return;
			}

			league.getFreeAgents().removeAll(hiredFreeAgents);

			try {
				acceptingTradeTeam.getFreeAgentsHiredAfterTrade(playersRequested, league);
			} catch (Exception e) {
				league.getFreeAgents().addAll(hiredFreeAgents);
				System.out.println("Trading can't happen as not enough free agents to hire");
				e.printStackTrace();
				return;
			}

			league.getFreeAgents().addAll(hiredFreeAgents);

			if (acceptingTradeTeam.getTeamCreatedBy().equals(USER)){
				isInterestedInPlayersTrade = generateAiTradeOfferToUser(generatingTradeTeam, playersOffered, acceptingTradeTeam, playersRequested);
			}
			else{
				isInterestedInPlayersTrade = generateAiTradeOfferToAi(generatingTradeTeam, playersOffered, acceptingTradeTeam, playersRequested);
			}

			if (isInterestedInPlayersTrade){
				offeringTeam = generatingTradeTeam;
				acceptingTeam = acceptingTradeTeam;
				offeredPlayers = playersOffered;
				requestedPlayers = playersRequested;
			}
		}
	}

	@Override
	public boolean generateAiTradeOfferToUser(ITeam aiTeam, ArrayList<IPlayer> aiTeamPlayers,
											ITeam userTeam, ArrayList<IPlayer> userPlayers) {
		IDisplayTradingOffers offer = new DisplayTradingOffers();
		offer.displayOfferToUser(aiTeamPlayers, userPlayers);
		boolean isAccepted = offer.inputTradeAcceptRejectBooleanFromUser();
		if (isAccepted){
			return true;
		}
		return false;
	}

	@Override
	public boolean generateAiTradeOfferToAi(ITeam offeringTeam, ArrayList<IPlayer> offeringTeamPlayers,
										  ITeam opponentTeam, ArrayList<IPlayer> opponentTeamPlayers) {
		double tradeAcceptanceChance = randomAcceptanceChance;
		if (opponentTeam.getGeneralManager().getGeneralManagerPersonality().equals(SHREWD)){
			tradeAcceptanceChance += shrewdValue;
		}
		else if (opponentTeam.getGeneralManager().getGeneralManagerPersonality().equals(NORMAL)){
			tradeAcceptanceChance += normalValue;
		}
		else if (opponentTeam.getGeneralManager().getGeneralManagerPersonality().equals(GAMBLER)){
			tradeAcceptanceChance += gamblerValue;
		}
		if (Math.random() < tradeAcceptanceChance) {
			return true;
		}
		return false;
	}

	@Override
	public void tradePlayers() {
		for (IPlayer offeringTeamPlayer : offeredPlayers) {
			acceptingTeam.addPlayer(offeringTeamPlayer);
		}
		for (IPlayer opponentTeamPlayer : requestedPlayers) {
			offeringTeam.addPlayer(opponentTeamPlayer);
		}
		for (IPlayer offeringTeamPlayer : offeredPlayers) {
			offeringTeam.removePlayer(offeringTeamPlayer);
		}
		for (IPlayer opponentTeamPlayer : requestedPlayers) {
			acceptingTeam.removePlayer(opponentTeamPlayer);
		}
		offeringTeam.completeRoster(league);
		acceptingTeam.completeRoster(league);
	}

	@Override
	public void tradeDraft() {
		System.out.println("Trade Draft");
		offeringTeam.completeRoster(league);
		acceptingTeam.completeRoster(league);
	}

//	private void generateAiTradeOfferToAi(ITeam offeringTeam, ArrayList<IPlayer> offeringTeamPlayers,
//										  ITeam opponentTeam, ArrayList<IPlayer> opponentTeamPlayers) {
//		double offeringTeamPlayersStrength = calculateTotalStrengthOfPlayers(offeringTeamPlayers);
//		double opponentTeamPlayersStrength = calculateTotalStrengthOfPlayers(opponentTeamPlayers);
//
//		if (offeringTeamPlayersStrength > opponentTeamPlayersStrength) {
//			acceptTradeOffer(offeringTeam, offeringTeamPlayers, opponentTeam, opponentTeamPlayers);
//			adjustAiTeamRoaster(offeringTeam);
//			adjustAiTeamRoaster(opponentTeam);
//		} else {
//			if (Math.random() < randomAcceptanceChance) {
//				acceptTradeOffer(offeringTeam, offeringTeamPlayers, opponentTeam, opponentTeamPlayers);
//				adjustAiTeamRoaster(offeringTeam);
//				adjustAiTeamRoaster(opponentTeam);
//			}
//		}
//	}

	//	end*****************************************************************************************************************

	@Override
	public void startTrading(ITradingConfig trading, ILeague league, ArrayList<ITeam> teams) {
		if (checkIfLeagueIsNull(league)) {
			return;
		}
		if (checkIfTeamsAreEmptyOrNull(teams)) {
			return;
		}
		if (checkIfFreeAgentsAreEmptyOrNull(league.getFreeAgents())) {
			return;
		}

		this.league = league;
		this.teams = teams;

		lossPoint = trading.getLossPoint();
		randomTradeOfferChance = trading.getRandomTradeOfferChance();
		maxPlayersPerTrade = trading.getMaxPlayersPerTrade();
		randomAcceptanceChance = trading.getRandomAcceptanceChance();
		checkForAiTradeOffers();
	}

	private void checkForAiTradeOffers() {
		for (ITeam team : teams) {
			if (team.getTeamCreatedBy().equals(IMPORT) && team.getLossPointCount() >= lossPoint) {
				if (Math.random() < randomTradeOfferChance) {
					generateAiTradeOffer(team, teams);
				}
			}
		}
	}

	private void generateAiTradeOffer(ITeam aiTeam, ArrayList<ITeam> teams) {
		ArrayList<IPlayer> aiTeamWeakestPlayers = sortPlayersOnStrength(aiTeam.getPlayers(), maxPlayersPerTrade, true);
		IPlayer aiWeakestPlayer = aiTeamWeakestPlayers.get(0);
		for (int i = 1; i < aiTeamWeakestPlayers.size(); i++) {
			if (aiWeakestPlayer.getPlayerPosition().equals(aiTeamWeakestPlayers.get(i).getPlayerPosition())) {
				continue;
			}
			aiTeamWeakestPlayers.removeAll(aiTeamWeakestPlayers.subList(i, aiTeamWeakestPlayers.size()));
			break;
		}

		ITeam StrongestTeam = null;
		ArrayList<IPlayer> StrongestTeamStrongestPlayers = null;
		double StrongestTeamStrongestPlayersStrength = 0.0;
		for (ITeam opponentTeam : teams) {
			if (aiTeam.equals(opponentTeam)) {
				continue;
			}
			ArrayList<IPlayer> opponentTeamPlayersWithPosition = getPlayersWithPosition(opponentTeam.getPlayers(),
					aiWeakestPlayer.getPlayerPosition());
			ArrayList<IPlayer> opponentTeamStrongestPlayers = sortPlayersOnStrength(opponentTeamPlayersWithPosition,
					aiTeamWeakestPlayers.size(), false);
			double opponentTeamStrongestPlayersStrength = calculateTotalStrengthOfPlayers(opponentTeamStrongestPlayers);
			if (opponentTeamStrongestPlayersStrength > StrongestTeamStrongestPlayersStrength) {
				StrongestTeam = opponentTeam;
				StrongestTeamStrongestPlayers = opponentTeamStrongestPlayers;
				StrongestTeamStrongestPlayersStrength = opponentTeamStrongestPlayersStrength;
			}
		}

		if (StrongestTeam == null) {
			return;
		}
		if (StrongestTeam.getTeamCreatedBy().equals(USER)) {
			generateAiTradeOfferToUser(aiTeam, aiTeamWeakestPlayers, StrongestTeam, StrongestTeamStrongestPlayers);
		} else if (StrongestTeam.getTeamCreatedBy().equals(IMPORT)) {
			generateAiTradeOfferToAi(aiTeam, aiTeamWeakestPlayers, StrongestTeam, StrongestTeamStrongestPlayers);
		}
		aiTeam.setLossPointCount(LOSS_POINT_RESET_COUNT);
	}

	@Override
	public void acceptTradeOffer(ITeam offeringTeam, ArrayList<IPlayer> offeringTeamPlayers, ITeam opponentTeam,
								 ArrayList<IPlayer> opponentTeamPlayers) {
		for (IPlayer offeringTeamPlayer : offeringTeamPlayers) {
			opponentTeam.addPlayer(offeringTeamPlayer);
		}
		for (IPlayer opponentTeamPlayer : opponentTeamPlayers) {
			offeringTeam.addPlayer(opponentTeamPlayer);
		}
		for (IPlayer offeringTeamPlayer : offeringTeamPlayers) {
			offeringTeam.removePlayer(offeringTeamPlayer);
		}
		for (IPlayer opponentTeamPlayer : opponentTeamPlayers) {
			opponentTeam.removePlayer(opponentTeamPlayer);
		}
	}

//	************************************************************************************************

	@Override
	public double calculateTotalStrengthOfPlayers(ArrayList<IPlayer> players) {
		double strength = 0.0;
		if (checkIfPlayersAreEmptyOrNull(players)) {
			return strength;
		}
		for (IPlayer player : players) {
			strength += player.getPlayerStrength();
		}
		return strength;
	}

	@Override
	public ArrayList<IPlayer> getPlayersWithPosition(ArrayList<IPlayer> players, String position) {
		if (checkIfPlayersAreEmptyOrNull(players) || position == null) {
			return null;
		}
		ArrayList<IPlayer> playersWithPosition = new ArrayList<>();
		if (position.equals(SKATER)) {
			for (IPlayer player : players) {
				if (player.getPlayerPosition().equals(FORWARD) || player.getPlayerPosition().equals(DEFENSE)) {
					if (player.isPlayerRetired()){
						continue;
					}
					playersWithPosition.add(player);
				}
			}
			return playersWithPosition;
		}
		for (IPlayer player : players) {
			if (player.getPlayerPosition().equals(position)) {
				if (player.isPlayerRetired()){
					continue;
				}
				playersWithPosition.add(player);
			}
		}
		return playersWithPosition;
	}

	@Override
	public ArrayList<IPlayer> sortPlayersOnStrength(ArrayList<IPlayer> playersToBeSorted, int playersCount,
													final boolean ascending) {
		if (checkIfPlayersAreEmptyOrNull(playersToBeSorted)) {
			return null;
		}
		if (playersCount <= 0) {
			return null;
		}
		ArrayList<IPlayer> players = new ArrayList<>();
		for (IPlayer player:playersToBeSorted){
			if (player.isPlayerRetired()){
				continue;
			}
			players.add(player);
		}
		players.sort((player1, player2) -> {
			if (ascending) {
				return Double.compare(player1.getPlayerStrength(), player2.getPlayerStrength());
			}
			return Double.compare(player2.getPlayerStrength(), player1.getPlayerStrength());
		});
		if (playersCount >= playersToBeSorted.size()) {
			return players;
		}
		return new ArrayList<>(players.subList(0, playersCount));
	}

	private void adjustAiTeamRoaster(ITeam aiTeam) {
		int skatersCount = aiTeam.getPlayingSkatersCount();
		int goaliesCount = aiTeam.getPlayingGoaliesCount();
		if (skatersCount > SKATERS_COUNT) {
			dropWeakestPlayersToFreeAgentList(league, aiTeam, SKATER, skatersCount - SKATERS_COUNT);
		} else if (skatersCount < SKATERS_COUNT) {
			hireStrongestPlayersFromFreeAgentList(league, aiTeam, SKATER, SKATERS_COUNT - skatersCount);
		}
		if (goaliesCount > GOALIES_COUNT) {
			dropWeakestPlayersToFreeAgentList(league, aiTeam, GOALIE, goaliesCount - GOALIES_COUNT);
		} else if (goaliesCount < GOALIES_COUNT) {
			hireStrongestPlayersFromFreeAgentList(league, aiTeam, GOALIE, GOALIES_COUNT - goaliesCount);
		}
	}

	@Override
	public void dropWeakestPlayersToFreeAgentList(ILeague league, ITeam team, String playerPosition, int count) {
		ArrayList<IPlayer> players = getPlayersWithPosition(team.getPlayers(), playerPosition);
		ArrayList<IPlayer> weakestPlayers = sortPlayersOnStrength(players, count, true);
		for (IPlayer player : weakestPlayers) {
			league.addFreeAgent(player);
		}
		for (IPlayer player : weakestPlayers) {
			team.removePlayer(player);
		}
	}

	@Override
	public void hireStrongestPlayersFromFreeAgentList(ILeague league, ITeam team, String freeAgentPosition, int count) {
		ArrayList<IPlayer> freeAgents = getFreeAgentsWithPosition(league.getFreeAgents(), freeAgentPosition);
		ArrayList<IPlayer> strongestFreeAgents = sortFreeAgentsOnStrength(freeAgents, count, false);
		for (IPlayer freeAgent : strongestFreeAgents) {
			team.addPlayer(freeAgent);
		}
		for (IPlayer freeAgent : strongestFreeAgents) {
			league.removeFreeAgent(freeAgent);
		}

	}

	@Override
	public ArrayList<IPlayer> sortFreeAgentsOnStrength(ArrayList<IPlayer> freeAgentsToBeSorted, int freeAgentsCount, final boolean ascending) {
		if (checkIfFreeAgentsAreEmptyOrNull(freeAgentsToBeSorted)) {
			return null;
		}
		if (freeAgentsCount <= 0) {
			return null;
		}
		ArrayList<IPlayer> freeAgents = new ArrayList<>();
		for (IPlayer freeAgent: freeAgentsToBeSorted){
			if (freeAgent.isPlayerRetired()){
				continue;
			}
			freeAgents.add(freeAgent);
		}
		freeAgents.sort((freeAgent1, freeAgent2) -> {
			if (ascending) {
				return Double.compare(freeAgent1.getPlayerStrength(), freeAgent2.getPlayerStrength());
			}
			return Double.compare(freeAgent2.getPlayerStrength(), freeAgent1.getPlayerStrength());
		});
		if (freeAgentsCount >= freeAgentsToBeSorted.size()) {
			return freeAgents;
		}
		return new ArrayList<>(freeAgents.subList(0, freeAgentsCount));
	}

	@Override
	public ArrayList<IPlayer> getFreeAgentsWithPosition(ArrayList<IPlayer> freeAgents, String position) {
		if (checkIfFreeAgentsAreEmptyOrNull(freeAgents) || position == null) {
			return null;
		}
		ArrayList<IPlayer> freeAgentsWithPosition = new ArrayList<>();
		if (position.equals(SKATER)) {
			for (IPlayer freeAgent : freeAgents) {
				if (freeAgent.getPlayerPosition().equals(FORWARD) || freeAgent.getPlayerPosition().equals(DEFENSE)) {
					if (freeAgent.isPlayerRetired()){
						continue;
					}
					freeAgentsWithPosition.add(freeAgent);
				}
			}
			return freeAgentsWithPosition;
		}
		for (IPlayer freeAgent : freeAgents) {
			if (freeAgent.getPlayerPosition().equals(position)) {
				if (freeAgent.isPlayerRetired()){
					continue;
				}
				freeAgentsWithPosition.add(freeAgent);
			}
		}
		return freeAgentsWithPosition;
	}

	private void adjustUserTeamRoaster(ITeam userTeam) {
		int skatersCount = userTeam.getPlayingSkatersCount();
		int goaliesCount = userTeam.getPlayingGoaliesCount();
		if (skatersCount > SKATERS_COUNT) {
			dropPlayersFromUserTeam(userTeam, SKATER, skatersCount - SKATERS_COUNT);
		} else if (skatersCount < SKATERS_COUNT) {
			hirePlayersForUserTeam(userTeam, SKATER, SKATERS_COUNT - skatersCount);
		}
		if (goaliesCount > GOALIES_COUNT) {
			dropPlayersFromUserTeam(userTeam, GOALIE, goaliesCount - GOALIES_COUNT);
		} else if (goaliesCount < GOALIES_COUNT) {
			hirePlayersForUserTeam(userTeam, GOALIE, GOALIES_COUNT - goaliesCount);
		}
	}

	private void dropPlayersFromUserTeam(ITeam team, String playerPosition, int count) {
		ArrayList<IPlayer> players = getPlayersWithPosition(team.getPlayers(), playerPosition);
		IDisplayRoaster displayRoaster = new DisplayRoster();
		displayRoaster.displayPlayersToBeDropped(players, count);
		ArrayList<Integer> playerIndexes = new ArrayList<>();
		for (int i = 0; i < count; i++) {
			while (true) {
				int index = displayRoaster.inputPlayerIndexToDrop();
				if (index >= 0 && index < players.size()) {
					playerIndexes.add(index);
					break;
				} else {
					displayRoaster.displayMessageToUser("Id not found");
				}
			}
		}
		for (int j : playerIndexes) {
			league.addFreeAgent(players.get(j));
		}
		for (int k : playerIndexes) {
			team.removePlayer(players.get(k));
		}
	}

	private void hirePlayersForUserTeam(ITeam team, String freeAgentPosition, int count) {
		ArrayList<IPlayer> freeAgents = getFreeAgentsWithPosition(league.getFreeAgents(), freeAgentPosition);
		IDisplayRoaster displayRoaster = new DisplayRoster();
		displayRoaster.displayFreeAgentsToBeHired(freeAgents, count);
		ArrayList<Integer> freeAgentsIndexes = new ArrayList<>();
		for (int i = 0; i < count; i++) {
			while (true) {
				int index = displayRoaster.inputFreeAgentIndexToHire();
				if (index >= 0 && index < freeAgents.size()) {
					freeAgentsIndexes.add(index);
					break;
				} else {
					displayRoaster.displayMessageToUser("Id not found");
				}
			}
		}
		for (int j : freeAgentsIndexes) {
			team.addPlayer(freeAgents.get(j));
		}
		for (int k : freeAgentsIndexes) {
			league.removeFreeAgent(freeAgents.get(k));
		}
	}
//	*****************************************************************************************************************






}