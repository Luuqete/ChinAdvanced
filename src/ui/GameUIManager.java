package ui;

import card.Card;
import containers.LogicDeckContainer;
import containers.LogicHandContainer;
import ui.panels.GamePanel;

public class GameUIManager implements GameUI{

    private GraphicalEntitiesManager graphicalEntitiesManager;
    private GamePanel gamePanel;

    public GameUIManager(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
        this.graphicalEntitiesManager = new GraphicalEntitiesManager(gamePanel);
    }

    @Override
    public void updateUserHand(LogicHandContainer[] userHand) {
        graphicalEntitiesManager.updateUserHand(userHand);
    }
    

    @Override
    public void updateOpponentHand(LogicHandContainer[] opponentHand) {
        graphicalEntitiesManager.updateOpponentHand(opponentHand);
    }

    @Override
    public void updatePlayDecks(LogicDeckContainer[] playDecks) {
        graphicalEntitiesManager.updatePlayDecks(playDecks);
      
    }

    @Override
    public void updateUserDecks(LogicDeckContainer[] userDecks) {
        graphicalEntitiesManager.updateUsersDecks(userDecks);
    }

    @Override
    public void moveCard(Card card) {
        graphicalEntitiesManager.moveCard(card);
    }

    @Override
    public void backToHandContainer(Card card) {
        graphicalEntitiesManager.backToHandContainer(card);
    }

    @Override
    public void hidePlayCards() {
        graphicalEntitiesManager.hidePlayCards();
    }

    @Override
    public void showPlayCards() {
        graphicalEntitiesManager.showPlayCards();
    }

    @Override
    public void onPause() {
        hidePlayCards();
        graphicalEntitiesManager.disableUserHandMovement();
    }

    @Override
    public void onResume() {
        showPlayCards();
        graphicalEntitiesManager.enableUserHandMovement();
    }

    @Override
    public void onWin() {
        gamePanel.showMessagePlayerWin();
        graphicalEntitiesManager.disableUserHandMovement();
    }

    @Override
    public void onLose() {
        gamePanel.showMessagePlayerLose();
        graphicalEntitiesManager.disableUserHandMovement();
    }

    @Override
    public void onInit() {
        graphicalEntitiesManager.enableUserHandMovement();
    }

    @Override
    public void onChin() {
        gamePanel.showMessageChin();
        graphicalEntitiesManager.hidePlayCards();
    }

    @Override
    public void onAttachment() {
        gamePanel.showMessageAttachamentFixed();
        graphicalEntitiesManager.hidePlayCards();
    }
    
}
