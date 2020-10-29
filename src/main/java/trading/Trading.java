package trading;

import UserInputOutput.DisplayRoaster;
import UserInputOutput.IDisplayRoaster;
import g4dhl.ILeague;
import g4dhl.ITeam;
import g4dhl.IFreeAgent;
import g4dhl.ITrading;
import g4dhl.IPlayer;
import UserInputOutput.DisplayTradingOffers;
import UserInputOutput.IDisplayTradingOffers;
import java.util.ArrayList;
import static trading.Constants.IMPORT;
import static trading.Constants.USER;
import static trading.Constants.SKATERS;
import static trading.Constants.GOALIES;
import static trading.Constants.SKATERS_COUNT;
import static trading.Constants.GOALIES_COUNT;
import static trading.Constants.LOSS_POINT_RESET_COUNT;

public class Trading {

    private ILeague league;
    private ArrayList<ITeam> teams;

    private int lossPoint;
    private double randomTradeOfferChance;
    private int maxPlayersPerTrade;
    private double randomAcceptanceChance;

    private boolean checkIfLeagueIsNull(ILeague league){
        return league == null;
    }

    private boolean checkIfTeamsAreEmptyOrNull(ArrayList<ITeam> teams){
        return teams == null || teams.size() == 0;
    }

    private boolean checkIfPlayersAreEmptyOrNull(ArrayList<IPlayer> players){
        return players == null || players.size() == 0;
    }

    private boolean checkIfFreeAgentsAreEmptyOrNull(ArrayList<IFreeAgent> freeAgents){
        return freeAgents == null || freeAgents.size() == 0;
    }

