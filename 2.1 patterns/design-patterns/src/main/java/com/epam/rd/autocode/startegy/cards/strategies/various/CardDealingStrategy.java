package com.epam.rd.autocode.startegy.cards.strategies.various;

import com.epam.rd.autocode.startegy.cards.card.Card;
import com.epam.rd.autocode.startegy.cards.deck.Deck;

import java.util.List;
import java.util.Map;

public interface CardDealingStrategy {

    Map<String, List<Card>> dealStacks(Deck deck, int players);
}
