package game;

import containers.DeckContainer;
import containers.HandContainer;
import containers.LogicDeckContainer;
import containers.LogicHandContainer;
import deck.Deck;
import deck.GeneralDeck;
import deck.InitialDeck;
import players.ConsolePlayer;
import players.PlayerEvents;
import players.StuffedPlayer;

public class Game {

    private GameController gameController;
    private GameFlow gameFlow;
    private GameState gameState;

    public Game(){
        this.gameState = new GameState();
        initStates();
        this.gameFlow = new GameFlow(gameState);

        PlayerEvents[] players = createPlayers();
        this.gameController = new GameController(gameFlow, players);
        
        players[0].setGameController(gameController);
        players[1].setGameController(gameController);
        
    }

    private void initStates() {
        InitialDeck initialDeck = new Deck();
        initialDeck.fill();
        initialDeck.shuffle();
        gameState.userDecks = initialDeck.getUsersDecks();

        gameState.playDecks = new GeneralDeck[]{
            new Deck(),
            new Deck()
        };

        gameState.userDecksContainers = new LogicDeckContainer[]{
            new DeckContainer(gameState.userDecks[0]),
            new DeckContainer(gameState.userDecks[1])
        };

        gameState.playDecksContainers = new LogicDeckContainer[]{
            new DeckContainer(gameState.playDecks[0]),
            new DeckContainer(gameState.playDecks[1])
        };

        gameState.p1HandContainers = new LogicHandContainer[]{
            new HandContainer(gameState.userDecksContainers[0]),
            new HandContainer(gameState.userDecksContainers[0]),
            new HandContainer(gameState.userDecksContainers[0]),
            new HandContainer(gameState.userDecksContainers[0])
        };

        gameState.p2HandContainers = new LogicHandContainer[]{
            new HandContainer(gameState.userDecksContainers[1]),
            new HandContainer(gameState.userDecksContainers[1]),
            new HandContainer(gameState.userDecksContainers[1]),
            new HandContainer(gameState.userDecksContainers[1])
        };

        gameState.temporalDeck = null;
    }

    private PlayerEvents[] createPlayers() {
        PlayerEvents[] players = new PlayerEvents[2];
        players[0] = new ConsolePlayer(0);
        players[1] = new StuffedPlayer(1);
        return players;
    }

        
}
