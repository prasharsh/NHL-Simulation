package UserInputOutput;

import java.util.ArrayList;

import g4dhl.IPlayer;

public interface IDisplayTradingOffers {
    void displayOfferToUser(ArrayList<IPlayer> offeredPlayers, ArrayList<IPlayer> consideredPlayers);
    boolean inputTradeAcceptRejectBooleanFromUser();
}
