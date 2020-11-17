package com.persistencemodel;

import com.datamodel.gameplayconfig.IGameplayConfig;
import com.datamodel.gameplayconfig.ITradingConfig;
import com.datamodel.gameplayconfig.TradingConfig;
import org.json.simple.JSONObject;

public class TradingDB implements ITradingDB {

    @Override
    public void loadTrading(JSONObject tradingObject, IGameplayConfig gameplayConfig) {
        ITradingConfig trading = new TradingConfig();
        trading.setTradingId((int) (long) tradingObject.get("tradingId"));
        trading.setRandomTradeOfferChance((float) (double) tradingObject.get("randomTradeOfferChance"));
        trading.setLossPoint((int) (long) tradingObject.get("lossPoint"));
        trading.setMaxPlayersPerTrade((int) (long) tradingObject.get("maxPlayersPerTrade"));
        trading.setRandomAcceptanceChance((float) (double) tradingObject.get("randomAcceptanceChance"));
        gameplayConfig.setTrading(trading);
    }
}
