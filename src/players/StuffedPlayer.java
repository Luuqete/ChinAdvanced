package players;

import java.awt.Point;

import card.Card;
import containers.LogicDeckContainer;
import game.GameController;
import game.comunicationObjects.GameStateInfo;

public class StuffedPlayer implements PlayerEvents,PlayerActions {

    //Player empty just for development and testing

    private int numPlayer;
    private GameController gameController;

    public StuffedPlayer(int numPlayer) {
        this.numPlayer = numPlayer;
        this.gameController = null;
    }

    @Override
    public void requestResume() {
        gameController.playerResume(numPlayer);
    }

    @Override
    public void requestPlayCard(Card card, LogicDeckContainer logicDeckContainer) {
        
    }

    @Override
    public void requestChin() {
        
    }

    @Override
    public void requestMoveCard(Card card, Point newPosition) {
        
    }

    @Override
    public void onPause() {
        requestResume();
    }

    @Override
    public void onResume() {
       
    }

    @Override
    public void onChin(GameStateInfo gameStateInfo) {
 
    }

    @Override
    public void onWin() {
        
    }

    @Override
    public void onLose() {
        
    }

    @Override
    public void onGameInit(GameStateInfo gameStateInfo) {
   
     
    }

    @Override
    public void onAttachament(GameStateInfo gameStateInfo) {
        

    }

    @Override
    public void onPlayCard(GameStateInfo gameStateInfo) {
        
   
    }

    @Override
    public void onCardMoved(Card card) {
        
         
    }

    @Override
    public void setGameController(GameController gameController) {
        this.gameController = gameController;
        requestInitGame();
        // This player is not ready to play, it is just a placeholder.
        // No need to set isReady or numPlayer.
    }

    @Override
    public void requestInitGame() {
        gameController.playerReadyToStart(numPlayer);
    }

    
    
}
