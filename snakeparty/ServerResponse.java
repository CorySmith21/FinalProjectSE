package snakeparty;

import java.io.Serializable;
import java.util.ArrayList;

public class ServerResponse implements Serializable  {
    private String targetPanel;
    private Boolean status;
    private int clientsConnected;
    private ArrayList<ScoreData> scores;
    
    public ServerResponse (String targetPanel, Boolean status) {
        this.targetPanel = targetPanel;
        this.status = status;
    }
    
    
    public ServerResponse(String targetPanel, boolean status, int size) {
        this.targetPanel = targetPanel;
        this.status = status;
        this.clientsConnected = size;
    }

    public ServerResponse(String targetPanel, boolean status, ArrayList<ScoreData> scores) {
        this.targetPanel = targetPanel;
        this.status = status;
        this.scores = scores;
    }


    public String getTargetPanel () {
        return targetPanel;
    }
    
    public Boolean getStatus() {
        return status;
    }
    
    public int getClientsConnected() {
        return clientsConnected;
    }
    
    public ArrayList<ScoreData> getScores() {
        return scores;
    }
}
