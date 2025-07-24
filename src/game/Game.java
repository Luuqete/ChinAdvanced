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

    private PlayerEvents[] players;
    public Game(){
        this.gameState = new GameState();
        initStates();
        this.gameFlow = new GameFlow(gameState);

        this.players = new PlayerEvents[2];
        this.players[0] = new ConsolePlayer(0);
    }

    private void initStates() {
        
        setUpUserDecks();

        setUpPlayDecks();

        setUpUserDecksContainers();

        setUpPlayDecksContainers();

        setUpPlayersHandContainers();

    }

    private void setUpPlayersHandContainers() {
        gameState.p1HandContainers = new LogicHandContainer[]{
            new HandContainer(gameState.userDecksContainers[0], 04),
            new HandContainer(gameState.userDecksContainers[0], 05),
            new HandContainer(gameState.userDecksContainers[0], 06),
            new HandContainer(gameState.userDecksContainers[0], 07)
        };

        gameState.p2HandContainers = new LogicHandContainer[]{
            new HandContainer(gameState.userDecksContainers[1], 8),
            new HandContainer(gameState.userDecksContainers[1], 9),
            new HandContainer(gameState.userDecksContainers[1], 10),
            new HandContainer(gameState.userDecksContainers[1], 11)
        };
    }

    private void setUpPlayDecksContainers() {
        gameState.playDecksContainers = new LogicDeckContainer[]{
            new DeckContainer(gameState.playDecks[0],02),
            new DeckContainer(gameState.playDecks[1],03)
        };
    }

    private void setUpUserDecksContainers() {
        gameState.userDecksContainers = new LogicDeckContainer[]{
            new DeckContainer(gameState.userDecks[0],00),
            new DeckContainer(gameState.userDecks[1],01)
        };
    }

    private void setUpUserDecks(){
        InitialDeck initialDeck = new Deck();
        initialDeck.fill();
        initialDeck.shuffle();
        gameState.userDecks = initialDeck.getUsersDecks();
    }

    private void setUpPlayDecks(){
        gameState.playDecks = new GeneralDeck[]{
            new Deck(),
            new Deck()
        };
    }

    public void startGame() {
        gameController = new GameController(gameFlow, players);
        players[0].setGameController(gameController);
        players[1].setGameController(gameController);
    }

    public void addEmptyPlayer(){
        players[1] = new StuffedPlayer(1);
    }

    public void addConsolePlayer(){
        players[1] = new ConsolePlayer(1);
    }

        
}
