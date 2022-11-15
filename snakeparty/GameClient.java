package snakeparty;


import javax.swing.JFrame;


public class GameClient extends JFrame {
   private InitialPanel initialPanel;
       
   public GameClient() {
       this.setVisible(true);
       this.setSize(469,420);
      this.setResizable(true);
       this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       
       initialPanel = new InitialPanel(this);
   }
   
  
   
   
   public static void main(String[] args) {
       new GameClient();
   }
}
