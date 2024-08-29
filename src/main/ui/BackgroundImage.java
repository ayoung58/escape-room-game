package ui;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import javax.imageio.ImageIO;

// Loads in the background image as a panel
public class BackgroundImage extends JPanel {
    private Image background;

//  EFFECT: imports the background image
    public BackgroundImage() {
        try {
            background = ImageIO.read(new File("src/main/ui/escapeRoomBackground.jpg"));
        } catch (Exception e) {
            System.out.println("no file.");
        }
    }

//    EFFECTS: paints the image onto the panel
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(background, 0, 0, null);
    }
}
