package ui.graphicEntities;

import containers.LogicHandContainer;
import ui.settings.Settings;

import javax.swing.JLabel;

import java.awt.Dimension;
import java.awt.Point;
import card.Card;

public class GraphicHand extends JLabel implements GraphicHandContainer {
    private LogicHandContainer logicHandContainer;

    private GraphicCard card;
    private boolean canMove;

    public GraphicHand(LogicHandContainer logicHandContainer,boolean canMove) {
        super();
        this.logicHandContainer = logicHandContainer;
        this.canMove = canMove;

        setLocation(Settings.translateLogicPoint(logicHandContainer.getLocation()));

        Card logicCard = logicHandContainer.getHandCard();
        //logicCard.setLocation(new Point((getX() + 5) * Settings.getScale(), (getY() + 5) * Settings.getScale()));

        card = new GraphicCard(logicCard, canMove, false);
        card.setHandPosition(Settings.translateLogicPoint(new Point(getX() + 5, getY() + 5)));

        setSize(Settings.scaleDimention(new Dimension(80,116)));
        
    }

    @Override
    public void setUp() {
        getParent().add(card);
        card.show();
    }

    @Override
    public void update(LogicHandContainer logicHandContainer) {
        this.logicHandContainer = logicHandContainer;
        updateCard(logicHandContainer.getHandCard());
    }

    private void updateCard(Card handCard) {
        getParent().remove(card);
        card = new GraphicCard(handCard, canMove, false);
        setUp();
    }

    @Override
    public boolean isAnyChange(LogicHandContainer logicHandContainer) {
        return !(this.logicHandContainer.getHandCard().equals(logicHandContainer.getHandCard()));
    }

    @Override
    public boolean isMyAssociatedLogicContainer(LogicHandContainer logicHandContainer) {
        return this.logicHandContainer.equals(logicHandContainer);
    }

    @Override
    public boolean containsCard(Card card) {
        return this.card.getCard().equals(card);
    }

    @Override
    public GraphicCard getCard() {
        return this.card;
    }

    @Override
    public void disableMovement() {
        this.canMove = false;
        card.setCanMove(canMove);
    }

    @Override
    public void enableMovement() {
        this.canMove = true;
        card.setCanMove(canMove);
    }

    

 
    
}
