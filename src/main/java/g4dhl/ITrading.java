package g4dhl;

public interface ITrading {
    int getTradingId();
    void setTradingId(int tradingId);

    int getLossPoint();
    boolean setLossPoint(int lossPoint);

    float getRandomTradeOfferChance();
    boolean setRandomTradeOfferChance(float randomTradeOfferChance);

    int getMaxPlayersPerTrade();
    boolean setMaxPlayersPerTrade(int maxPlayersPerTrade);

    float getRandomAcceptanceChance();
    boolean setRandomAcceptanceChance(float randomAcceptanceChance);
}
