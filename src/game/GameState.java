package game;

import containers.LogicDeckContainer;
import containers.LogicHandContainer;
import deck.GeneralDeck;
import game.comunicationObjects.GameStateInfo;

public class GameState {
    public boolean isPaused;
    public GeneralDeck temporalDeck;
    public GeneralDeck[] userDecks;
    public GeneralDeck[] playDecks; 
    public LogicDeckContainer[] userDecksContainers;
    public LogicDeckContainer[] playDecksContainers;
    public LogicHandContainer[] p1HandContainers;
    public LogicHandContainer[] p2HandContainers;

    public GameStateInfo getGameStateInfo() {
        return new GameStateInfo(userDecksContainers, playDecksContainers, p1HandContainers, p2HandContainers);
    }
    
}
