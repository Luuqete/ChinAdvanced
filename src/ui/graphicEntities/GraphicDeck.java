package ui.graphicEntities;

import javax.swing.JLabel;

import containers.LogicDeckContainer;

public class GraphicDeck extends JLabel implements GraphicDeckContainer {
    private LogicDeckContainer logicDeckContainer;

    private GraphicCard card;

    private boolean isVisible;

    public GraphicDeck(LogicDeckContainer logicDeckContainer) {
        super();
        this.isVisible = false; // Default visibility
        this.logicDeckContainer = logicDeckContainer;
        card = new GraphicCard(logicDeckContainer.showTopCard(), false,!isVisible);
        card.setHandPosition(getLocation());
    }

    @Override
    public void update(LogicDeckContainer logicDeckContainer) {
        this.logicDeckContainer = logicDeckContainer;
        getParent().remove(card);
        card = new GraphicCard(logicDeckContainer.showTopCard(), false,!isVisible);
    }

    @Override
    public void hide() {
        this.isVisible = false;
        card.setFaceDown(!isVisible);
    }

    @Override
    public void show() {
        this.isVisible = true;
        card.setFaceDown(!isVisible);
    }

    @Override
    public boolean isAnyChange(LogicDeckContainer logicDeckContainer) {
        return !this.logicDeckContainer.showTopCard().equals(logicDeckContainer.showTopCard());
    }

    @Override
    public boolean isMyAssociatedLogicContainer(LogicDeckContainer logicDeckContainer) {
        return this.logicDeckContainer.equals(logicDeckContainer);
    }

    //@Override
    public void setUp() {
        getParent().add(card);
        card.show();
    }

    @Override
    public LogicDeckContainer getLogicDeckContainer() {
        return this.logicDeckContainer;
    }

    
}
