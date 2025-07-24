package players;

import java.awt.Point;

import card.Card;
import containers.LogicDeckContainer;
import game.GameController;
import game.comunicationObjects.GameStateInfo;
import ui.GameUI;

public class HumanPlayer implements PlayerEvents, GraphicPlayer {

    private GameController gameController;
    private int numPlayer;
    private GameUI ui;

    public HumanPlayer(int numPlayer) {
        this.numPlayer = numPlayer;
        this.ui = null;
        this.gameController = null;
    }

    @Override
    public void requestResume() {
        gameController.playerResume(numPlayer);
    }

    @Override
    public void requestPlayCard(Card card, LogicDeckContainer logicDeckContainer) {
        if (gameController.canPlayCard(card, logicDeckContainer)) {
            gameController.playerPlayCard(card, logicDeckContainer);
        } else {
            ui.backToHandContainer(card);
        }
    }

    @Override
    public void requestChin() {
        gameController.playerChin(numPlayer);
    }

    @Override
    public void requestMoveCard(Card card, Point newPosition) {
        gameController.moveCard(card, newPosition);
    }

    @Override
    public void requestInitGame() {
        gameController.playerReadyToStart(numPlayer);

    }

    @Override
    public void onPause() {
        ui.onPause();
    }

    @Override
    public void onResume() {
        ui.onResume();
    }

    @Override
    public void onChin(GameStateInfo gameStateInfo) {
        ui.updatePlayDecks(gameStateInfo.playDecksContainers());

    }

    @Override
    public void onWin() {
        ui.onWin();
    }

    @Override
    public void onLose() {
        ui.onLose();
    }

    @Override
    public void onGameInit(GameStateInfo gameStateInfo) {
        ui.onInit();
    }

    @Override
    public void onAttachament(GameStateInfo gameStateInfo) {
        ui.updatePlayDecks(gameStateInfo.playDecksContainers());
    }

    @Override
    public void onPlayCard(GameStateInfo gameStateInfo) {
        ui.updateOpponentHand(numPlayer == 0 ? gameStateInfo.p2HandContainers() : gameStateInfo.p1HandContainers());
        ui.updateUserHand(numPlayer == 0 ? gameStateInfo.p1HandContainers() : gameStateInfo.p2HandContainers());
    }

    @Override
    public void onCardMoved(Card card) {
        ui.moveCard(card);
    }

    @Override
    public void setGameController(GameController gameController) {
        this.gameController = gameController;
    }

    @Override
    public void setUI(GameUI ui) {
        this.ui = ui;
        preChargeGameData();

    }

    private void preChargeGameData() {
        GameStateInfo gameStateInfo = gameController.getGameStateInfo();
        this.ui.updateUserHand(
                this.numPlayer == 0 ? gameStateInfo.p1HandContainers() : gameStateInfo.p2HandContainers());
        this.ui.updateOpponentHand(
                this.numPlayer == 0 ? gameStateInfo.p2HandContainers() : gameStateInfo.p1HandContainers());
        this.ui.updatePlayDecks(gameStateInfo.playDecksContainers());
        this.ui.updateUserDecks(gameStateInfo.userDecksContainers());
    }
}