    public void startTrading(ITrading trading , ILeague league, ArrayList<ITeam> teams){
        if (checkIfLeagueIsNull(league)){
            return;
        }
        if (checkIfTeamsAreEmptyOrNull(teams)){
            return;
        }
        if (checkIfFreeAgentsAreEmptyOrNull(league.getFreeAgents())){
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

    private void checkForAiTradeOffers(){
        for (ITeam team: teams){
            if (team.getTeamCreatedBy().equals(IMPORT) && team.getLossPointCount() >= lossPoint){
                if(Math.random() < randomTradeOfferChance){
                    generateAiTradeOffer(team, teams);
                }
            }
        }
    }

    private void generateAiTradeOffer(ITeam aiTeam, ArrayList<ITeam> teams) {
        ArrayList<IPlayer> aiTeamWeakestPlayers = sortPlayersOnStrength(aiTeam.getPlayers(), maxPlayersPerTrade, true);
        IPlayer aiWeakestPlayer = aiTeamWeakestPlayers.get(0);

        for (int i=1; i< aiTeamWeakestPlayers.size(); i++){
            if (aiWeakestPlayer.getPlayerPosition().equals(aiTeamWeakestPlayers.get(i).getPlayerPosition())){
                continue;
            }
            aiTeamWeakestPlayers.removeAll(aiTeamWeakestPlayers.subList(i, aiTeamWeakestPlayers.size()));
            break;
        }

        ITeam StrongestTeam = null;
        ArrayList<IPlayer> StrongestTeamStrongestPlayers = null;
        double StrongestTeamStrongestPlayersStrength = 0.0;

        for (ITeam opponentTeam: teams){
            if (aiTeam.equals(opponentTeam)){
                continue;
            }

            ArrayList<IPlayer> opponentTeamPlayersWithPosition = getPlayersWithPosition(opponentTeam.getPlayers(), aiWeakestPlayer.getPlayerPosition());
            ArrayList<IPlayer> opponentTeamStrongestPlayers = sortPlayersOnStrength(opponentTeamPlayersWithPosition, aiTeamWeakestPlayers.size(), false);

            double opponentTeamStrongestPlayersStrength= calculateTotalStrengthOfPlayers(opponentTeamStrongestPlayers);

            if (opponentTeamStrongestPlayersStrength > StrongestTeamStrongestPlayersStrength){
                StrongestTeam = opponentTeam;
                StrongestTeamStrongestPlayers = opponentTeamStrongestPlayers;
                StrongestTeamStrongestPlayersStrength = opponentTeamStrongestPlayersStrength;
            }
        }

        if (StrongestTeam == null){
            return;
        }

        if (StrongestTeam.getTeamCreatedBy().equals(USER)){
            generateAiTradeOfferToUser(aiTeam, aiTeamWeakestPlayers, StrongestTeam, StrongestTeamStrongestPlayers);
        }
        else if (StrongestTeam.getTeamCreatedBy().equals(IMPORT)){
            generateAiTradeOfferToAi(aiTeam, aiTeamWeakestPlayers, StrongestTeam, StrongestTeamStrongestPlayers);
        }
        aiTeam.setLossPointCount(LOSS_POINT_RESET_COUNT);
    }

    private void generateAiTradeOfferToUser(ITeam aiTeam, ArrayList<IPlayer> aiTeamPlayers, ITeam userTeam, ArrayList<IPlayer> userPlayers) {
        IDisplayTradingOffers offer = new DisplayTradingOffers();
        offer.displayOfferToUser(aiTeamPlayers, userPlayers);
        boolean tradeAccepted = offer.inputTradeAcceptRejectBooleanFromUser();
        if (tradeAccepted){
            acceptTradeOffer(aiTeam, aiTeamPlayers, userTeam, userPlayers);
            adjustAiTeamRoaster(aiTeam);
            adjustUserTeamRoaster(userTeam);
        }
    }

    private void generateAiTradeOfferToAi(ITeam offeringTeam, ArrayList<IPlayer> offeringTeamPlayers, ITeam opponentTeam, ArrayList<IPlayer> opponentTeamPlayers) {
        double offeringTeamPlayersStrength = calculateTotalStrengthOfPlayers(offeringTeamPlayers);
        double opponentTeamPlayersStrength = calculateTotalStrengthOfPlayers(opponentTeamPlayers);

        if (offeringTeamPlayersStrength > opponentTeamPlayersStrength){
            acceptTradeOffer(offeringTeam, offeringTeamPlayers, opponentTeam, opponentTeamPlayers);
            adjustAiTeamRoaster(offeringTeam);
            adjustAiTeamRoaster(opponentTeam);
        }
        else {
            if (Math.random() < randomAcceptanceChance) {
                acceptTradeOffer(offeringTeam, offeringTeamPlayers, opponentTeam, opponentTeamPlayers);
                adjustAiTeamRoaster(offeringTeam);
                adjustAiTeamRoaster(opponentTeam);
            }
        }
    }

    public void acceptTradeOffer(ITeam offeringTeam, ArrayList<IPlayer> offeringTeamPlayers, ITeam opponentTeam, ArrayList<IPlayer> opponentTeamPlayers) {
        for (IPlayer offeringTeamPlayer: offeringTeamPlayers){
            opponentTeam.addPlayer(offeringTeamPlayer);
        }

        for (IPlayer opponentTeamPlayer: opponentTeamPlayers){
            offeringTeam.addPlayer(opponentTeamPlayer);
        }

        for (IPlayer offeringTeamPlayer: offeringTeamPlayers){
            offeringTeam.removePlayer(offeringTeamPlayer);
        }

        for (IPlayer opponentTeamPlayer: opponentTeamPlayers){
            opponentTeam.removePlayer(opponentTeamPlayer);
        }
    }

    public double calculateTotalStrengthOfPlayers(ArrayList<IPlayer> players) {
        double strength = 0.0;
        if (checkIfPlayersAreEmptyOrNull(players)){
            return strength;
        }
        for (IPlayer player: players){
            strength+= player.getPlayerStrength();
        }
        return strength;
    }

    public ArrayList<IPlayer> getPlayersWithPosition(ArrayList<IPlayer> playersWithPosition, String position){
        if (checkIfPlayersAreEmptyOrNull(playersWithPosition) || position == null){
            return null;
        }
        ArrayList<IPlayer> players = new ArrayList<>();
        for (IPlayer player: playersWithPosition){
            if (player.getPlayerPosition().equals(position)){
                players.add(player);
            }
        }
        return players;
    }

    public ArrayList<IPlayer> sortPlayersOnStrength(ArrayList<IPlayer> playersToBeSorted, int playersCount, final boolean ascending) {
        if (checkIfPlayersAreEmptyOrNull(playersToBeSorted)){
            return null;
        }
        if (playersCount <= 0){
            return null;
        }
        ArrayList<IPlayer> players = new ArrayList<>(playersToBeSorted);
        players.sort((player1, player2) -> {
            if (ascending) {
                return Double.compare(player1.getPlayerStrength(), player2.getPlayerStrength());
            }
            return Double.compare(player2.getPlayerStrength(), player1.getPlayerStrength());
        });
        if (playersCount >= playersToBeSorted.size()){
            return players;
        }
        return (ArrayList<IPlayer>) players.subList(0, playersCount);
    }

    private void adjustAiTeamRoaster(ITeam aiTeam){
        int skatersCount = aiTeam.getSkatersCount();
        int goaliesCount = aiTeam.getGoaliesCount();

        if(skatersCount > SKATERS_COUNT){
            dropWeakestPlayersToFreeAgentList(league, aiTeam, SKATERS, skatersCount - SKATERS_COUNT);
        }
        else if(skatersCount < SKATERS_COUNT){
            hireStrongestPlayersFromFreeAgentList(league, aiTeam, SKATERS, SKATERS_COUNT - skatersCount);
        }

        if(goaliesCount > GOALIES_COUNT){
            dropWeakestPlayersToFreeAgentList(league, aiTeam, GOALIES, goaliesCount - GOALIES_COUNT);
        }
        else if(goaliesCount < GOALIES_COUNT){
            hireStrongestPlayersFromFreeAgentList(league, aiTeam, GOALIES, GOALIES_COUNT - goaliesCount);
        }
    }

    public void dropWeakestPlayersToFreeAgentList(ILeague league, ITeam team, String playerPosition, int count) {
        ArrayList<IPlayer> players = getPlayersWithPosition(team.getPlayers(), playerPosition);
        ArrayList<IPlayer> weakestPlayers = sortPlayersOnStrength(players, count, true);
        for (IPlayer player: weakestPlayers){
            PlayerToFreeAgent playerToFreeAgent = new PlayerToFreeAgent(player);
            league.addFreeAgent(playerToFreeAgent.getFreeAgent());
        }
        for (IPlayer player: weakestPlayers){
            team.removePlayer(player);
        }
    }

    public void hireStrongestPlayersFromFreeAgentList(ILeague league, ITeam team, String freeAgentPosition, int count) {
        ArrayList<IFreeAgent> freeAgents = getFreeAgentsWithPosition(league.getFreeAgents(), freeAgentPosition);
        ArrayList<IFreeAgent> strongestFreeAgents = sortFreeAgentsOnStrength(freeAgents, count, false);
        for (IFreeAgent freeAgent: strongestFreeAgents){
            FreeAgentToPlayer freeAgentToPlayer = new FreeAgentToPlayer(freeAgent);
            team.addPlayer(freeAgentToPlayer.getPlayer());
        }
        for (IFreeAgent freeAgent: strongestFreeAgents){
            league.removeFreeAgent(freeAgent);
        }

    }

    public ArrayList<IFreeAgent> sortFreeAgentsOnStrength(ArrayList<IFreeAgent> freeAgentsToBeSorted, int freeAgentsCount, final boolean ascending) {
        if (checkIfFreeAgentsAreEmptyOrNull(freeAgentsToBeSorted)){
            return null;
        }
        if (freeAgentsCount <= 0){
            return null;
        }
        ArrayList<IFreeAgent> freeAgents = new ArrayList<>(freeAgentsToBeSorted);
        freeAgents.sort((freeAgent1, freeAgent2) -> {
            if (ascending) {
                return Double.compare(freeAgent1.getFreeAgentStrength(), freeAgent2.getFreeAgentStrength());
            }
            return Double.compare(freeAgent2.getFreeAgentStrength(), freeAgent1.getFreeAgentStrength());
        });
        if (freeAgentsCount >= freeAgentsToBeSorted.size()){
            return freeAgents;
        }
        return (ArrayList<IFreeAgent>) freeAgents.subList(0, freeAgentsCount);
    }

    public ArrayList<IFreeAgent> getFreeAgentsWithPosition(ArrayList<IFreeAgent> freeAgentsWithPosition, String position) {
        if (checkIfFreeAgentsAreEmptyOrNull(freeAgentsWithPosition) || position == null){
            return null;
        }
        ArrayList<IFreeAgent> freeAgents = new ArrayList<>();
        for (IFreeAgent freeAgent: freeAgentsWithPosition){
            if (freeAgent.getFreeAgentPosition().equals(position)){
                freeAgents.add(freeAgent);
            }
        }
        return freeAgents;
    }

    private void adjustUserTeamRoaster(ITeam userTeam){
        int skatersCount = userTeam.getSkatersCount();
        int goaliesCount = userTeam.getGoaliesCount();

        if(skatersCount > SKATERS_COUNT){
            dropPlayersFromUserTeam(userTeam, SKATERS, skatersCount - SKATERS_COUNT);
        }
        else if(skatersCount < SKATERS_COUNT){
            hirePlayersForUserTeam(userTeam, SKATERS, SKATERS_COUNT - skatersCount);
        }
        if(goaliesCount > GOALIES_COUNT){
            dropPlayersFromUserTeam(userTeam, GOALIES, goaliesCount - GOALIES_COUNT);
        }
        else if(goaliesCount < GOALIES_COUNT){
            hirePlayersForUserTeam(userTeam, GOALIES, GOALIES_COUNT - goaliesCount);
        }
    }

    private void dropPlayersFromUserTeam (ITeam team, String playerPosition, int count) {
        ArrayList<IPlayer> players = getPlayersWithPosition(team.getPlayers(), playerPosition);

        IDisplayRoaster displayRoaster = new DisplayRoaster();
        displayRoaster.displayPlayersToBeDropped(players, count);
        ArrayList<Integer> playerIndexes = new ArrayList<>();

        for (int i=0; i<count; i++){
            while (true){
                int index = displayRoaster.inputPlayerIndexToDrop();
                if (index >=0 && index <players.size()){
                    playerIndexes.add(index);
                    break;
                }
                else {
                    displayRoaster.displayMessageToUser("Id not found");
                }
            }
        }

        for (int j:playerIndexes){
            PlayerToFreeAgent playerToFreeAgent = new PlayerToFreeAgent(players.get(j));
            league.addFreeAgent(playerToFreeAgent.getFreeAgent());
        }

        for (int k:playerIndexes){
            team.removePlayer(players.get(k));
        }
    }

    private void hirePlayersForUserTeam(ITeam team, String freeAgentPosition, int count) {
        ArrayList<IFreeAgent> freeAgents = getFreeAgentsWithPosition(league.getFreeAgents(), freeAgentPosition);
        IDisplayRoaster displayRoaster = new DisplayRoaster();
        displayRoaster.displayFreeAgentsToBeHired(freeAgents, count);
        ArrayList<Integer> freeAgentsIndexes = new ArrayList<>();

        for (int i=0; i<count; i++){
            while (true){
                int index = displayRoaster.inputFreeAgentIndexToHire();
                if (index >=0 && index <freeAgents.size()){
                    freeAgentsIndexes.add(index);
                    break;
                }
                else {
                    displayRoaster.displayMessageToUser("Id not found");
                }
            }
        }

        for (int j:freeAgentsIndexes){
            FreeAgentToPlayer freeAgentToPlayer = new FreeAgentToPlayer(freeAgents.get(j));
            team.addPlayer(freeAgentToPlayer.getPlayer());
        }

        for (int k:freeAgentsIndexes){
            league.removeFreeAgent(freeAgents.get(k));
        }
    }
}
