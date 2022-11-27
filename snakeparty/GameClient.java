package snakeparty;

import javax.swing.JFrame;
import ocsf.client.AbstractClient;

// Entry point for client requirement
// Will not directly carry out anything to do with the server

public class GameClient extends AbstractClient {
    private JFrame frame = new JFrame();
    @SuppressWarnings("unused")
    private InitialPanel initialPanel;

    public GameClient() {
        super("127.0.0.1", 12345);
        frame.setVisible(true);
        frame.setSize(469, 420);
        frame.setResizable(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        initialPanel = new InitialPanel(frame);
    }

    public void connectionEstablished() {
        System.out.println("We're up baby!");
    }

    public void handleMessageFromServer(Object arg0) {
        System.out.println("Server is trying to tell us something...");
    }

    public void connectionClosed() {
        System.out.println("Time to shut her down...");
    }

    public static void main(String[] args) {
        new GameClient();
    }
}
