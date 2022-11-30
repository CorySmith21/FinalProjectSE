package snakeparty;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.io.IOException;

public class GameServerGui extends JFrame {
    private JLabel status;
    private JTextArea log;
    private JButton quit;
    private GameServer server;

    public GameServerGui() {
        // Create the main variables that will be used.
        JPanel north = new JPanel();
        JPanel center = new JPanel(new BorderLayout());
        JPanel south = new JPanel();
        EventHandler handler = new EventHandler();
        int i = 0;

        // Set the title and default close operation.
        this.setTitle("Chat Server");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Create the status label.
        JLabel statusText = new JLabel("Status:");
        north.add(statusText);
        status = new JLabel("Not Connected");
        status.setForeground(Color.RED);
        north.add(status);

        // Create the server log panel.
        JPanel serverLogPanel = new JPanel(new BorderLayout());
        JLabel serverLabel = new JLabel("Server Log", JLabel.CENTER);
        JPanel serverLabelBuffer = new JPanel();
        serverLabelBuffer.add(serverLabel);
        serverLogPanel.add(serverLabelBuffer, BorderLayout.NORTH);
        log = new JTextArea(10, 35);
        log.setEditable(false);
        JScrollPane serverLogPane = new JScrollPane(log);
        JPanel serverLogPaneBuffer = new JPanel();
        serverLogPaneBuffer.add(serverLogPane);
        serverLogPanel.add(serverLogPaneBuffer, BorderLayout.SOUTH);

        // Add the server log panel to the south part of the center.
        JPanel centerSouth = new JPanel();
        centerSouth.add(serverLogPanel);
        center.add(centerSouth, BorderLayout.SOUTH);

        quit = new JButton("Quit");
        quit.addActionListener(handler);
        south.add(quit);

        // Add the north, center, and south JPanels to the JFrame.
        this.add(north, BorderLayout.NORTH);
        this.add(center, BorderLayout.CENTER);
        this.add(south, BorderLayout.SOUTH);

        // Display the window.
        this.setSize(450, 450);
        this.setVisible(true);
        
        this.initializeServer();

    }

    public void initializeServer() {
        server = new GameServer();
        server.setLog(log);
        server.setStatus(status);
    }

    public static void main(String[] args) {
        new GameServerGui();
    }

    class EventHandler implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            Object buttonClicked = e.getSource();

            if (buttonClicked == quit) {
                System.exit(0);
            }
        }
    }
}
