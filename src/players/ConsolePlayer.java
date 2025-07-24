package players;

import java.awt.Point;
import java.util.Scanner;

import card.Card;
import containers.LogicDeckContainer;
import containers.LogicHandContainer;
import game.GameController;
import game.comunicationObjects.GameStateInfo;

public class ConsolePlayer implements PlayerEvents, PlayerActions {

    private int numPlayer;
    private boolean isReady;
    private boolean isPaused;

    private LogicHandContainer[] myHand;
    private LogicHandContainer[] opponentHand;
    private LogicDeckContainer[] playDecks;
    private LogicDeckContainer[] userDecks;

    private GameController gameController;

    public ConsolePlayer(int numPlayer) {
        this.gameController = null;
        this.numPlayer = numPlayer;
        this.isReady = false;
        this.isPaused = false;
    }

    @Override
    public void requestResume() {
        gameController.playerResume(numPlayer);
    }

    @Override
    public void requestPlayCard(Card card, LogicDeckContainer logicDeckContainer) {
        gameController.playerPlayCard(card, logicDeckContainer);
    }

    @Override
    public void requestChin() {
        gameController.playerChin(numPlayer);
    }

    @Override
    public void requestMoveCard(Card card, Point newPosition) {
        gameController.moveCard(card, newPosition);
        // In this class, this won´t be used.
    }

    @Override
    public void requestInitGame() {
        isReady = true;
        gameController.playerReadyToStart(numPlayer);
    }

    @Override
    public void onPause() {
        this.isPaused = true;
        displayGameState();
    }

    @Override
    public void onResume() {
        this.isPaused = false;
        displayGameState();
    }

    @Override
    public void onChin(GameStateInfo gameStateInfo) {
        setInformation(gameStateInfo);
        displayGameState();
    }

    @Override
    public void onWin() {
        System.out.println("Ganaste");
    }

    @Override
    public void onLose() {
        System.out.println("Perdiste");
    }

    @Override
    public void onGameInit(GameStateInfo gameStateInfo) {
        setInformation(gameStateInfo);
        System.out.println("INICIAAAAAAAAAAAA");
        displayGameState();
    }

    @Override
    public void onAttachament(GameStateInfo gameStateInfo) {
        setInformation(gameStateInfo);
        displayGameState();
    }

    @Override
    public void onPlayCard(GameStateInfo gameStateInfo) {
        setInformation(gameStateInfo);
        displayGameState();
    }

    @Override
    public void onCardMoved(Card card) {
        // In this class, this won´t be used.
    }

    @Override
    public void setGameController(GameController gameController) {
        this.gameController = gameController;

        showMenu();
    }

    private void setInformation(GameStateInfo gameStateInfo) {
        this.myHand = (numPlayer == 0) ? gameStateInfo.p1HandContainers() : gameStateInfo.p2HandContainers();
        this.opponentHand = (numPlayer == 0) ? gameStateInfo.p2HandContainers() : gameStateInfo.p1HandContainers();
        this.playDecks = gameStateInfo.playDecksContainers();
        this.userDecks = gameStateInfo.userDecksContainers();

    }

    private void displayGameState() {
        System.out.println("\n════════════════════════════════════════════════════════════");
        System.out.println("                    🔷 GAME STATE 🔷                    ");
        System.out.println("════════════════════════════════════════════════════════════");

        System.out.println(isPaused ? "⏸️ Game is paused" : "▶️ Game is running");

        // Mano del oponente
        System.out.println("👤 Opponent's Hand:");
        for (int i = 0; i < opponentHand.length; i++) {
            LogicHandContainer container = opponentHand[i];
            if (container != null && container.getHandCard() != null) {
                System.out.printf("[%d] %s  ", i, container.getHandCard());
            } else {
                System.out.printf("[%d] (empty)  ", i);
            }
        }
        System.out.println();

        // Cantidad de cartas en el mazo del oponente
        int opponentDeckIndex = (numPlayer == 0) ? 1 : 0;
        System.out.printf("📦 Opponent Deck (Player %d): %d cards\n", opponentDeckIndex,
                userDecks[opponentDeckIndex].getDeckSize());

        // Play decks
        System.out.println("\n🎴 Play Decks:");
        for (int i = 0; i < playDecks.length; i++) {
            String topCard = isPaused ? "[X]" : playDecks[i].showTopCard().toString();
            System.out.printf("  [%d] %s\n", i, topCard);
        }

        // Cantidad de cartas en mi mazo
        int myDeckIndex = numPlayer;
        System.out.printf("\n📦 Your Deck (Player %d): %d cards\n", myDeckIndex, userDecks[myDeckIndex].getDeckSize());

        // Mi mano
        System.out.println("\n🃏 Your Hand:");
        for (int i = 0; i < myHand.length; i++) {
            LogicHandContainer container = myHand[i];
            if (container != null && container.getHandCard() != null) {
                System.out.printf("[%d] %s  ", i, container.getHandCard());
            } else {
                System.out.printf("[%d] (empty)  ", i);
            }
        }

        System.out.println("\n════════════════════════════════════════════════════════════\n");

        showMenu();
    }

    public void showMenu() {
        try {
            Scanner scanner = new Scanner(System.in);

            System.out.println("╔══════════════════════════════╗");
            System.out.println("║     Menú de Jugada Player " + numPlayer + "  ║");
            System.out.println("╚══════════════════════════════╝");
            System.out.println("1. Jugar carta");
            System.out.println("2. Mover carta");
            System.out.println("3. Gritar ¡Chin!");
            System.out.println("4. Reanudar partida");
            System.out.println("5. Ver información");
            System.out.println("0. Pasar turno");
            System.out.print("Elegí una opción: ");

            int opcion = scanner.nextInt();

            switch (opcion) {
                case 1 -> {
                    System.out.print("Elegí el índice de la carta de tu mano: ");
                    int cardIndex = scanner.nextInt();
                    if (cardIndex < 0 || cardIndex >= myHand.length) {
                        System.out.println("Índice inválido.");
                        break;
                    }

                    System.out.print("Elegí el índice del mazo donde la querés jugar: ");
                    int deckIndex = scanner.nextInt();
                    if (deckIndex < 0 || deckIndex >= playDecks.length) {
                        System.out.println("Índice de mazo inválido.");
                        break;
                    }

                    Card cardToPlay = myHand[cardIndex].getHandCard();
                    LogicDeckContainer targetDeck = playDecks[deckIndex];
                    requestPlayCard(cardToPlay, targetDeck);
                }

                case 2 -> {
                    System.out.print("Elegí el índice de la carta de tu mano: ");
                    int cardIndex = scanner.nextInt();
                    if (cardIndex < 0 || cardIndex >= myHand.length) {
                        System.out.println("Índice inválido.");
                        break;
                    }

                    System.out.print("Nueva posición X: ");
                    int x = scanner.nextInt();
                    System.out.print("Nueva posición Y: ");
                    int y = scanner.nextInt();

                    Card cardToMove = myHand[cardIndex].getHandCard();
                    requestMoveCard(cardToMove, new Point(x, y));
                }

                case 3 -> requestChin();

                case 4 -> {
                    if (!isReady) {
                        requestInitGame();
                    } else {
                        requestResume();
                    }
                }

                case 5 -> displayGameState();

                case 0 -> {
                    System.out.println("pasando");

                }

                default -> {
                    System.out.println("Opción inválida.");
                    scanner.close();
                }
            }

            System.out.println(); // Espacio entre acciones

        } catch (Exception e) {
            System.out.println("Error en la entrada: " + e.getMessage());
        }
    }

}