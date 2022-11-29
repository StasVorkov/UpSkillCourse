package com.epam.rd.autocode.startegy.cards.strategies.concrete;

import com.epam.rd.autocode.startegy.cards.card.Card;
import com.epam.rd.autocode.startegy.cards.deck.Deck;
import com.epam.rd.autocode.startegy.cards.strategies.various.CardDealingStrategy;

import java.util.List;
import java.util.Map;

public abstract class Strategy {

    CardDealingStrategy strategy;

    public Map<String, List<Card>> executeDealStacks(Deck deck, int players) {
        return strategy.dealStacks(deck, players);
    }
}
