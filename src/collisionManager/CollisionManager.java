package collisionManager;

import java.awt.Rectangle;

import containers.LogicDeckContainer;
import ui.graphicEntities.GraphicDeckContainer;

public class CollisionManager {

    private static CollisionManager instance;

    private GraphicDeckContainer[] playDecks;

    private CollisionManager(GraphicDeckContainer[] playDecks) {
        this.playDecks = playDecks;
    }

    public LogicDeckContainer cardCollidesWithDecks(Rectangle bounds) {
        for (GraphicDeckContainer deck : playDecks) {
            if (deck.getBounds().intersects(bounds)) {
                return deck.getLogicDeckContainer();
            }
        }
        return null;
    }

    public static CollisionManager getInstance() {
        if (instance == null) {
            throw new IllegalStateException("CollisionManager has not been initialized.");
        }
        return instance;
    }

    public static void initialize(GraphicDeckContainer[] playDecks) {
        if (instance == null) {
            instance = new CollisionManager(playDecks);
        }
    }
    
}
