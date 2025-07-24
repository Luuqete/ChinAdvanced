package ui.panels;

import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import ui.GraphicalConstants;
import ui.settings.Settings;

public class GamePanel extends JPanel {
    private JLabel bg;
    private JButton startResumeButton;
    private JButton chinButton;
    private JLabel messagesLabel;

    //Necesita el player????????????

    public GamePanel() {
        super();

        setUpLayout();

        setUpBackground();

        setUpResumeButton();

        setUpChinButton();

        setUpMessagesLabel();
    }

    private void setUpLayout() {
        setLayout(null);
        setSize(Settings.scaleDimention(GraphicalConstants.GAME_PANEL_SIZE));
        setVisible(false);
    }

    private void setUpBackground() {
        bg = new JLabel();
        bg.setSize(Settings.scaleDimention(GraphicalConstants.GAME_PANEL_SIZE));
        bg.setLocation(0, 0);
        setComponentZOrder(bg, getComponentCount() - 1);
        // bg.setIcon(new javax.swing.ImageIcon("src/images/game_background.png"));
        add(bg);
    }

    private void setUpResumeButton() {
        startResumeButton = new JButton("Start/Resume");
        startResumeButton.setSize(Settings.scaleDimention(GraphicalConstants.BUTTON_SIZE));
        startResumeButton.setLocation(Settings.translateLogicPoint(GraphicalConstants.RESUME_BUTTON_LOCATION));
        setComponentZOrder(startResumeButton, 0);
        add(startResumeButton);
    }

    private void setUpChinButton() {
        chinButton = new JButton("Chin");
        chinButton.setSize(Settings.scaleDimention(GraphicalConstants.BUTTON_SIZE));
        chinButton.setLocation(Settings.translateLogicPoint(GraphicalConstants.CHIN_BUTTON_LOCATION));
        setComponentZOrder(chinButton, 0);
        add(chinButton);
    }

    private void setUpMessagesLabel() {
        messagesLabel = new JLabel();
        messagesLabel.setSize(Settings.scaleDimention(GraphicalConstants.GAME_PANEL_SIZE));
        messagesLabel.setLocation(0, 0);
        setComponentZOrder(messagesLabel, getComponentCount() - 1);
        add(messagesLabel);
    }

    private void showMessage(String text, Color color) {
        messagesLabel.setForeground(color);
        messagesLabel.setText(text);
        setComponentZOrder(messagesLabel, 0);
        revalidate();
        repaint();
    }

    private void showTimedMessage(String text, Color color, int durationMillis) {
        showMessage(text, color);

        javax.swing.Timer timer = new javax.swing.Timer(durationMillis, e -> {
            messagesLabel.setText("");
            setComponentZOrder(messagesLabel, getComponentCount() - 1);
            revalidate();
            repaint();
        });
        timer.setRepeats(false);
        timer.start();
    }

    public void showMessagePlayerWin() {
        showMessage("¡Ganaste!", Color.GREEN);
    }

    public void showMessagePlayerLose() {
        showMessage("¡Perdiste!", Color.RED);
    }

    public void showMessageChin() {
        showTimedMessage("¡¡CHIN!!", Color.YELLOW, 2000);
    }

    public void showMessageAttachamentFixed() {
        showTimedMessage("Atasko", Color.BLUE, 2000);
    }

}
