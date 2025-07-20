package deck;

import card.Card;

public interface GeneralDeck {
    public void shuffle();
    public Card showTopCard();
    public Card takeTopCard();
    public void pushCard(Card card);
    public boolean isEmpty();
    public int getSize();

}
