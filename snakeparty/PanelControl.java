package snakeparty;

import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JFrame;

import ocsf.client.AbstractClient;

public class PanelControl {
    static public InitialPanel initialPanel;
    static public LoginPanel loginPanel;
    static public CreateAccountPanel createAccountPanel;
    static public GamePanel gamePanel;
    static public int NumberOfClientsConnected;
    static public int currentGameScore;
    static private AbstractClient client;

    public PanelControl(JFrame frame, AbstractClient client) {
        PanelControl.initialPanel = new InitialPanel(frame, client);
        PanelControl.loginPanel = new LoginPanel(frame, client);
        PanelControl.createAccountPanel = new CreateAccountPanel(frame, client);
        PanelControl.gamePanel = new GamePanel(frame, client);
        PanelControl.client = client;
    }

    public static void setNumberOfClientsConnected(int clientsConnected) {
        PanelControl.gamePanel.updateNumberOfClientsConnected(clientsConnected);
        PanelControl.NumberOfClientsConnected = clientsConnected;
    }

    public static void setCurrentScore(int score) {
        PanelControl.gamePanel.updateCurrentGameScore(score);
        PanelControl.currentGameScore = score;
        try {
            PanelControl.client.sendToServer(new ScoreData(score));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void setScores(ArrayList<ScoreData> scores) {
        PanelControl.gamePanel.updateScores(scores);
    }

    public static void startGame() throws IOException {
        PanelControl.client.sendToServer("start");
    }
    
    public static void gameOver() throws IOException {
        PanelControl.client.sendToServer("over");
    }

    public static void endGame() {
        PanelControl.gamePanel.itsOver();
    }

}
