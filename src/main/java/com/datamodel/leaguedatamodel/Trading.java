package com.datamodel.leaguedatamodel;

import com.inputoutputmodel.IDisplayTradingOffers;
import com.inputoutputmodel.InputOutputModelAbstractFactory;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Trading implements ITrading {

	final static Logger logger = Logger.getLogger(Trading.class);

	private final ILeague league;
	private final int lossPoint;
	private final double randomTradeOfferChance;
	private final int maxPlayersPerTrade;
	private final double randomAcceptanceChance;
	private final double shrewdValue;
	private final double normalValue;
	private final double gamblerValue;
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

	public Trading() {
		league = LeagueDataModelAbstractFactory.instance().createLeague();
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
	public void setOfferingTeam(ITeam offeringTeam) {
		this.offeringTeam = offeringTeam;
	}

	@Override
	public ITeam getOfferingTeam() {
		return offeringTeam;
	}

	@Override
	public void setAcceptingTeam(ITeam acceptingTeam) {
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
															List<List<Integer>> allTradingCombinations) {
		if(maxPlayersAllowedPerTrade == 1) {
			List<List<Integer>> tradingCombinations = new ArrayList<>();
			for(int i = 0; i <= totalNoOfPlayers; i++) {
				tradingCombinations.add(new ArrayList<>(Arrays.asList(i)));
			}
			allTradingCombinations.addAll(tradingCombinations);
			return tradingCombinations;
		}
		List<List<Integer>> tradingCombinations = new ArrayList<>();
		List<List<Integer>> tradingCombinationsMinusOne = setPossibleTradeCombinations(totalNoOfPlayers,
				maxPlayersAllowedPerTrade - 1, allTradingCombinations);
		for(List<Integer> tradingCombination : tradingCombinationsMinusOne) {
			for(int i = tradingCombination.get(tradingCombination.size() - 1) + 1; i <= totalNoOfPlayers; i++) {
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
		if(team.getTeamCreatedBy().equals(IMPORT) && team.getLossPointCount() >= lossPoint) {
			if(Math.random() < randomTradeOfferChance) {
				logger.info(team.getTeamName() + " can generate a trade offer");
				return true;
			}
		}
		return false;
	}

	@Override
	public void generateBestTradeOffer(ITeam generatingTradeTeam) {
		isInterestedInPlayersTrade = false;
		offeringTeam = null;
		acceptingTeam = null;
		offeredPlayers = null;
		requestedPlayers = null;
		double bestGainRatio = 1.0;
		List<Integer> generatingTradeTeamPlayersIndices = null;
		List<Integer> acceptingTradeTeamPlayersIndices = null;
		ITeam acceptingTradeTeam = null;

		if(tradingCombinations == null) {
			tradingCombinations = new ArrayList<>();
			setPossibleTradeCombinations(PLAYERS_COUNT - 1, maxPlayersPerTrade, tradingCombinations);
			logger.debug("Possible trade combinations for " + generatingTradeTeam.getTeamName() + " is " + tradingCombinations.size());
		}

		List<ITeam> teams = league.getAllTeams();

		for(ITeam team : teams) {
			team.prepareForTrade();
		}

		for(List<Integer> offeredPlayersIndices : tradingCombinations) {
			int generatingTradeTeamSkatingStat = 0;
			int generatingTradeTeamShootingStat = 0;
			int generatingTradeTeamCheckingStat = 0;
			int generatingTradeTeamSavingStat = 0;

			for(int playerIndex : offeredPlayersIndices) {
				generatingTradeTeamSkatingStat += generatingTradeTeam.getPlayer(playerIndex).getPlayerSkating();
				generatingTradeTeamShootingStat += generatingTradeTeam.getPlayer(playerIndex).getPlayerShooting();
				generatingTradeTeamCheckingStat += generatingTradeTeam.getPlayer(playerIndex).getPlayerChecking();
				generatingTradeTeamSavingStat += generatingTradeTeam.getPlayer(playerIndex).getPlayerSaving();
			}

			for(ITeam opponentTeam : teams) {
				if(opponentTeam == generatingTradeTeam) {
					continue;
				}

				for(List<Integer> requestedPlayersIndices : tradingCombinations) {
					int acceptingTradeTeamSkatingStat = 0;
					int acceptingTradeTeamShootingStat = 0;
					int acceptingTradeTeamCheckingStat = 0;
					int acceptingTradeTeamSavingStat = 0;

					for(int playerIndex : requestedPlayersIndices) {
						acceptingTradeTeamSkatingStat += opponentTeam.getPlayer(playerIndex).getPlayerSkating();
						acceptingTradeTeamShootingStat += opponentTeam.getPlayer(playerIndex).getPlayerShooting();
						acceptingTradeTeamCheckingStat += opponentTeam.getPlayer(playerIndex).getPlayerChecking();
						acceptingTradeTeamSavingStat += opponentTeam.getPlayer(playerIndex).getPlayerSaving();
					}

					int differenceInSkatingStat = acceptingTradeTeamSkatingStat - generatingTradeTeamSkatingStat;
					int differenceInShootingStat = acceptingTradeTeamShootingStat - generatingTradeTeamShootingStat;
					int differenceInCheckingStat = acceptingTradeTeamCheckingStat - generatingTradeTeamCheckingStat;
					int differenceInSavingStat = acceptingTradeTeamSavingStat - generatingTradeTeamSavingStat;

					double myGain = generatingTradeTeam.getTradingGain(differenceInSkatingStat,
							differenceInShootingStat, differenceInCheckingStat, differenceInSavingStat);
					double theirGain = opponentTeam.getTradingGain(differenceInSkatingStat, differenceInShootingStat,
							differenceInCheckingStat, differenceInSavingStat);

					if(myGain <= 0.0 || theirGain <= 0.0) {
						continue;
					}

					double currentGainRatio = myGain / theirGain;
					if(currentGainRatio > bestGainRatio) {
						bestGainRatio = currentGainRatio;
						generatingTradeTeamPlayersIndices = offeredPlayersIndices;
						acceptingTradeTeamPlayersIndices = requestedPlayersIndices;
						acceptingTradeTeam = opponentTeam;
					}
				}
			}
		}

		if(acceptingTradeTeam == null) {
			isInterestedInPlayersTrade = false;
			logger.info("Could not find any team to trade for players with " + generatingTradeTeam.getTeamName());
		} else {
			List<IPlayer> playersOffered =
					generatingTradeTeamPlayersIndices.stream().map(generatingTradeTeam::getPlayer).collect(Collectors.toList());
			List<IPlayer> playersRequested =
					acceptingTradeTeamPlayersIndices.stream().map(acceptingTradeTeam::getPlayer).collect(Collectors.toList());
			List<IPlayer> hiredFreeAgents;

			try {
				hiredFreeAgents = generatingTradeTeam.getFreeAgentsHiredAfterTrade(playersOffered, league);
			} catch(Exception e) {
				logger.warn(generatingTradeTeam.getTeamName() + " could not hire free agents.");
				generatingTradeTeam.setLossPointCount(LOSS_POINT_RESET_COUNT);
				logger.info(generatingTradeTeam.getTeamName() + " loss point is reset to " + LOSS_POINT_RESET_COUNT +
						". Exiting trade for players.");
				return;
			}

			league.getFreeAgents().removeAll(hiredFreeAgents);
			logger.info("Removed " + hiredFreeAgents.size() + " free agents to check if the other team can still " +
					"complete their roster.");

			try {
				acceptingTradeTeam.getFreeAgentsHiredAfterTrade(playersRequested, league);
			} catch(Exception e) {
				logger.warn(acceptingTradeTeam.getTeamName() + " could not hire free agents.");
				league.getFreeAgents().addAll(hiredFreeAgents);
				logger.info("Added back " + hiredFreeAgents.size() + " free agents to the league that were removed to "
						+ "check the roster completeness.");
				generatingTradeTeam.setLossPointCount(LOSS_POINT_RESET_COUNT);
				logger.info(generatingTradeTeam.getTeamName() + " loss point is reset to " + LOSS_POINT_RESET_COUNT +
						". Exiting trade for players.");
				return;
			}

			league.getFreeAgents().addAll(hiredFreeAgents);
			logger.info("Added back " + hiredFreeAgents.size() + " free agents to the league that were removed to " +
					"check the roster completeness.");

			if(acceptingTradeTeam.getTeamCreatedBy().equals(USER)) {
				IDisplayTradingOffers displayTradingOffers =
						InputOutputModelAbstractFactory.instance().createDisplayTradingOffers();
				logger.info("Trade offer proposed to " + acceptingTradeTeam.getTeamName());
				isInterestedInPlayersTrade = generateAiTradeOfferToUser(playersOffered, playersRequested,
						displayTradingOffers);
			} else {
				logger.info("Trade offer proposed to " + acceptingTradeTeam.getTeamName());
				isInterestedInPlayersTrade = generateAiTradeOfferToAi(acceptingTradeTeam);
			}

			if(isInterestedInPlayersTrade) {
				offeringTeam = generatingTradeTeam;
				acceptingTeam = acceptingTradeTeam;
				offeredPlayers = playersOffered;
				requestedPlayers = playersRequested;
				logger.info("Team that generated the trade offer " + offeringTeam.getTeamName());
				logger.info("Players offered in the trade with stats in order (SKATING, SHOOTING, CHECKING, SAVING)");
				offeredPlayers.forEach((offeredPlayer) -> logger.info("Name --> " + offeredPlayer.getPlayerName() + ","
						+ " " + "Stats --> (" + offeredPlayer.getPlayerSkating() + ", " + offeredPlayer.getPlayerShooting() + ", " + offeredPlayer.getPlayerChecking() + ", " + offeredPlayer.getPlayerSaving() + ")"));
				logger.info("Team that accepted the trade offer " + acceptingTeam.getTeamName());
				logger.info("Players requested in the trade with stats in order (SKATING, SHOOTING, CHECKING, " +
						"SAVING)");
				requestedPlayers.forEach((requestedPlayer) -> logger.info("Name --> " + requestedPlayer.getPlayerName() + ", " + "Stats --> (" + requestedPlayer.getPlayerSkating() + ", " + requestedPlayer.getPlayerShooting() + ", " + requestedPlayer.getPlayerChecking() + ", " + requestedPlayer.getPlayerSaving() + ")"));
			}
		}
	}

	@Override
	public boolean generateAiTradeOfferToUser(List<IPlayer> aiTeamPlayers, List<IPlayer> userPlayers,
											  IDisplayTradingOffers displayTradingOffers) {
		displayTradingOffers.displayOfferToUser(aiTeamPlayers, userPlayers);
		boolean isAccepted = displayTradingOffers.inputTradeAcceptRejectBooleanFromUser();
		if(isAccepted) {
			logger.info("User team accepted the trade offer.");
			return true;
		}
		logger.info("User team rejected the trade offer.");
		return false;
	}

	@Override
	public boolean generateAiTradeOfferToAi(ITeam team) {
		double tradeAcceptanceChance = randomAcceptanceChance;
		switch(team.getGeneralManager().getGeneralManagerPersonality()) {
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
		if(Math.random() < tradeAcceptanceChance) {
			logger.info(team.getTeamName() + " accepted the trade offer.");
			return true;
		}
		logger.info(team.getTeamName() + " rejected the trade offer.");
		return false;
	}

	@Override
	public void tradePlayers() {
		for(IPlayer offeringTeamPlayer : offeredPlayers) {
			acceptingTeam.addPlayer(offeringTeamPlayer);
			logger.info(offeringTeamPlayer.getPlayerName() + " added to the team " + acceptingTeam.getTeamName());
		}
		for(IPlayer opponentTeamPlayer : requestedPlayers) {
			offeringTeam.addPlayer(opponentTeamPlayer);
			logger.info(opponentTeamPlayer.getPlayerName() + " added to the team " + offeringTeam.getTeamName());
		}
		for(IPlayer offeringTeamPlayer : offeredPlayers) {
			offeringTeam.removePlayer(offeringTeamPlayer);
			logger.info(offeringTeamPlayer.getPlayerName() + " removed from the team " + offeringTeam.getTeamName());
		}
		for(IPlayer opponentTeamPlayer : requestedPlayers) {
			acceptingTeam.removePlayer(opponentTeamPlayer);
			logger.info(opponentTeamPlayer.getPlayerName() + " removed from the team " + acceptingTeam.getTeamName());
		}
		offeringTeam.completeRoster(league);
		acceptingTeam.completeRoster(league);
		offeringTeam.setLossPointCount(LOSS_POINT_RESET_COUNT);
		logger.info(offeringTeam.getTeamName() + " loss point is reset to " + LOSS_POINT_RESET_COUNT);
	}

	@Override
	public void tradeDraft(ITeam team, IDrafting drafting) {
		ITeam strongestTeam = league.getStrongestTeam();
		if(strongestTeam == team) {
			logger.info("Cannot generate the draft pick offer with the team itself.");
			team.setLossPointCount(LOSS_POINT_RESET_COUNT);
			logger.info(team.getTeamName() + " loss point is reset to " + LOSS_POINT_RESET_COUNT + ". Exiting draft " + "pick trade.");
			return;
		}
		List<IPlayer> strongestPlayers = strongestTeam.getStrongestPlayersByStrength(strongestTeam.getPlayers());

		ITeam[] teamPicks = drafting.getDraftPick(strongestTeam);
		int teamPickRound = -1;

		for(int i = teamPicks.length - 1; i >= 0; i--) {
			if(drafting.getDraftPickByRound(team, i) == team) {
				teamPickRound = i;
				break;
			}
		}

		if(teamPickRound == -1) {
			logger.info("Cannot generate the draft pick offer for team " + team.getTeamName() + " as all the draft " + "picks are already traded.");
		} else {
			List<IPlayer> playersToTrade = new ArrayList<>();
			playersToTrade.add(strongestPlayers.get(teamPickRound));
			try {
				strongestTeam.getFreeAgentsHiredAfterTrade(playersToTrade, league);
			} catch(Exception e) {
				logger.warn(team.getTeamName() + " could not hire free agents.");
				team.setLossPointCount(LOSS_POINT_RESET_COUNT);
				logger.info(team.getTeamName() + " loss point is reset to " + LOSS_POINT_RESET_COUNT + ". Exiting " +
						"draft pick trade");
				return;
			}

			boolean isOfferAccepted;

			if(strongestTeam.getTeamCreatedBy().equals(USER)) {
				IDisplayTradingOffers displayTradingOffers =
						InputOutputModelAbstractFactory.instance().createDisplayTradingOffers();
				logger.info("Draft trade offer proposed to " + strongestTeam.getTeamName());
				isOfferAccepted = generateDraftPickOfferToUser(team, teamPickRound, playersToTrade,
						displayTradingOffers);
			} else {
				logger.info("Draft trade offer proposed to " + strongestTeam.getTeamName());
				isOfferAccepted = generateDraftPickOfferToAi();
			}

			if(isOfferAccepted) {
				drafting.setDraftPickByRound(team, strongestTeam, teamPickRound);
				logger.info((teamPickRound + 1) + " round draft pick of " + team.getTeamName() + " traded to " + strongestTeam.getTeamName());
				IPlayer tradedPlayer = playersToTrade.get(0);
				team.addPlayer(tradedPlayer);
				logger.info(tradedPlayer.getPlayerName() + " added to " + team.getTeamName() + " in lieu of the " +
						"future" + " draft round trade");
				logger.info(tradedPlayer.getPlayerName() + " STATS (SKATING, SHOOTING, CHECKING, SAVING) --> (" + tradedPlayer.getPlayerSkating() + ", " + tradedPlayer.getPlayerShooting() + ", " + tradedPlayer.getPlayerChecking() + ", " + tradedPlayer.getPlayerSaving() + ")");
				strongestTeam.removePlayer(playersToTrade.get(0));
				logger.info(tradedPlayer.getPlayerName() + " removed from team " + strongestTeam.getTeamName());
				team.completeRoster(league);
				strongestTeam.completeRoster(league);
			} else {
				logger.info("Draft trade pick offer rejected by " + strongestTeam.getTeamName());
			}
		}
		team.setLossPointCount(LOSS_POINT_RESET_COUNT);
		logger.info(team.getTeamName() + " loss point is reset to " + LOSS_POINT_RESET_COUNT + "");
	}

	@Override
	public boolean generateDraftPickOfferToUser(ITeam team, int teamPickRound, List<IPlayer> playersToTrade,
												IDisplayTradingOffers displayTradingOffers) {
		displayTradingOffers.displayDraftOfferToUser(team, teamPickRound, playersToTrade);
		boolean isAccepted = displayTradingOffers.inputTradeAcceptRejectBooleanFromUser();
		if(isAccepted) {
			logger.info("User team accepted the " + (teamPickRound + 1) + " draft round pick offered by " + team.getTeamName());
			return true;
		}
		logger.info("User team rejected the " + (teamPickRound + 1) + " draft round pick offered by " + team.getTeamName());
		return false;
	}

	@Override
	public boolean generateDraftPickOfferToAi() {
		if(Math.random() < randomAcceptanceChance) {
			logger.info("Accepted the future draft pick trade offer.");
			return true;
		}
		logger.info("Rejected the future draft pick trade offer.");
		return false;
	}
}