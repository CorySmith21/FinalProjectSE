package snakeparty;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;

import ocsf.client.AbstractClient;

public class GamePanel implements ActionListener {
    private JPanel panel = new JPanel();
    private AbstractClient client;
    private JFrame frame;

    public GamePanel(JFrame frame, AbstractClient client) {
        this.frame = frame;
        this.client = client;
        this.initializePanel();
    }

    public void initializePanel() {

    }

    public void display() {
        frame.getContentPane().removeAll();
        frame.getContentPane().add(panel);
        frame.validate();
        frame.repaint();
    }

    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        return;
    }
}
