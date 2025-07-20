package deck;

import java.util.Collections;
import java.util.LinkedList;

import card.Card;

public class Deck implements GeneralDeck, InitialDeck {

    private LinkedList<Card> cards;

    public Deck() {
        cards = new LinkedList<>();
    }

    private Deck(LinkedList<Card> cards) {
        this.cards = cards;
    }

    @Override
    public void shuffle() {
        Collections.shuffle(this.cards);
    }

    @Override
    public Card showTopCard() {
        // Logic to show the top card
        return this.cards.peek(); // Show the top card without removing it
    }

    @Override
    public Card takeTopCard() {
        // Logic to take the top card
        return this.cards.pop(); // Remove and return the top card
    }

    @Override
    public void pushCard(Card card) {
        // Logic to push a card onto the deck
        this.cards.push(card);
    }

    @Override
    public boolean isEmpty() {
        // Logic to check if the deck is empty
        return cards.isEmpty(); // Placeholder return
    }

    @Override
    public int getSize() {
        // Logic to get the size of the deck
        return this.cards.size(); // Placeholder return
    }

    @Override
    public GeneralDeck[] getUsersDecks() {
        GeneralDeck[] toRet = new Deck[2];

        int mitad = this.cards.size() / 2;

        toRet[0] = new Deck(new LinkedList<>(this.cards.subList(0, mitad)));
        toRet[1] = new Deck(new LinkedList<>(this.cards.subList(mitad, this.cards.size())));

        return toRet;
    }

    @Override
    public void fill() {
        int[] palos = { 0, 1, 2, 3 };
        int[] nums = { 1, 2, 3, 4, 5, 6, 7, 10, 11, 12 };

        for (int palo : palos) {
            for (int num : nums) {
                this.cards.add(new Card(num, palo));
            }
        }
    }

}
