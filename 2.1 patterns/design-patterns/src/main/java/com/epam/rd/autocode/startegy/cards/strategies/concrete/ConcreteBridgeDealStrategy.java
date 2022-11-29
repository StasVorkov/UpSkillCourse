package com.epam.rd.autocode.startegy.cards.strategies.concrete;

import com.epam.rd.autocode.startegy.cards.strategies.various.BridgeStrategy;

public class ConcreteBridgeDealStrategy extends Strategy {

    public ConcreteBridgeDealStrategy() {
        strategy = new BridgeStrategy();
    }
}
