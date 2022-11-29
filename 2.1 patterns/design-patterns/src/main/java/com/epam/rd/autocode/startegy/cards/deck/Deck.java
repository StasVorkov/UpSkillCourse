package com.epam.rd.autocode.startegy.cards.deck;

import com.epam.rd.autocode.startegy.cards.card.Card;

import java.util.List;

public interface Deck {

    Card dealCard();

    List<Card> restCards();

    int size();

}
