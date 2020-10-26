package UserInputOutput;

import g4dhl.IPlayer;

import java.util.ArrayList;

public interface IDisplayTradingOffers {
    void displayOfferToUser(ArrayList<IPlayer> offeredPlayers, ArrayList<IPlayer> consideredPlayers);
    boolean inputTradeAcceptRejectBooleanFromUser();
}
