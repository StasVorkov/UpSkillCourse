package com.epam.rd.autocode.startegy.cards.strategies.concrete;

import com.epam.rd.autocode.startegy.cards.strategies.various.ClassicPokerStrategy;


public class ConcreteClassicPokerDealStrategy extends Strategy {

    public ConcreteClassicPokerDealStrategy() {
        strategy = new ClassicPokerStrategy();
    }
}
