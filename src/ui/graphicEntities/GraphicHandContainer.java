package ui.graphicEntities;

import java.awt.Point;

import card.Card;
import containers.LogicHandContainer;

public interface GraphicHandContainer {
    public void update(LogicHandContainer logicHandContainer);
    public boolean isAnyChange(LogicHandContainer logicHandContainer);
    public boolean isMyAssociatedLogicContainer(LogicHandContainer logicHandContainer);
    public boolean containsCard(Card card);
    public void setUp();
    public GraphicCard getCard();
    public void setLocation(Point location);
    public void disableMovement();
    public void enableMovement();
}

