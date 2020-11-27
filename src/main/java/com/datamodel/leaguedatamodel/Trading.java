package com.datamodel.leaguedatamodel;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;
import com.inputoutputmodel.DisplayTradingOffers;
import com.inputoutputmodel.IDisplayTradingOffers;

public class Trading implements ITrading {

	private ILeague league;
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
	private String IMPORT = "import";
	private String USER = "user";
	private final String SHREWD = "shrewd";
	private final String NORMAL = "normal";
	private final String GAMBLER = "gambler";
	private final int LOSS_POINT_RESET_COUNT = 0;
	private final int PLAYERS_COUNT = 30;


	public Trading(ILeague league){
		this.league = league;
		lossPoint = league.getGamePlayConfig().getTrading().getLossPoint();
		randomTradeOfferChance = league.getGamePlayConfig().getTrading().getRandomTradeOfferChance();
		shrewdValue = league.getGamePlayConfig().getTrading().getGMTable().getShrewd();
		normalValue = league.getGamePlayConfig().getTrading().getGMTable().getNormal();
		gamblerValue = league.getGamePlayConfig().getTrading().getGMTable().getGambler();
		maxPlayersPerTrade = league.getGamePlayConfig().getTrading().getMaxPlayersPerTrade();
		randomAcceptanceChance = league.getGamePlayConfig().getTrading().getRandomAcceptanceChance();
	}

	@Override
	public boolean isInterestedInPlayersTrade() {
		return isInterestedInPlayersTrade;
	}

	@Override
	public void setOfferingTeam(ITeam offeringTeam){
		this.offeringTeam = offeringTeam;
	}

	@Override
	public ITeam getOfferingTeam(){
		return offeringTeam;
	}

	@Override
	public void setAcceptingTeam(ITeam acceptingTeam){
		this.acceptingTeam = acceptingTeam;
	}

	@Override
	public ITeam getAcceptingTeam() {
		return acceptingTeam;
	}

	@Override
	public void setOfferedPlayers(ArrayList<IPlayer> offeredPlayers) {
		this.offeredPlayers = offeredPlayers;
	}

	@Override
	public ArrayList<IPlayer> getOfferedPlayers() {
		return offeredPlayers;
	}

	@Override
	public void setRequestedPlayers(ArrayList<IPlayer> requestedPlayers) {
		this.requestedPlayers = requestedPlayers;
	}

	@Override
	public ArrayList<IPlayer> getRequestedPlayers() {
		return requestedPlayers;
	}

	@Override
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

	@Override
	public ArrayList<ArrayList<Integer>> getPossibleTradeCombinations(){
		return tradingCombinations;
	}

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
		System.out.println(generatingTradeTeam.getTeamName());

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

		ArrayList<ITeam> teams = league.getAllTeams();

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

