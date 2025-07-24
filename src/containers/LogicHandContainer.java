package containers;

import java.awt.Point;

import card.Card;

public interface LogicHandContainer {
    public void askCard();
    public boolean isActive();
    public void enable();
    public void disable();
    public Card getHandCard();
    public Point getLocation();
    public int getId();
    public boolean equals(Object obj);
}
