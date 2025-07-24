package ui.CardMouseEventHandle;

import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import javax.swing.SwingUtilities;

import collisionManager.CollisionManager;
import containers.LogicDeckContainer;
import players.PlayerActions;
import ui.graphicEntities.GraphicCard;



public class CardMouseEventHandle implements MouseListener,MouseMotionListener{
    private GraphicCard card;
    private boolean move;
    private PlayerActions player; 

    public CardMouseEventHandle(GraphicCard card, PlayerActions player) {
        this.card = card;
        this.player = player;
    }
    
    
    
    
    @Override
    public void mouseClicked(MouseEvent e) {
        
    }

    @Override
    public void mousePressed(MouseEvent e) {
       
  
        if(e.getComponent() == this.card && card.getCanMove()){
            move = true;
            card.getParent().setComponentZOrder(card, 0);
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if(e.getComponent() == this.card && move){
            move = false;
            LogicDeckContainer playDeck = CollisionManager.getInstance().cardCollidesWithDecks(card.getBounds());
            if(playDeck != null){
                player.requestPlayCard(card.getCard(), playDeck);
            } else {
                player.requestMoveCard(card.getCard(), card.getHandPosition());
            }
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        
    }

    @Override
    public void mouseExited(MouseEvent e) {
        
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        if(move){
            Point p = SwingUtilities.convertPoint(e.getComponent(), e.getPoint(), card.getParent());
            Point newlocation = new Point(p.x - card.getWidth() / 2, p.y - card.getHeight() / 2);
            player.requestMoveCard(card.getCard(), newlocation);
        }
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        
    }

}
