package game.comunicationObjects;

import containers.LogicDeckContainer;
import containers.LogicHandContainer;

public record GameStateInfo(LogicDeckContainer[] userDecksContainers, 
                            LogicDeckContainer[] playDecksContainers, 
                            LogicHandContainer[] p1HandContainers, 
                            LogicHandContainer[] p2HandContainers) {
    
}
