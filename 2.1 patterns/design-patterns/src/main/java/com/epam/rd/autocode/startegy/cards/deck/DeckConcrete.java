package com.epam.rd.autocode.startegy.cards.deck;

import com.epam.rd.autocode.startegy.cards.card.Card;
import com.epam.rd.autocode.startegy.cards.card.CardConcrete;

import java.util.LinkedList;
import java.util.List;

public class DeckConcrete implements Deck {

    private final LinkedList<Card> restCards;

    public DeckConcrete(final int amount) {

        restCards = new LinkedList<>();
        for (int i = 1; i <= amount; i++) {
            restCards.push(new CardConcrete(Integer.toString(i)));
        }
    }

    @Override
    public Card dealCard() {
        return restCards.size() == 0 ? null : restCards.pop();
    }

    @Override
    public List<Card> restCards() {
        return restCards;
    }

    @Override
    public int size() {
        return restCards.size();
    }
}
