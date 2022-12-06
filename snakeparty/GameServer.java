package snakeparty;

import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.*;
import ocsf.server.AbstractServer;
import ocsf.server.ConnectionToClient;

public class GameServer extends AbstractServer {
    private ClientMsgHandler clientMsgHandler = new ClientMsgHandler();
    private JTextArea log;
    private JLabel status;
    private ArrayList<ConnectedClient> connectedClients = new ArrayList<ConnectedClient>();
    private boolean running = false;
    static private boolean gameInProgress = false;

    public GameServer() {
        super(8300);
        this.setTimeout(50000);
        try {
            this.listen();
        } catch (IOException e) {
            // TODO Auto-generated catch block
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
        log.append("Client " + client.getId() + " connected\n");
        connectedClients.add(new ConnectedClient(client.getId(), client));
    }

    private void addClientLoggedIn(ConnectionToClient arg1) {
        for (int i = 0; i < connectedClients.size(); i++) {
            if (connectedClients.get(i).getId() == arg1.getId()) {
                connectedClients.get(i).setLoggedIn(true);
                this.updateClientsWithLatestLoggedIn();
            }
        }
    }

    private void removeClientLoggedIn(ConnectionToClient arg1) {
        int index = -1;
        for (int i = 0; i < connectedClients.size(); i++) {
            if (connectedClients.get(i).getId() == arg1.getId()) {
                log.append("Client " + connectedClients.get(i).getId() + " disconnected\n");
                index = i;
            }
        }
        if (index > -1) {
            connectedClients.remove(index);
            this.updateClientsWithLatestLoggedIn();
        }
    }

    private void updateClientsWithLatestLoggedIn() {
        ServerResponse response = new ServerResponse("all", true, connectedClients.size());
        for (int i = 0; i < connectedClients.size(); i++) {
            try {
                connectedClients.get(i).getClient().sendToClient(response);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void updateClientScore(ScoreData arg0, ConnectionToClient arg1) {
        for (int i = 0; i < connectedClients.size(); i++) {
            if (connectedClients.get(i).getId() == arg1.getId()) {
                log.append("Client " + connectedClients.get(i).getId() + " scored\n");
                connectedClients.get(i).setScore(arg0.getScore());
            }
        }

        if (gameInProgress) {
            ArrayList<ScoreData> scores = gameScores();
            ServerResponse response = new ServerResponse("gameScore", true, scores);
            for (int i = 0; i < connectedClients.size(); i++) {
                try {
                    connectedClients.get(i).getClient().sendToClient(response);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private ArrayList<ScoreData> gameScores() {
        ArrayList<ScoreData> gameScores = new ArrayList<ScoreData>();
        for (int i = 0; i < connectedClients.size(); i++) {
            gameScores.add(new ScoreData(connectedClients.get(i).getId(), connectedClients.get(i).getScore()));
        }

        return gameScores;
    }

    public void startGame(ConnectionToClient arg1) {
        gameInProgress = true;
        ServerResponse response = new ServerResponse("gamePanel", true);
        for (int i = 0; i < connectedClients.size(); i++) {
            if (connectedClients.get(i).getId() == arg1.getId()) {
                connectedClients.get(i).setGameInProgress(true);
            } else {
                try {
                    connectedClients.get(i).setGameInProgress(true);
                    connectedClients.get(i).getClient().sendToClient(response);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                ;
            }
        }
    }
    
    public void endGame(ConnectionToClient arg1) {
        for (int i = 0; i < connectedClients.size(); i++) {
            if (connectedClients.get(i).getId() == arg1.getId()) {
                connectedClients.get(i).setGameInProgress(false);
            }
        }
        
        this.endGameCheck();
    }
    
    public void endGameCheck() {
        int done = 0;
        for (int i = 0; i < connectedClients.size(); i++) {
            if (!connectedClients.get(i).getGameInProgress()) {
                GameServer.gameInProgress = false;
                done =+ 1;
            }
        }
        
        if ((connectedClients.size() - done) < 2) {
            ServerResponse response = new ServerResponse("over", true);
            for (int i = 0; i < connectedClients.size(); i++) {
                try {
                    connectedClients.get(i).getClient().sendToClient(response);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void handleMessageFromClient(Object arg0, ConnectionToClient arg1) {
        if (arg0.equals("remove")) {
            this.removeClientLoggedIn(arg1);
        }
        
        if (arg0.equals("over")) {
            this.endGame(arg1);
        }
        
        if (arg0.equals("start")) {
            this.startGame(arg1);
        }
        
        if (arg0 instanceof ScoreData) {
            this.updateClientScore((ScoreData) arg0, arg1);
        }
        
        if (arg0 instanceof LoginData) {
            Boolean ret = clientMsgHandler.verifyAccount((LoginData) arg0);
            if (ret) {
                try {
                    ServerResponse response = new ServerResponse("LoginPanel", ret);
                    arg1.sendToClient(response);
                    this.addClientLoggedIn(arg1);

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
        
        if (arg0 instanceof CreateAccountData) {
            Boolean ret = clientMsgHandler.createNewAccount((CreateAccountData) arg0);
            if (ret) {
                try {
                    ServerResponse response = new ServerResponse("CreateAccountPanel", ret);
                    arg1.sendToClient(response);

                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {
                try {
                    ServerResponse response = new ServerResponse("CreateAccountPanel", ret);
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
    }
}
