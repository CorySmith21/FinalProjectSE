package snakeparty;

import java.io.IOException;

import ocsf.server.AbstractServer;
import ocsf.server.ConnectionToClient;

public class GameServer extends AbstractServer {

    public GameServer() {
        super(12345);
        try {
            this.listen();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        System.out.println("server started");
    }

    public void handleMessageFromClient(Object arg0, ConnectionToClient arg1) {
        System.out.println("Client message");
    }

    public void listeningException(Throwable e) {
        System.out.println(e.getMessage());
    }

    public void serverStarted() {
        System.out.println("Server is up baby");
    }

    public void serverStopped() {
        System.out.println("Server is down");
    }

    public void serverClosed() {
        System.out.println("Server is closed");
    }

    public void clientConnected(ConnectionToClient client) {
        System.out.println("Client connected");
    
    }
    
    public static void main(String[] args) {
        new GameServer();
    }
}
