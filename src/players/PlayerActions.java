package players;

import card.Card;
import containers.LogicDeckContainer;
import game.GameController;

import java.awt.Point;


public interface PlayerActions {

    public void requestResume();
    public void requestPlayCard(Card card, LogicDeckContainer logicDeckContainer);
    public void requestChin();
    public void requestMoveCard(Card card, Point newPosition);
    public void requestInitGame();
    public void setGameController(GameController gameController);
}