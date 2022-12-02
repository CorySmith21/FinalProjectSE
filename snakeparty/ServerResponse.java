package snakeparty;

import java.io.Serializable;

public class ServerResponse implements Serializable  {
    private String targetPanel;
    private Boolean status;
    
    public ServerResponse (String targetPanel, Boolean status) {
        this.targetPanel = targetPanel;
        this.status = status;
    }
    
    public String getTargetPanel () {
        return targetPanel;
    }
    
    public Boolean getStatus() {
        return status;
    }
}
