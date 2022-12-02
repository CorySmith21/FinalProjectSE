package snakeparty;

import java.io.IOException;

import javax.swing.JFrame;
import ocsf.client.AbstractClient;

// Entry point for client requirement
// Will not directly carry out anything to do with the server

public class GameClient extends AbstractClient {
    private JFrame frame = new JFrame();
    private InitialPanel initialPanel;

    public GameClient() {
        super("localhost", 8300);
        frame.setVisible(true);
        frame.setSize(469, 420);
        frame.setResizable(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        initialPanel = new InitialPanel(frame, this);
        initialPanel.display();
        this.check();
    }

    public void check() {
        try {
            this.openConnection();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(isConnected());
    }

    public void connectionEstablished() {
//        System.out.println("We're up baby!");
    }

    protected void handleMessageFromServer(Object arg0) {

        if (arg0 instanceof ServerResponse) {
            if (((ServerResponse) arg0).getTargetPanel().equals("LoginPanel")) {
                LoginPanel loginPanel = new LoginPanel(frame, this);
                if (((ServerResponse) arg0).getStatus().equals(true)) {
                    loginPanel.success();
                }
                if (((ServerResponse) arg0).getStatus().equals(false)) {
                    loginPanel.failure();
                }

            }
            if (((ServerResponse) arg0).getTargetPanel().equals("CreateAccountPanel")) {
               // CreateAccountPanel createAccountPanel = new CreateAccountPanel(frame, this);
                if (((ServerResponse) arg0).getStatus().equals(true)) {
                    // createAccountPanel.success()
                }
                if (((ServerResponse) arg0).getStatus().equals(false)) {
                    // createAccountPanel.failure()
                }
            }
        }
    }

    public void connectionClosed() {
//        System.out.println("Time to shut her down...");
    }

    public static void main(String[] args) {
        new GameClient();
    }

}
