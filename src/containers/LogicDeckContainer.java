package containers;

import java.awt.Point;
import card.Card;

public interface LogicDeckContainer {
    public boolean canPlay(Card card);
    public void play(Card card);
    public Card showTopCard();
    public Card takeTopCard();
    public Point getLocation();
    public boolean isDeckEmpty();
    public int getDeckSize();
}
