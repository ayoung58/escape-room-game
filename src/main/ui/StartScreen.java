package ui;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.*;


// Creates the start screen for the game
public class StartScreen extends JPanel implements ActionListener {
    private BackgroundImage backImage = new BackgroundImage();
    private GuiEscapeGame guiEscapeGame;
    JButton start;

    JFrame frame = new JFrame();

//    EFFECTS: creates the start button and frame, and creates the start splash screen
    public StartScreen() {
        start = new JButton("Start");
        start.setActionCommand("start");
        start.addActionListener(this);
        backImage.setLayout(new GridBagLayout());
        backImage.add(start,new GridBagConstraints());
        frame.add(backImage);
        frame.setSize(500,500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
    }

//    EFFECTS: starts the game if the start button is pressed
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("start")) {
            guiEscapeGame = new GuiEscapeGame();
            frame.dispose();
        }
    }
}
