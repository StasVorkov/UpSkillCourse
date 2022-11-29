package com.epam.rd.autocode.startegy.cards.strategies.various;

import com.epam.rd.autocode.startegy.cards.card.Card;
import com.epam.rd.autocode.startegy.cards.deck.Deck;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class FoolStrategy implements CardDealingStrategy {

    private static final int PLAYER_AMOUNT_CARDS = 6;
    private static final String PLAYER = "Player ";
    private static final String TRUMP_CARD = "Trump card";
    private static final String REMAINING = "Remaining";


    @Override
    public Map<String, List<Card>> dealStacks(Deck deck, int players) {
        Map<String, List<Card>> allStacks = new LinkedHashMap<>();
        createStacks(allStacks, players);

        for (int i = 0; i < PLAYER_AMOUNT_CARDS; i++) {
            for (int j = 0; j < players; j++) {
                allStacks.get(PLAYER + (j + 1)).add(deck.dealCard());
            }
        }

        allStacks.get(TRUMP_CARD).add(deck.dealCard());

        allStacks.get(REMAINING).addAll(deck.restCards());
        return allStacks;
    }

    private void createStacks(final Map<String, List<Card>> stacks, final int players) {
        for (int i = 1; i <= players; i++) {
            stacks.put(PLAYER + (i), new ArrayList<>());
        }
        stacks.put(TRUMP_CARD, new ArrayList<>());
        stacks.put(REMAINING, new ArrayList<>());
    }
}