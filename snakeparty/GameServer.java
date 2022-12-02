package snakeparty;

import java.awt.*;
import java.io.IOException;

import javax.swing.*;
import ocsf.server.AbstractServer;
import ocsf.server.ConnectionToClient;

public class GameServer extends AbstractServer {
    private ClientMsgHandler clientMsgHandler = new ClientMsgHandler();
    private JTextArea log;
    private JLabel status;
    private boolean running = false;

    public GameServer() {
        super(8300);
        try {
            this.setTimeout(50000);
            this.listen();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean isRunning() {
        return running;
    }

    public void setLog(JTextArea log) {
        this.log = log;
    }

    public void setStatus(JLabel status) {
        this.status = status;
    }

    public void serverStarted() {
        status.setText("Listening");
        status.setForeground(Color.GREEN);
        log.append("Server started\n");
    }

    public void serverStopped() {
        status.setText("Stopped");
        status.setForeground(Color.RED);
        log.append("Server stopped accepting new clients - press Listen to start accepting new clients\n");
    }

    public void serverClosed() {
        running = false;
        status.setText("Close");
        status.setForeground(Color.RED);
        log.append("Server and all current clients are closed - press Listen to restart\n");
    }

    public void clientConnected(ConnectionToClient client) {
        System.out.println("Client connected");
        log.append("Client " + client.getId() + " connected\n");
    }

    public void handleMessageFromClient(Object arg0, ConnectionToClient arg1) {
        if (arg0 instanceof LoginData) {
            Boolean ret = clientMsgHandler.verifyAccount((snakeparty.LoginData) arg0);
            if (ret) {
                try {
                    ServerResponse response = new ServerResponse("LoginPanel", ret);
                    arg1.sendToClient(response);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {
                try {
                    ServerResponse response = new ServerResponse("LoginPanel", ret);
                    arg1.sendToClient(response);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return;
    }
    // Method that handles listening exceptions by displaying exception information.
    public void listeningException(Throwable exception) {
        running = false;
        status.setText("Exception occurred while listening");
        status.setForeground(Color.RED);
        log.append("Listening exception: " + exception.getMessage() + "\n");
        log.append("Press Listen to restart server\n");
    }
}
