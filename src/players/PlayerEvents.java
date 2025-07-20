package players;

import card.Card;
import game.GameController;
import game.comunicationObjects.GameStateInfo;

public interface PlayerEvents {
    public void onPause();
    public void onResume();
    public void onChin(GameStateInfo gameStateInfo);
    public void onWin();
    public void onLose();
    public void onGameInit(GameStateInfo gameStateInfo);
    public void onAttachament(GameStateInfo gameStateInfo);
    public void onPlayCard(GameStateInfo gameStateInfo);
    public void onCardMoved(Card card);
    public void setGameController(GameController gameController);
}
