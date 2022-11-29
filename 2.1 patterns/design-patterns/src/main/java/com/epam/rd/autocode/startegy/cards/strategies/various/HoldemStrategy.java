package com.epam.rd.autocode.startegy.cards.strategies.various;

import com.epam.rd.autocode.startegy.cards.card.Card;
import com.epam.rd.autocode.startegy.cards.deck.Deck;

import java.util.*;

public class HoldemStrategy implements CardDealingStrategy {

    private static final int COMMUNITY_AMOUNT_CARDS = 5;
    private static final int PLAYER_AMOUNT_CARDS = 2;
    private static final String PLAYER = "Player ";
    private static final String COMMUNITY = "Community";
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

        for (int i = 0; i < COMMUNITY_AMOUNT_CARDS; i++) {
            allStacks.get(COMMUNITY).add(deck.dealCard());
        }

        allStacks.get(REMAINING).addAll(deck.restCards());
        return allStacks;
    }

    private void createStacks(final Map<String, List<Card>> stacks, final int players) {
        for (int i = 1; i <= players; i++) {
            stacks.put(PLAYER + (i), new ArrayList<>());
        }
        stacks.put(COMMUNITY, new ArrayList<>());
        stacks.put(REMAINING, new ArrayList<>());
    }
}