					double currentGainRatio = myGain/theirGain;
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
			System.out.println("Only draft trade possible");
		}
		else {
			ArrayList<IPlayer> playersOffered = generatingTradeTeamPlayersIndices.stream().map(generatingTradeTeam::getPlayer).collect(Collectors.toCollection(ArrayList::new));
			ArrayList<IPlayer> playersRequested = acceptingTradeTeamPlayersIndices.stream().map(acceptingTradeTeam::getPlayer).collect(Collectors.toCollection(ArrayList::new));
			ArrayList<IPlayer> hiredFreeAgents;

			try {
				hiredFreeAgents = generatingTradeTeam.getFreeAgentsHiredAfterTrade(playersOffered, league);
			} catch (Exception e) {
				System.out.println("Trading can't happen as not enough free agents to hire");
				generatingTradeTeam.setLossPointCount(LOSS_POINT_RESET_COUNT);
				return;
			}

			league.getFreeAgents().removeAll(hiredFreeAgents);

			try {
				acceptingTradeTeam.getFreeAgentsHiredAfterTrade(playersRequested, league);
			} catch (Exception e) {
				System.out.println("Trading can't happen as not enough free agents to hire");
				league.getFreeAgents().addAll(hiredFreeAgents);
				generatingTradeTeam.setLossPointCount(LOSS_POINT_RESET_COUNT);
				return;
			}

			league.getFreeAgents().addAll(hiredFreeAgents);

			if (acceptingTradeTeam.getTeamCreatedBy().equals(USER)){
				IDisplayTradingOffers offer = new DisplayTradingOffers();
				isInterestedInPlayersTrade = generateAiTradeOfferToUser(playersOffered, playersRequested, offer);
			}
			else{
				isInterestedInPlayersTrade = generateAiTradeOfferToAi(generatingTradeTeam);
			}

			if (isInterestedInPlayersTrade){
				offeringTeam = generatingTradeTeam;
				acceptingTeam = acceptingTradeTeam;
				offeredPlayers = playersOffered;
				requestedPlayers = playersRequested;
				System.out.println("Trade of players happened");
				System.out.println("offering team:"+offeringTeam.getTeamName());
				System.out.println("acceptingTeam team:"+acceptingTeam.getTeamName());
				System.out.println("offeredPlayers:"+offeredPlayers.size());
				System.out.println("requestedPlayers:"+requestedPlayers.size());
			}
		}
	}

	@Override
	public boolean generateAiTradeOfferToUser(ArrayList<IPlayer> aiTeamPlayers, ArrayList<IPlayer> userPlayers, IDisplayTradingOffers displayTradingOffers) {
		displayTradingOffers.displayOfferToUser(aiTeamPlayers, userPlayers);
		boolean isAccepted = displayTradingOffers.inputTradeAcceptRejectBooleanFromUser();
		if (isAccepted){
			return true;
		}
		return false;
	}

	@Override
	public boolean generateAiTradeOfferToAi(ITeam team) {
		double tradeAcceptanceChance = randomAcceptanceChance;
		switch (team.getGeneralManager().getGeneralManagerPersonality()) {
			case SHREWD:
				tradeAcceptanceChance += shrewdValue;
				break;
			case NORMAL:
				tradeAcceptanceChance += normalValue;
				break;
			case GAMBLER:
				tradeAcceptanceChance += gamblerValue;
				break;
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
		offeringTeam.setLossPointCount(LOSS_POINT_RESET_COUNT);
	}

	@Override
	public void tradeDraft(ITeam team, IDrafting drafting) {
		ITeam strongestTeam = league.getStrongestTeam();
		if (strongestTeam == team){
			System.out.println("This team is already the strongest. No benefit of generating a trade.");
			team.setLossPointCount(LOSS_POINT_RESET_COUNT);
			return;
		}
		ArrayList<IPlayer> strongestPlayers = strongestTeam.getStrongestPlayersByStrength(strongestTeam.getPlayers());

		ITeam[] teamPicks = drafting.getDraftPick(strongestTeam);
		int teamPickRound = -1;

		for (int i=teamPicks.length-1; i>=0 ; i--){
			if (drafting.getDraftPickByRound(team, i) == team){
				teamPickRound = i;
				break;
			}
		}

		if (teamPickRound == -1){
			System.out.println("Trade cannot happen as future draft picks are already traded");
		}
		else {
			ArrayList<IPlayer> playersToTrade = new ArrayList<>();
			playersToTrade.add(strongestPlayers.get(teamPickRound));
			try {
				strongestTeam.getFreeAgentsHiredAfterTrade(playersToTrade, league);
			} catch (Exception e) {
				System.out.println("Trading can't happen as not enough free agents to hire");
				team.setLossPointCount(LOSS_POINT_RESET_COUNT);
				return;
			}

			boolean isOfferAccepted;

			if (strongestTeam.getTeamCreatedBy().equals(USER)){
				IDisplayTradingOffers displayTradingOffers = new DisplayTradingOffers();
				isOfferAccepted = generateDraftPickOfferToUser(team, teamPickRound, playersToTrade, displayTradingOffers);
			}
			else{
				isOfferAccepted = generateDraftPickOfferToAi();
			}

			if (isOfferAccepted){
				System.out.println("Draft traded");
				drafting.setDraftPickByRound(team, strongestTeam, teamPickRound);
				team.addPlayer(playersToTrade.get(0));
				strongestTeam.removePlayer(playersToTrade.get(0));
				team.completeRoster(league);
				strongestTeam.completeRoster(league);
			}
			else {
				System.out.println("Draft offer rejected");
			}
		}
		team.setLossPointCount(LOSS_POINT_RESET_COUNT);
	}

	@Override
	public boolean generateDraftPickOfferToUser(ITeam team, int teamPickRound, ArrayList<IPlayer> playersToTrade, IDisplayTradingOffers displayTradingOffers) {
		displayTradingOffers.displayDraftOfferToUser(team, teamPickRound, playersToTrade);
		boolean isAccepted = displayTradingOffers.inputTradeAcceptRejectBooleanFromUser();
		if (isAccepted){
			return true;
		}
		return false;
	}

	@Override
	public boolean generateDraftPickOfferToAi() {
		if (Math.random() < randomAcceptanceChance) {
			return true;
		}
		return false;
	}
}