package com.epam.rd.autocode.startegy.cards.strategies.concrete;

import com.epam.rd.autocode.startegy.cards.strategies.various.HoldemStrategy;

public class ConcreteHoldemDealStrategy extends Strategy {

    public ConcreteHoldemDealStrategy() {
        strategy = new HoldemStrategy();
    }
}
