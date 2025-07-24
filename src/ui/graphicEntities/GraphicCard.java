package ui.graphicEntities;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import java.awt.Dimension;
import java.awt.Image;
import java.awt.Point;

import card.Card;
import ui.settings.Settings;

public class GraphicCard extends JLabel {
    private Card card;
    private boolean isFaceDown;
    private boolean canMove;
    private Point handPosition;
    public GraphicCard(Card card, boolean canMove, boolean isFaceDown) {
        super();
        this.card = card;
        this.canMove = canMove;
        this.isFaceDown = isFaceDown;
        setLocation(Settings.translateLogicPoint(card.getLocation()));
        setSize(Settings.scaleDimention(new Dimension(60, 96)));
        setVisible(false);
    }

    private void configureImage() {
        ImageIcon originalIcon;
        if (isFaceDown) {
            originalIcon = new ImageIcon("src/cardsImages/" + Card.CARD_BACK + ".png");
        } else {
            originalIcon = new ImageIcon("src/cardsImages/" + card.getPathName() + ".png");

        }
        Dimension scaledDimension = Settings.scaleDimention(new Dimension(60, 96));
        Image scaledImage = originalIcon.getImage().getScaledInstance(scaledDimension.width, scaledDimension.height, Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(scaledImage);
        this.setIcon(scaledIcon);
    }

    public void show(){
        configureImage();
        setVisible(true);
    }

    public void setFaceDown(boolean isFaceDown) {
        this.isFaceDown = isFaceDown;
        configureImage();
    }

    public boolean isFaceDown() {
        return isFaceDown;
    }

    public Card getCard(){
        return card;
    }

    public Point getHandPosition() {
        return handPosition;
    }

    public void setHandPosition(Point handPosition) {
        this.handPosition = handPosition;
        super.setLocation(handPosition);
    }

    public boolean getCanMove() {
        return canMove;
    }

    public void moveToHandPosition() {
        setLocation(handPosition);
    }

    public void setCanMove(boolean canMove) {
        this.canMove = canMove;
    }
}
