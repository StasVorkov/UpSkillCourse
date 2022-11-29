package com.epam.rd.autocode.startegy.cards.strategies.various;

import com.epam.rd.autocode.startegy.cards.card.Card;
import com.epam.rd.autocode.startegy.cards.deck.Deck;

import java.util.*;

public class BridgeStrategy implements CardDealingStrategy {

    private static final int PLAYER_AMOUNT_CARDS = 13;
    private static final String PLAYER = "Player ";

    @Override
    public Map<String, List<Card>> dealStacks(Deck deck, int players) {
        Map<String, List<Card>> allStacks = new LinkedHashMap<>();
        createStacks(allStacks, players);

        for (int i = 0; i < PLAYER_AMOUNT_CARDS; i++) {
            for (int j = 0; j < players; j++) {
                allStacks.get(PLAYER + (j + 1)).add(deck.dealCard());
            }
        }
        return allStacks;
    }

    private void createStacks(final Map<String, List<Card>> stacks, final int players) {
        for (int i = 1; i <= players; i++) {
            stacks.put(PLAYER + (i), new ArrayList<>());
        }
    }
}