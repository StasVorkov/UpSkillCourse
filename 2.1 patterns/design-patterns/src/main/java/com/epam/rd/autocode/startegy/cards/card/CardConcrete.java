package com.epam.rd.autocode.startegy.cards.card;

public class CardConcrete implements Card {

    private final String nameCard;

    public CardConcrete(final String nameCard) {
        this.nameCard = nameCard;
    }

    @Override
    public String name() {
        return nameCard;
    }

    @Override
    public String toString() {
        return nameCard;
    }
}
