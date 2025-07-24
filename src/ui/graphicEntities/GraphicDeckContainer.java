package ui.graphicEntities;

import java.awt.Point;
import java.awt.Rectangle;

import containers.LogicDeckContainer;

public interface GraphicDeckContainer {
    public void update(LogicDeckContainer logicDeckContainer);
    public void hide();
    public void show();
    public boolean isAnyChange(LogicDeckContainer logicDeckContainer);
    public boolean isMyAssociatedLogicContainer(LogicDeckContainer logicDeckContainer);
    public void setUp();
    public Rectangle getBounds();
    public LogicDeckContainer getLogicDeckContainer();
    public void setLocation(Point translateLogicPoint);
}
