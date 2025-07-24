package containers;

import java.awt.Point;

import card.Card;
import deck.GeneralDeck;
import entity.Entity;

public class DeckContainer extends Entity implements LogicDeckContainer {
    private GeneralDeck deck;
    private final int id;

    public DeckContainer(GeneralDeck deck, int id) {
        super();
        setLocation(new Point(0,0));
        this.deck = deck;
        this.id = id;
    }

    

    @Override
    public Card showTopCard() {
        return deck.showTopCard(); // Return the top card without removing it
    }

    @Override
    public Card takeTopCard() {
        return deck.takeTopCard();

    }

    @Override
    public boolean isDeckEmpty() {
        return deck.isEmpty();
    }

    @Override
    public boolean canPlay(Card card) {
        boolean toRet = false;
        if (deck.isEmpty()) {
            toRet = true;
        } else {
            toRet = isNext(card, deck.showTopCard());
        }

        return toRet;
    }

    private boolean isNext(Card card, Card top) {
    int numCard = card.getNum();
    int numTopCard = top.getNum();
    int diff = Math.abs(numCard - numTopCard);

    boolean isConsecutive = (diff == 1 || diff == 11);
    boolean isSpecialCase = (numCard == 7 && numTopCard == 10) || (numCard == 10 && numTopCard == 7);

    return isConsecutive || isSpecialCase;
}


    @Override
    public void play(Card card) {
        deck.pushCard(card);
    }

    @Override
    public int getDeckSize() {
        return deck.getSize();
    }



    @Override
    public int getId() {
       return id;
    }

    @Override 
    public boolean equals(Object obj) {
        boolean toRet = false;
        if (this == obj) toRet = true;
        if (!(obj instanceof DeckContainer)) toRet = false;
        DeckContainer other = (DeckContainer) obj;
        toRet = this.id == other.getId();
        return toRet;
    }

}
