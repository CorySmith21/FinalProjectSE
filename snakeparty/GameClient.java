package snakeparty;

import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JFrame;
import ocsf.client.AbstractClient;

// Entry point for client requirement
// Will not directly carry out anything to do with the server

public class GameClient extends AbstractClient {
    private JFrame frame = new JFrame();
    private PanelControl mainControl;

    public GameClient() {
        super("localhost", 8300);
        frame.setVisible(true);
        frame.setSize(450,450);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainControl = new PanelControl(frame, this);
        this.initialize();
    }

    public void initialize() {
        PanelControl.initialPanel.display();
    }
    

    public void connectionEstablished() {}

    protected void handleMessageFromServer(Object arg0) {
        
        if (arg0 instanceof ServerResponse) {
            
            if (((ServerResponse) arg0).getTargetPanel().equals("LoginPanel")) {
             
                if (((ServerResponse) arg0).getStatus().equals(true)) {
                    PanelControl.loginPanel.success();
                }
                if (((ServerResponse) arg0).getStatus().equals(false)) {
                    PanelControl.loginPanel.failure();
                }

            }
            if (((ServerResponse) arg0).getTargetPanel().equals("CreateAccountPanel")) {
                if (((ServerResponse) arg0).getStatus().equals(true)) {
                     PanelControl.createAccountPanel.success();
                }
                if (((ServerResponse) arg0).getStatus().equals(false)) {
                    PanelControl.createAccountPanel.failure();
                }
            }
            
            if (((ServerResponse) arg0).getTargetPanel().equals("gamePanel")) {
                 if (((ServerResponse) arg0).getStatus().equals(true)) {
                     PanelControl.gamePanel.forceStartOrEnd(true);
                 }
                 if (((ServerResponse) arg0).getStatus().equals(false)) {
                     // createAccountPanel.failure()
                 }
             }
            
            if (((ServerResponse) arg0).getTargetPanel().equals("gameScore")) {
                PanelControl.setScores(((ServerResponse) arg0).getScores());
            }
             
            
            if (((ServerResponse) arg0).getTargetPanel().equals("all")) {
                PanelControl.setNumberOfClientsConnected(((ServerResponse) arg0).getClientsConnected());
            }
            
            if (((ServerResponse) arg0).getTargetPanel().equals("over")) {
                PanelControl.endGame();
            }
        }
        
    }

    public void connectionClosed() {
        try {
            this.sendToServer("killme");
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new GameClient();
    }

}
