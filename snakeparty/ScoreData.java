package snakeparty;

import java.io.Serializable;

public class ScoreData implements Serializable{
    private long id;
    private int score;
    
    public ScoreData(int score) {
        this.score = score;
    }
    
    public ScoreData(long id, int score) {
        this.id = id;
        this.score = score;
    }
    
    
    public int getScore() {
        return this.score;
    }
    
    public void setId (long id) {
        this.id = id;
    }
    
    public long getId() {
        return this.id;
    }
}
