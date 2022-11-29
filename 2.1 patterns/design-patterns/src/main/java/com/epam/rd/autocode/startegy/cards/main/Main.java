package com.epam.rd.autocode.startegy.cards.main;

import com.epam.rd.autocode.startegy.cards.deck.DeckConcrete;
import com.epam.rd.autocode.startegy.cards.factory.CardDealingStrategies;
import com.epam.rd.autocode.startegy.cards.strategies.concrete.*;
import com.epam.rd.autocode.startegy.cards.strategies.various.CardDealingStrategy;


public class Main {
    public static void main(String[] args) {

        CardDealingStrategy hm = CardDealingStrategies.texasHoldemCardDealingStrategy();
        System.out.println(hm.dealStacks(new DeckConcrete(52), 5));

        Strategy holdem = new ConcreteHoldemDealStrategy();
        System.out.println(holdem.executeDealStacks(new DeckConcrete(52), 4));

        Strategy classic = new ConcreteClassicPokerDealStrategy();
        System.out.println(classic.executeDealStacks(new DeckConcrete(52), 4));

        Strategy bridge = new ConcreteBridgeDealStrategy();
        System.out.println(bridge.executeDealStacks(new DeckConcrete(52), 4));

        Strategy fool = new ConcreteFoolDealStrategy();
        System.out.println(fool.executeDealStacks(new DeckConcrete(36), 5));
    }
}
