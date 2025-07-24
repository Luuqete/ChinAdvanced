package ui;

import card.Card;
import collisionManager.CollisionManager;
import containers.LogicDeckContainer;
import containers.LogicHandContainer;
import ui.graphicEntities.GraphicDeck;
import ui.graphicEntities.GraphicDeckContainer;
import ui.graphicEntities.GraphicHand;
import ui.graphicEntities.GraphicHandContainer;
import ui.panels.GamePanel;
import ui.settings.Settings;

public class GraphicalEntitiesManager {
    private GraphicDeckContainer[] playDeckContainers;
    private GraphicHandContainer[] userHandContainers;
    private GraphicHandContainer[] opponentHandContainers;
    private GraphicDeckContainer[] usersDeckContainers;
    private GamePanel gamePanel;

    public GraphicalEntitiesManager(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }

    public void updateUserHand(LogicHandContainer[] userHand){
        if (userHandContainers == null) {
            initUserHand(userHand);
        } else {
            for (int i = 0; i < userHandContainers.length; i++) {
                if (userHandContainers[i].isMyAssociatedLogicContainer(userHand[i])) {
                    if (userHandContainers[i].isAnyChange(userHand[i])) {
                        userHandContainers[i].update(userHand[i]);
                        break;
                    }
                    
                }
            }
        }
        updateGraphics();
    }
    public void updateOpponentHand(LogicHandContainer[] opponentHand){
        if (opponentHandContainers == null) {
            initOpponentHand(opponentHand);
        } else {
            for (int i = 0; i < opponentHandContainers.length; i++) {
                if (opponentHandContainers[i].isMyAssociatedLogicContainer(opponentHand[i])) {
                    if (opponentHandContainers[i].isAnyChange(opponentHand[i])) {
                        opponentHandContainers[i].update(opponentHand[i]);
                        break;
                    }
                    
                }
            }
        }
        updateGraphics();
    }
    public void updatePlayDecks(LogicDeckContainer[] playDecks){
         if (playDeckContainers == null) {
            initPlayDecks(playDecks);
        } else {
            for (int i = 0; i < playDeckContainers.length; i++) {
                if (playDeckContainers[i].isMyAssociatedLogicContainer(playDecks[i])) {
                    if (playDeckContainers[i].isAnyChange(playDecks[i])) {
                        playDeckContainers[i].update(playDecks[i]);
                        break;
                    }
                    
                }
            }
        }
        updateGraphics();
    }
    public void updateUsersDecks(LogicDeckContainer[] usersDecks){
        if (usersDeckContainers == null) {
            initUsersDecks(usersDecks);
        } else {
            for (int i = 0; i < usersDeckContainers.length; i++) {
                if (usersDeckContainers[i].isMyAssociatedLogicContainer(usersDecks[i])) {
                    if (usersDeckContainers[i].isAnyChange(usersDecks[i])) {
                        usersDeckContainers[i].update(usersDecks[i]);
                        break;
                    }
                    
                }
            }
        }
        updateGraphics();
    }

    private void initUserHand(LogicHandContainer[] userHand){
        userHandContainers = new GraphicHandContainer[userHand.length];
        for (int i = 0; i < userHand.length; i++) {
            GraphicHand graphicHandContainer = new GraphicHand(userHand[i], false); //<-- until the game starts, the user hand is not movable
            gamePanel.add(graphicHandContainer);
            userHandContainers[i] = graphicHandContainer;
            userHandContainers[i].setUp();
            userHandContainers[i].setLocation(Settings.translateLogicPoint(userHand[i].getLocation()));
            
        }
    }
    private void initOpponentHand(LogicHandContainer[] opponentHand){
        opponentHandContainers = new GraphicHandContainer[opponentHand.length];
        for (int i = 0; i < opponentHand.length; i++) {
            GraphicHand graphicHandContainer = new GraphicHand(opponentHand[i], false);
            gamePanel.add(graphicHandContainer);
            opponentHandContainers[i] = graphicHandContainer;
            opponentHandContainers[i].setUp();
            opponentHandContainers[i].setLocation(Settings.translateLogicPoint(opponentHand[i].getLocation()));
            
        }
    }
    private void initPlayDecks(LogicDeckContainer[] playDecks){
        playDeckContainers = new GraphicDeckContainer[playDecks.length];
        for (int i = 0; i < playDecks.length; i++) {
            GraphicDeck graphicDeck = new GraphicDeck(playDecks[i]);
            gamePanel.add(graphicDeck);
            playDeckContainers[i] = graphicDeck;
            playDeckContainers[i].setUp();
            playDeckContainers[i].hide();
            playDeckContainers[i].setLocation(Settings.translateLogicPoint(playDecks[i].getLocation()));
        }

        CollisionManager.initialize(playDeckContainers);
    }
    private void initUsersDecks(LogicDeckContainer[] usersDecks){
        usersDeckContainers = new GraphicDeckContainer[usersDecks.length];
        for (int i = 0; i < usersDecks.length; i++) {
            GraphicDeck graphicDeck = new GraphicDeck(usersDecks[i]);
            gamePanel.add(graphicDeck);
            usersDeckContainers[i] = graphicDeck;
            usersDeckContainers[i].setUp();
            usersDeckContainers[i].hide();
            usersDeckContainers[i].setLocation(Settings.translateLogicPoint(usersDecks[i].getLocation()));
        }
    }

    public void moveCard(Card card){
        for (GraphicHandContainer handContainer : userHandContainers) {
            if (handContainer.containsCard(card)) {
                handContainer.getCard().setLocation(Settings.translateLogicPoint(card.getLocation()));
                return;
            }
        }
        for (GraphicHandContainer handContainer : opponentHandContainers) {
            if (handContainer.containsCard(card)) {
                handContainer.getCard().setLocation(Settings.translateLogicPoint(card.getLocation()));
                return;
            }
        }
    }
    public void backToHandContainer(Card card){
        for (GraphicHandContainer handContainer : userHandContainers) {
            if (handContainer.containsCard(card)) {
                handContainer.getCard().moveToHandPosition();
                return;
            }
        }
        for (GraphicHandContainer handContainer : opponentHandContainers) {
            if (handContainer.containsCard(card)) {
                handContainer.getCard().moveToHandPosition();
                return;
            }
        }

    }

    public void hidePlayCards(){
        for (GraphicDeckContainer deck : playDeckContainers) {
            deck.hide();
        }

    }
    public void showPlayCards(){
        for (GraphicDeckContainer deck : playDeckContainers) {
            deck.show();
        }

    }

    private void updateGraphics(){
        gamePanel.revalidate();
        gamePanel.repaint();
    }

    public void disableUserHandMovement() {
        for (GraphicHandContainer handContainer : userHandContainers) {
            handContainer.disableMovement();
        }
    }

    public void enableUserHandMovement() {
        for (GraphicHandContainer handContainer : userHandContainers) {
            handContainer.enableMovement();
        }
    }
}
