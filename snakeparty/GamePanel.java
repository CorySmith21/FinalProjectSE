package snakeparty;

import ocsf.client.AbstractClient;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import java.awt.Color;
import java.awt.Font;

public class GamePanel implements KeyListener, ActionListener {
    private JPanel panel = new JPanel();
    private JFrame frame;
    private GameView gameView;
    private JButton startBtn;
    private JButton quitBtn;
    private AbstractClient client;
    private int numberOfClientsConnected;
    private JPanel panel_2;
    private JLabel lblNewLabel;
    private JLabel numberOfClientsConnectedLabel;
    private int currentGameScore;
    private JLabel lblNewLabel_1;
    private JLabel scoreLabel;
    private ArrayList<ScoreData> scores;
    private JPanel scoresPanel;
    private JLabel playerLabel;
    private JLabel lblNewLabel_3;

    public GamePanel(JFrame frame, AbstractClient client) {
        this.frame = frame;
        this.client = client;
        this.gameView = new GameView(frame, client);
        this.initializePanel();
    }

    public void initializePanel() {
        panel.removeAll();
        panel.setFocusable(true);
        panel.addKeyListener(this);
        frame.getContentPane().add(panel);
        frame.setSize(450, 550);
        GridBagLayout gbl_panel = new GridBagLayout();
        gbl_panel.columnWidths = new int[] { 0, 143, 0, 0 };
        gbl_panel.rowHeights = new int[] { 30, 305, 0, 0, 0, 0 };
        gbl_panel.columnWeights = new double[] { 1.0, 1.0, 1.0, Double.MIN_VALUE };
        gbl_panel.rowWeights = new double[] { 1.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE };
        panel.setLayout(gbl_panel);
        GridBagConstraints gbc_gameView = new GridBagConstraints();
        gbc_gameView.insets = new Insets(0, 0, 5, 5);
        gbc_gameView.gridx = 1;
        gbc_gameView.gridy = 1;
        panel.add(gameView, gbc_gameView);

        panel_2 = new JPanel();
        GridBagConstraints gbc_panel_2 = new GridBagConstraints();
        gbc_panel_2.insets = new Insets(0, 0, 5, 5);
        gbc_panel_2.fill = GridBagConstraints.BOTH;
        gbc_panel_2.gridx = 1;
        gbc_panel_2.gridy = 2;
        panel.add(panel_2, gbc_panel_2);

        lblNewLabel = new JLabel("Connected Clients:");
        lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 11));
        panel_2.add(lblNewLabel);

        numberOfClientsConnectedLabel = new JLabel("0");
        numberOfClientsConnectedLabel.setFont(new Font("Tahoma", Font.BOLD, 11));
        numberOfClientsConnectedLabel.setForeground(new Color(0, 255, 64));
        panel_2.add(numberOfClientsConnectedLabel);

        lblNewLabel_1 = new JLabel("Score:");
        lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 11));
        panel_2.add(lblNewLabel_1);

        scoreLabel = new JLabel("0");
        scoreLabel.setFont(new Font("Tahoma", Font.BOLD, 11));
        panel_2.add(scoreLabel);

        JPanel panel_1 = new JPanel();
        GridBagConstraints gbc_panel_1 = new GridBagConstraints();
        gbc_panel_1.anchor = GridBagConstraints.NORTH;
        gbc_panel_1.insets = new Insets(0, 0, 5, 5);
        gbc_panel_1.gridx = 1;
        gbc_panel_1.gridy = 3;
        panel.add(panel_1, gbc_panel_1);

        startBtn = new JButton("Start");
        startBtn.setEnabled(false);
        startBtn.addActionListener(this);
        panel_1.add(startBtn);

        quitBtn = new JButton("Quit");
        quitBtn.addActionListener(this);
        panel_1.add(quitBtn);
        
        scoresPanel = new JPanel();
        GridBagConstraints gbc_scoresPanel = new GridBagConstraints();
        gbc_scoresPanel.insets = new Insets(0, 0, 0, 5);
        gbc_scoresPanel.fill = GridBagConstraints.BOTH;
        gbc_scoresPanel.gridx = 1;
        gbc_scoresPanel.gridy = 4;
        panel.add(scoresPanel, gbc_scoresPanel);
        
        playerLabel = new JLabel("");
        scoresPanel.add(playerLabel);
        
        panel.requestFocus();
    }

    public void display() {
        frame.getContentPane().removeAll();
        this.initializePanel();
        frame.validate();
        frame.repaint();
    }

    @Override
    public void keyPressed(KeyEvent ke) {
        if (ke.getKeyCode() == KeyEvent.VK_LEFT && (!gameView.rightDirection)) {
            gameView.leftPress();
        }
        if (ke.getKeyCode() == KeyEvent.VK_RIGHT && (!gameView.leftDirection)) {
            gameView.rightPress();
        }
        if (ke.getKeyCode() == KeyEvent.VK_UP && (!gameView.downDirection)) {
            gameView.upPress();
        }
        if (ke.getKeyCode() == KeyEvent.VK_DOWN && (!gameView.upDirection)) {
            gameView.downPress();
        }
    }

    public void updateNumberOfClientsConnected(int num) {
        if (num > 1) {
            numberOfClientsConnectedLabel.setText(String.format("%s", num));
            startBtn.setEnabled(true);
        } else {
            numberOfClientsConnectedLabel.setText(String.format("%s", 0));
            startBtn.setEnabled(false);
        }
        numberOfClientsConnected = num;
    }
    
    public void forceStartOrEnd(boolean start) {
        if (start) {
            startBtn.setEnabled(false);
            gameView.start();
            panel.requestFocus();
        } 
    }

    public void updateCurrentGameScore(int score) {
        scoreLabel.setText(String.format("%s", score));
        currentGameScore = score;
    }

    @Override
    public void keyTyped(KeyEvent ke) {
    }

    @Override
    public void keyReleased(KeyEvent ke) {
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if (source == startBtn) {
            try {
                PanelControl.startGame();
                this.forceStartOrEnd(true);
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }

        if (source == quitBtn) {
            if (client.isConnected()) {
                try {
                    client.sendToServer("remove");
                    client.closeConnection();
                } catch (IOException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
            }
            PanelControl.initialPanel.display();
        }

    }

    public void updateScores(ArrayList<ScoreData> scores) {
        this.scores = scores;
        ArrayList<String> tmp = new ArrayList<String>(); 
        for(int i = 0; i < scores.size(); i++) {
            tmp.add(String.format("Client %s : %s", scores.get(i).getId(), scores.get(i).getScore()));
        }
        
        playerLabel.setText(tmp.toString().replace("[", "").replace("]", "").replace(",", " "));
        
    }

    public void itsOver() {
        int index = 0;
        int score = 0;
        for (int i = 0; i < scores.size(); i++) {
            if (scores.get(i).getScore() > score) {
                index = i;
                score = scores.get(i).getScore();
            }
        }
        
        playerLabel.setText(String.format("The winner is Client %s with %s points", scores.get(index).getId(), scores.get(index).getScore()));
        
        gameView.done(null);
    }
}
