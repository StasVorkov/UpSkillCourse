package com.epam.rd.autocode.startegy.cards.factory;

import com.epam.rd.autocode.startegy.cards.strategies.various.*;

public class CardDealingStrategies {

    public static CardDealingStrategy texasHoldemCardDealingStrategy() {
        return new HoldemStrategy();
    }

    public static CardDealingStrategy classicPokerCardDealingStrategy() {
        return new ClassicPokerStrategy();
    }

    public static CardDealingStrategy bridgeCardDealingStrategy() {
        return new BridgeStrategy();
    }

    public static CardDealingStrategy foolCardDealingStrategy() {
        return new FoolStrategy();
    }

}
