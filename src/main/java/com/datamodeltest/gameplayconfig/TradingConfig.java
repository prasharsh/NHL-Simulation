package com.datamodeltest.gameplayconfig;

public class TradingConfig implements ITradingConfig {
    private int tradingId;
    private int lossPoint;
    private float randomTradeOfferChance;
    private int maxPlayersPerTrade;
    private float randomAcceptanceChance;

    @Override
    public int getTradingId() {
        return this.tradingId;
    }

    @Override
    public void setTradingId(int tradingId) {
        this.tradingId = tradingId;
    }

    @Override
    public int getLossPoint() {
        return this.lossPoint;
    }

    @Override
    public boolean setLossPoint(int lossPoint) {
        if (isValidLossPoint(lossPoint)) {
            this.lossPoint = lossPoint;
            return true;
        } else {
            return false;
        }
    }

    @Override
    public float getRandomTradeOfferChance() {
        return this.randomTradeOfferChance;
    }

    @Override
    public boolean setRandomTradeOfferChance(float randomTradeOfferChance) {
        if (isValidTradeOfferChance(randomTradeOfferChance)) {
            this.randomTradeOfferChance = randomTradeOfferChance;
            return true;
        } else {
            return false;
        }
    }

    @Override
    public int getMaxPlayersPerTrade() {
        return this.maxPlayersPerTrade;
    }

    @Override
    public boolean setMaxPlayersPerTrade(int maxPlayersPerTrade) {
        if (isValidMaxplayerEntry(maxPlayersPerTrade)) {
            this.maxPlayersPerTrade = maxPlayersPerTrade;
            return true;
        } else {
            return false;
        }
    }

    @Override
    public float getRandomAcceptanceChance() {
        return this.randomAcceptanceChance;
    }

    @Override
    public boolean setRandomAcceptanceChance(float randomAcceptanceChance) {
        if (isValidAcceptanceChance(randomAcceptanceChance)) {
            this.randomAcceptanceChance = randomAcceptanceChance;
            return true;
        } else {
            return false;
        }
    }

    private boolean isValidLossPoint(int lossPoint) {
        return lossPoint >= 0;
    }

    private boolean isValidTradeOfferChance(float TradeOfferChance) {
        return TradeOfferChance >= 0 && TradeOfferChance <= 1;
    }

    private boolean isValidMaxplayerEntry(int maxPlayerEntry) {
        return maxPlayerEntry >= 0;
    }

    private boolean isValidAcceptanceChance(float randomAcceptanceChance) {
        return randomAcceptanceChance >= 0 && randomAcceptanceChance <= 1;
    }
}
