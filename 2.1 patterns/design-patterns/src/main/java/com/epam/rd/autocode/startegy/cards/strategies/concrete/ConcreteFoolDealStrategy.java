package com.epam.rd.autocode.startegy.cards.strategies.concrete;

import com.epam.rd.autocode.startegy.cards.strategies.various.FoolStrategy;

public class ConcreteFoolDealStrategy extends Strategy {

    public ConcreteFoolDealStrategy() {
        strategy = new FoolStrategy();
    }
}
