package trading;

import java.util.ArrayList;

import g4dhl.IFreeAgent;
import g4dhl.ILeague;
import g4dhl.IPlayer;
import g4dhl.ITeam;

public interface ITrading {
    void startTrading(g4dhl.ITrading trading , ILeague league, ArrayList<ITeam> teams);
    void acceptTradeOffer(ITeam offeringTeam, ArrayList<IPlayer> offeringTeamPlayers, ITeam opponentTeam, ArrayList<IPlayer> opponentTeamPlayers);
    double calculateTotalStrengthOfPlayers(ArrayList<IPlayer> players);
    ArrayList<IPlayer> getPlayersWithPosition(ArrayList<IPlayer> players, String position);
    ArrayList<IPlayer> sortPlayersOnStrength(ArrayList<IPlayer> playersToBeSorted, int playersCount, final boolean ascending);
    void dropWeakestPlayersToFreeAgentList(ILeague league, ITeam team, String playerPosition, int count);
    void hireStrongestPlayersFromFreeAgentList(ILeague league, ITeam team, String freeAgentPosition, int count);
    ArrayList<IFreeAgent> sortFreeAgentsOnStrength(ArrayList<IFreeAgent> freeAgentsToBeSorted, int freeAgentsCount, final boolean ascending);
    ArrayList<IFreeAgent> getFreeAgentsWithPosition(ArrayList<IFreeAgent> freeAgents, String position);
}
