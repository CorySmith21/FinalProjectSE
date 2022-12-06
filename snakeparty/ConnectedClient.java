package snakeparty;

import java.io.Serializable;

import ocsf.server.ConnectionToClient;

public class ConnectedClient implements Serializable  {
    private long id;
    private ConnectionToClient client;
    private boolean loggedIn = false;
    private int score = 0;
    private boolean gameInProgress = false;
    
    public ConnectedClient(long id, ConnectionToClient client) {
        this.id = id;
        this.client = client;
    }
    
    public long getId() {
        return id;
    }
    
    public ConnectionToClient getClient() {
        return this.client;
    }
    
    public void setLoggedIn(boolean loggedIn) {
        this.loggedIn = loggedIn;
    }
    
    public boolean getLoggedin() {
        return loggedIn;
    }
    
    public void setScore(int score) {
        this.score = score;
    }
    
    public int getScore() {
        return this.score;
    }
    
    public void setGameInProgress(boolean status) {
        this.gameInProgress = status;
    }
    
    public boolean getGameInProgress() {
        return this.gameInProgress;
    }
    
}
