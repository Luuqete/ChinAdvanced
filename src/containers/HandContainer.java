package containers;

import card.Card;
import entity.Entity;

public class HandContainer extends Entity implements LogicHandContainer {
    private Card handCard;
    private LogicDeckContainer userDeck;
    public boolean isActive;

    public HandContainer(LogicDeckContainer userDeck) {
        super();
        this.userDeck = userDeck;
        this.handCard = null;
        this.isActive = false;
    }

    @Override
    public void askCard() {
        if(!userDeck.isDeckEmpty()) {
            handCard = userDeck.takeTopCard();
        } else {
            this.disable(); // or handle the case when the deck is empty
        }
    }

    @Override
    public boolean isActive() {
        return isActive;
    }

    @Override
    public void enable() {
        isActive = true;
    }

    @Override
    public void disable() {
        isActive = false;
    }

    @Override
    public Card getHandCard() {
        return handCard;
    }

}
