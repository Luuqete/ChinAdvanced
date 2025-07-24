package game;

import card.Card;
import containers.LogicDeckContainer;
import game.comunicationObjects.*;
import players.PlayerEvents;
import java.awt.Point;
import java.util.Arrays;
import java.util.List;


public class GameController {
    private GameFlow gameFlow;
    private PlayerEvents[] players;

    private boolean[] playersReady; 

    public GameController(GameFlow gameFlow, PlayerEvents[] players) {
        this.gameFlow = gameFlow;
        this.players = players;
        initPlayersReady();
    }

    private void resetPlayerReadyFlags() {
         Arrays.fill(playersReady, false);
    }

    private void initPlayersReady() {
        playersReady = new boolean[players.length];
        resetPlayerReadyFlags();
    }


    public void playerChin(int numPlayer){
        List<GameEvent> events = gameFlow.handleChin(numPlayer);
        for (GameEvent event : events) {
            handleEvent(event);
        }
    }

    public boolean canPlayCard(Card card, LogicDeckContainer logicDeckContainer) {
        return gameFlow.canPlayCard(card, logicDeckContainer);
    }

    public void playerPlayCard(Card card, LogicDeckContainer logicDeckContainer) {
        GameEvent event = gameFlow.playCard(card, logicDeckContainer);
        handleEvent(event);
        List<GameEvent> events = gameFlow.generalCheck();
        for (GameEvent e : events) {
            handleEvent(e); 
        }   
    }

    public void playerReadyToStart(int playerIndex) {
        playersReady[playerIndex] = true;
        if (everyPlayerReady()) {
            GameEvent startEvent = gameFlow.startGame();
            handleEvent(startEvent);
            resetPlayerReadyFlags();
        }
    }
    

    public void moveCard(Card card, Point newLocation) {
        //Isn´t the Swing setLocation method, it is the game logic one
        card.setLocation(newLocation);
        for (PlayerEvents player : players) {
            player.onCardMoved(card);
        }

    }

    public void playerResume(int playerIndex) {
        if (playerIndex >= 0 && playerIndex < playersReady.length) {
            playersReady[playerIndex] = true;
        }

        if(everyPlayerReady()){
            GameEvent resumeEvent = gameFlow.resumeGame();
            handleEvent(resumeEvent);
            resetPlayerReadyFlags();
        }
    }

    public GameStateInfo getGameStateInfo() {
        return gameFlow.getGameStateInfo();
    }

    private boolean everyPlayerReady() {

        boolean toRet = true;

        for (boolean ready : playersReady) {
            if (!ready) {
                toRet = false;
                break;
            }
        }

        return toRet;
    }

    public void handleEvent(GameEvent event){
        switch (event) {
            case GameEvent.PauseEvent pe -> onPause();
            case GameEvent.ResumeEvent re -> onResume();
            case GameEvent.ChinEvent ce -> onChin(ce.gameStateInfo());
            case GameEvent.WinEvent we -> onWin(we.winnerPlayerNumber());
            case GameEvent.AttachmentFixedEvent afe -> onAttachment(afe.gameStateInfo());
            case GameEvent.StartGameEvent sge -> onStartGame(sge.gameStateInfo());
            case GameEvent.PlayCardEvent pce -> onPlayCard(pce.gameStateInfo());
            case GameEvent.NoEvent ne -> doNothing();
        }
    }

    private void doNothing() {
        // This method intentionally left blank
    }

    private void onChin(GameStateInfo gameStateInfo) {
        for (PlayerEvents player : players) {
            player.onChin(gameStateInfo);
        }
    }

    private void onAttachment(GameStateInfo gameStateInfo) {
        for (PlayerEvents player : players) {
            player.onAttachament(gameStateInfo);
        }
    }

    private void onPlayCard(GameStateInfo gameStateInfo) {
        for (PlayerEvents player : players) {
            player.onPlayCard(gameStateInfo);
        }
    }

    private void onPause() {
        for (PlayerEvents player : players) {
            player.onPause();
        }
    }

    private void onResume() {
        for (PlayerEvents player : players) {
            player.onResume();
        }
    }

    private void onWin(int winnerPlayerNumber) {
        int looserPlayerNumber = (winnerPlayerNumber == 0) ? 1 : 0;
        players[winnerPlayerNumber].onWin();
        players[looserPlayerNumber].onLose();
    }

    private void onStartGame(GameStateInfo gameStateInfo) {
        for (PlayerEvents player : players) {
            player.onGameInit(gameStateInfo);
        }
    }

}
