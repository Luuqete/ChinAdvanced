package game;

import java.util.ArrayList;
import java.util.List;

import card.Card;
import containers.LogicDeckContainer;
import containers.LogicHandContainer;
import deck.*;
import game.comunicationObjects.GameEvent;

public class GameFlow {
    private GameState state;

    public GameFlow(GameState state) {
        this.state = state;
        putTwoCardsFromUsersDecksToPlayDecks();
        putCardsInHandContainers();
    }

    private void putCardsInHandContainers() {
        for (int i = 0; i < state.p1HandContainers.length; i++) {
            state.p1HandContainers[i].enable();
            state.p1HandContainers[i].askCard();
        }
        for (int i = 0; i < state.p2HandContainers.length; i++) {
            state.p2HandContainers[i].enable();
            state.p2HandContainers[i].askCard();
        }
    }

    public List<GameEvent> generalCheck() {
        boolean someoneWon = false;
        boolean isBlocked = false;
        int winner = -1;
        List<GameEvent> events = new ArrayList<>();

        if (GameLogic.checkWin(0, state)) {
            someoneWon = true;
            winner = 0;
        }

        else if (GameLogic.checkWin(1, state)) {
            someoneWon = true;
            winner = 1;
        }

        while (GameLogic.isBlocked(state) && !someoneWon) {
            isBlocked = true;
            putTwoCardsFromUsersDecksToPlayDecks();

        }

        if (someoneWon) {
            events.add(new GameEvent.WinEvent(winner));
        } else if (isBlocked) {
            events.add(new GameEvent.AttachmentFixedEvent(state.getGameStateInfo()));
            events.add(pauseGame());
        } else {
            events.add(new GameEvent.NoEvent());
        }

        return events;
    }

    public List<GameEvent> handleChin(int numPlayer) {
        List<GameEvent> events = new ArrayList<>();

        Card top0 = state.playDecksContainers[0].showTopCard();
        Card top1 = state.playDecksContainers[1].showTopCard();

        if (GameLogic.isChin(top0, top1)) {
            int opponentIndex = (numPlayer == 0) ? 1 : 0;
            transferCardsToPlayerDeck(opponentIndex, 0);
            transferCardsToPlayerDeck(opponentIndex, 1);
            enableHandContainers(opponentIndex);
        } else {
            transferCardsToPlayerDeck(numPlayer, 0);
            transferCardsToPlayerDeck(numPlayer, 1);
            enableHandContainers(numPlayer);
        }

        putTwoCardsFromUsersDecksToPlayDecks();
        events.add(new GameEvent.ChinEvent(state.getGameStateInfo()));
        events.add(pauseGame());
        return events;
    }

    public GameEvent pauseGame() {
        GameEvent toRet = new GameEvent.NoEvent();
        if (!state.isPaused) {
            state.isPaused = true;
            toRet = new GameEvent.PauseEvent();
        }
        return toRet;
    }

    public GameEvent resumeGame() {
        GameEvent toRet = new GameEvent.NoEvent();
        if (state.isPaused) {
            state.isPaused = false;
            toRet = new GameEvent.ResumeEvent();
        }
        return toRet;
    }

    public GameEvent startGame() {
        return new GameEvent.StartGameEvent(state.getGameStateInfo());
    }

    public boolean canPlayCard(Card card, LogicDeckContainer logicDeckContainer) {
        return GameLogic.canPlayCard(card, logicDeckContainer);

    }

    public GameEvent playCard(Card card, LogicDeckContainer logicDeckContainer) {
        if (canPlayCard(card, logicDeckContainer)) {
            logicDeckContainer.play(card);
            LogicHandContainer handContainer = getHandContainerForCard(card);
            handContainer.askCard();
            return new GameEvent.PlayCardEvent(state.getGameStateInfo());
        } else {
            return new GameEvent.NoEvent();
        }
    }

    // Auxiliary private methods

    private LogicHandContainer getHandContainerForCard(Card card) {
        for (LogicHandContainer handContainer : state.p1HandContainers) {
            if (handContainer.isActive() && handContainer.getHandCard().equals(card)) {
                return handContainer;
            }
        }
        for (LogicHandContainer handContainer : state.p2HandContainers) {
            if (handContainer.isActive() && handContainer.getHandCard().equals(card)) {
                return handContainer;
            }
        }
        return null; // If no matching hand container is found
    }

    private void putTwoCardsFromUsersDecksToPlayDecks() {
        GeneralDeck[] userDecks = state.userDecks;
        GeneralDeck[] playDecks = state.playDecks;

        boolean p0Empty = userDecks[0].isEmpty();
        boolean p1Empty = userDecks[1].isEmpty();

        if (p0Empty && p1Empty) {

            fillTemporalDeckIfNeeded();

            state.temporalDeck.shuffle();

            playDecks[0].pushCard((state.temporalDeck.takeTopCard()));
            playDecks[1].pushCard((state.temporalDeck.takeTopCard()));
        } else if (p0Empty) {
            putTwoCardsFromOneDeck(userDecks[1]);
        } else if (p1Empty) {
            putTwoCardsFromOneDeck(userDecks[0]);
        } else {
            playDecks[0].pushCard(userDecks[0].takeTopCard());
            playDecks[1].pushCard(userDecks[1].takeTopCard());
        }

    }

    private void fillTemporalDeckIfNeeded() {
        if (state.temporalDeck == null) {
            initializeTemporalDeck();
        }

        if (state.temporalDeck.isEmpty()) {
            for (GeneralDeck deck : state.playDecks) {
                while (!deck.isEmpty()) {
                    state.temporalDeck.pushCard(deck.takeTopCard());
                }
            }
        }

    }

    private void initializeTemporalDeck() {
        state.temporalDeck = new Deck();
    }

    private void putTwoCardsFromOneDeck(GeneralDeck deck) {
        Card firstCard = deck.takeTopCard();
        state.playDecks[0].pushCard(firstCard);

        Card secondCard;
        if (deck.isEmpty()) {
            fillTemporalDeckIfNeeded();
            secondCard = state.temporalDeck.takeTopCard();
        } else {
            secondCard = deck.takeTopCard();
        }
        state.playDecks[1].pushCard(secondCard);
    }

    private void transferCardsToPlayerDeck(int playerIndex, int sourceDeckIndex) {
        while (!state.playDecksContainers[sourceDeckIndex].isDeckEmpty()) {
            Card topCard = state.playDecksContainers[sourceDeckIndex].takeTopCard();
            state.userDecks[playerIndex].pushCard(topCard);
        }
    }

    private void enableHandContainers(int playerIndex) {
        LogicHandContainer[] handContainers = playerIndex == 0 ? state.p1HandContainers : state.p2HandContainers;
        for (LogicHandContainer c : handContainers) {
            if (!c.isActive()) {
                c.enable();
                c.askCard();
            }
        }
    }

}
