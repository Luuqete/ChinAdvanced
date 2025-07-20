package game;

import card.Card;
import containers.LogicDeckContainer;
import containers.LogicHandContainer;

public class GameLogic {


    public static boolean checkWin(int player,GameState gameState) {
        if (!gameState.userDecksContainers[player].isDeckEmpty())
            return false;

        LogicHandContainer[] handContainers = player == 1 ? gameState.p1HandContainers : gameState.p2HandContainers;

        for (LogicHandContainer container : handContainers) {
            if (container.isActive()) {
                return false;
            }
        }
        return true;
    }

    public static boolean isBlocked(GameState gameState) {
        boolean isp1Blocked = true;
        boolean isp2Blocked = true;

        for (LogicHandContainer container : gameState.p1HandContainers) {

            if (!container.isActive())
                continue;

            Card card = container.getHandCard();
            if (gameState.playDecksContainers[0].canPlay(card) || gameState.playDecksContainers[1].canPlay(card)) {
                isp1Blocked = false;
            }
        }

        for (LogicHandContainer container : gameState.p2HandContainers) {

            if (!container.isActive())
                continue;

            Card card = container.getHandCard();
            if (gameState.playDecksContainers[0].canPlay(card) || gameState.playDecksContainers[1].canPlay(card)) {
                isp2Blocked = false;
            }
        }

        return isp1Blocked && isp2Blocked;
    }

    public static boolean isChin(Card firstDeckCard, Card secondDeckCard) {
        return firstDeckCard.getNum() == secondDeckCard.getNum();
    }

    public static boolean canPlayCard(Card card, LogicDeckContainer logicDeckContainer) {
        return logicDeckContainer.canPlay(card);
    }
}
