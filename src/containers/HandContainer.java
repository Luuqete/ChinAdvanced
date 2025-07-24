package containers;

import card.Card;
import entity.Entity;

public class HandContainer extends Entity implements LogicHandContainer {
    private Card handCard;
    private LogicDeckContainer userDeck;
    public boolean isActive;
    private final int id;

    public HandContainer(LogicDeckContainer userDeck, int id) {
        super();
        this.userDeck = userDeck;
        this.handCard = null;
        this.id = id;
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

    @Override
    public int getId() {
       return id;
    }

    @Override 
    public boolean equals(Object obj) {
        boolean toRet = false;
        if (this == obj) toRet = true;
        if (!(obj instanceof HandContainer)) toRet = false;
        HandContainer other = (HandContainer) obj;
        toRet = this.id == other.getId();
        return toRet;
    }

}
