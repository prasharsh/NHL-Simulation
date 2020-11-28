package com.datamodel.leaguedatamodel;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import com.inputoutputmodel.DisplayTradingOffers;
import com.inputoutputmodel.IDisplayTradingOffers;
import org.apache.log4j.Logger;

public class Trading implements ITrading {

	final static Logger logger = Logger.getLogger(Trading.class);

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
	private List<IPlayer> offeredPlayers;
	private List<IPlayer> requestedPlayers;
	private List<List<Integer>> tradingCombinations;
	private final String IMPORT = "import";
	private final String USER = "user";
	private final String SHREWD = "shrewd";
	private final String NORMAL = "normal";
	private final String GAMBLER = "gambler";
	private final int LOSS_POINT_RESET_COUNT = 0;
	private final int PLAYERS_COUNT = 30;

	public Trading(){
		league = LeagueDataModelAbstractFactory.instance().createLeague();
		lossPoint = league.getGamePlayConfig().getTrading().getLossPoint();
		randomTradeOfferChance = league.getGamePlayConfig().getTrading().getRandomTradeOfferChance();
		shrewdValue = league.getGamePlayConfig().getTrading().getGMTable().getShrewd();
		normalValue = league.getGamePlayConfig().getTrading().getGMTable().getNormal();
		gamblerValue = league.getGamePlayConfig().getTrading().getGMTable().getGambler();
		maxPlayersPerTrade = league.getGamePlayConfig().getTrading().getMaxPlayersPerTrade();
		randomAcceptanceChance = league.getGamePlayConfig().getTrading().getRandomAcceptanceChance();
	}

//	public Trading(ILeague league){
//		this.league = league;
//		lossPoint = league.getGamePlayConfig().getTrading().getLossPoint();
//		randomTradeOfferChance = league.getGamePlayConfig().getTrading().getRandomTradeOfferChance();
//		shrewdValue = league.getGamePlayConfig().getTrading().getGMTable().getShrewd();
//		normalValue = league.getGamePlayConfig().getTrading().getGMTable().getNormal();
//		gamblerValue = league.getGamePlayConfig().getTrading().getGMTable().getGambler();
//		maxPlayersPerTrade = league.getGamePlayConfig().getTrading().getMaxPlayersPerTrade();
//		randomAcceptanceChance = league.getGamePlayConfig().getTrading().getRandomAcceptanceChance();
//	}

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
	public void setOfferedPlayers(List<IPlayer> offeredPlayers) {
		this.offeredPlayers = offeredPlayers;
	}

	@Override
	public List<IPlayer> getOfferedPlayers() {
		return offeredPlayers;
	}

	@Override
	public void setRequestedPlayers(List<IPlayer> requestedPlayers) {
		this.requestedPlayers = requestedPlayers;
	}

	@Override
	public List<IPlayer> getRequestedPlayers() {
		return requestedPlayers;
	}

	@Override
	public List<List<Integer>> setPossibleTradeCombinations(int totalNoOfPlayers, int maxPlayersAllowedPerTrade,
																	  List<List<Integer>> allTradingCombinations){
		if(maxPlayersAllowedPerTrade ==1){
			List<List<Integer>> tradingCombinations = new ArrayList<>();
			for(int i = 0; i<= totalNoOfPlayers; i++)
			{
				tradingCombinations.add(new ArrayList<>(Arrays.asList(new Integer[]{i})));
			}
			allTradingCombinations.addAll(tradingCombinations);
			return tradingCombinations;
		}
		List<List<Integer>> tradingCombinations = new ArrayList<>();
		List<List<Integer>> tradingCombinationsMinusOne = setPossibleTradeCombinations(totalNoOfPlayers, maxPlayersAllowedPerTrade -1, allTradingCombinations);
		for(List<Integer> tradingCombination : tradingCombinationsMinusOne)
		{
			for(int i = tradingCombination.get(tradingCombination.size()-1)+1; i<= totalNoOfPlayers; i++)
			{
				List<Integer> newTradingCombination = new ArrayList<>(tradingCombination);
				newTradingCombination.add(i);
				tradingCombinations.add(newTradingCombination);
			}
		}
		allTradingCombinations.addAll(tradingCombinations);
		return tradingCombinations;
	}

	@Override
	public boolean isTradePossible(ITeam team) {
		if (team.getTeamCreatedBy().equals(IMPORT) && team.getLossPointCount() >= lossPoint) {
			if (Math.random() < randomTradeOfferChance) {
				logger.info(team.getTeamName()+" can generate a trade offer");
				return true;
			}
		}
		return false;
	}

	@Override
	public void generateBestTradeOffer(ITeam generatingTradeTeam){
		logger.debug("entered generateBestTradeOffer()");
		isInterestedInPlayersTrade = false;
		offeringTeam = null;
		acceptingTeam = null;
		offeredPlayers = null;
		requestedPlayers = null;
		double bestGainRatio = 1.0;
		List<Integer>  generatingTradeTeamPlayersIndices = null;
		List<Integer>  acceptingTradeTeamPlayersIndices = null;
		ITeam acceptingTradeTeam = null;

		if (tradingCombinations == null){
			tradingCombinations = new ArrayList<>();
			setPossibleTradeCombinations(PLAYERS_COUNT-1, maxPlayersPerTrade, tradingCombinations);
			logger.debug("Possible trade combinations for "+generatingTradeTeam.getTeamName()+" is "+tradingCombinations.size());
		}

		List<ITeam> teams = league.getAllTeams();

		for (ITeam team: teams){
			team.prepareForTrade();
		}

		for (List<Integer> offeredPlayersIndices: tradingCombinations){
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

				for (List<Integer> requestedPlayersIndices: tradingCombinations){
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
			logger.debug("Could not find any team to trade players with "+generatingTradeTeam.getTeamName());
		}
		else {
			List<IPlayer> playersOffered = generatingTradeTeamPlayersIndices.stream().map(generatingTradeTeam::getPlayer).collect(Collectors.toList());
			List<IPlayer> playersRequested = acceptingTradeTeamPlayersIndices.stream().map(acceptingTradeTeam::getPlayer).collect(Collectors.toList());
			List<IPlayer> hiredFreeAgents;

			try {
				hiredFreeAgents = generatingTradeTeam.getFreeAgentsHiredAfterTrade(playersOffered, league);
			} catch (Exception e) {
				logger.warn(generatingTradeTeam.getTeamName()+" could not hire free agents. Exiting trade for players.");
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
		logger.debug("exited generateBestTradeOffer()");
	}

	@Override
	public boolean generateAiTradeOfferToUser(List<IPlayer> aiTeamPlayers, List<IPlayer> userPlayers, IDisplayTradingOffers displayTradingOffers) {
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
		List<IPlayer> strongestPlayers = strongestTeam.getStrongestPlayersByStrength(strongestTeam.getPlayers());

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
			List<IPlayer> playersToTrade = new ArrayList<>();
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
	public boolean generateDraftPickOfferToUser(ITeam team, int teamPickRound, List<IPlayer> playersToTrade, IDisplayTradingOffers displayTradingOffers) {
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