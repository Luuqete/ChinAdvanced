package ui;

import containers.LogicHandContainer;
import card.Card;
import containers.LogicDeckContainer;
public interface GameUI {
    public void updateUserHand(LogicHandContainer[] userHand);
    public void updateOpponentHand(LogicHandContainer[] opponentHand);
    public void updatePlayDecks(LogicDeckContainer[] playDecks);
    public void updateUserDecks(LogicDeckContainer[] userDecks);
    public void moveCard(Card card);
    public void backToHandContainer(Card card);

    public void hidePlayCards();
    public void showPlayCards();
    public void onPause();
    public void onResume();
    public void onWin();
    public void onLose();
    public void onInit();
    public void onChin();
    public void onAttachment();
}