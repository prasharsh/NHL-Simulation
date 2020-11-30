package com.persistencemodel;

import org.json.simple.JSONObject;

import com.datamodel.gameplayconfig.IGamePlayConfig;
import com.datamodel.gameplayconfig.ITradingConfig;
import com.datamodel.gameplayconfig.TradingConfig;

public class TradingDB implements ITradingDB {

    @Override
    public void loadTrading(JSONObject tradingObject, IGamePlayConfig gameplayConfig) {
        ITradingConfig trading = new TradingConfig();
        trading.setTradingId((int) (long) tradingObject.get("tradingId"));
        trading.setRandomTradeOfferChance((float) (double) tradingObject.get("randomTradeOfferChance"));
        trading.setLossPoint((int) (long) tradingObject.get("lossPoint"));
        trading.setMaxPlayersPerTrade((int) (long) tradingObject.get("maxPlayersPerTrade"));
        trading.setRandomAcceptanceChance((float) (double) tradingObject.get("randomAcceptanceChance"));
        gameplayConfig.setTrading(trading);
    }
}
