package snakeparty;

import ocsf.client.AbstractClient;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;


public class GamePanel implements ActionListener {
    private JPanel panel = new JPanel();
    private GameView gameView;
    private AbstractClient client;
    private JFrame frame;
    
    private boolean leftDirection = false;
    private boolean rightDirection = true;
    private boolean upDirection = false;
    private boolean downDirection = false;

    public GamePanel(JFrame frame, AbstractClient client) {
        this.frame = frame;
        this.client = client;
        this.initializePanel();
    }

    public void initializePanel() {
        panel.addKeyListener(new TAdapter());
        frame.getContentPane().add(panel);
    }

    public void display() {
        frame.getContentPane().removeAll();
        frame.getContentPane().add(panel);
        frame.validate();
        frame.repaint();
    }

  
    
    private class TAdapter extends KeyAdapter {

        @Override
        public void keyPressed(KeyEvent e) {

            int key = e.getKeyCode();
            System.out.println(key);

            if ((key == KeyEvent.VK_LEFT) && (!rightDirection)) {
                System.out.println("key");
                leftDirection = true;
                upDirection = false;
                downDirection = false;
            }

            if ((key == KeyEvent.VK_RIGHT) && (!leftDirection)) {
                System.out.println("key");
                rightDirection = true;
                upDirection = false;
                downDirection = false;
            }

            if ((key == KeyEvent.VK_UP) && (!downDirection)) {
                System.out.println("key");
                upDirection = true;
                rightDirection = false;
                leftDirection = false;
            }

            if ((key == KeyEvent.VK_DOWN) && (!upDirection)) {
                System.out.println("key");
                downDirection = true;
                rightDirection = false;
                leftDirection = false;
            }
        }
    }



    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println(e.toString());
        
    }
}
