package ui;

import model.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import javax.swing.*;

// This class manages the inventory screen that is shown
public class InventoryScreen extends JPanel implements ActionListener {
    private BufferedImage background;
    private Color color = new Color(100,100,100, 0);
    ArrayList<Item> items;
    private JFrame frame = new JFrame("Inventory");
    ArrayList<JButton> inventoryButtons = new ArrayList<>();
    private JButton btnGreenKey = new JButton("Green key");
    private JButton btnRedKey = new JButton("Red key");
    private JButton btnPiece1 = new JButton("Piece 1");
    private JButton btnPiece2 = new JButton("Piece 2");
    private JButton btnPiece3 = new JButton("Piece 3");
    private JButton btnLockedNotebook = new JButton("Notebook (locked)");
    private JButton btnUnlockedNotebook = new JButton("Notebook (unlocked)");
    private JButton btnSmallSafe = new JButton("Small safe");
    private JButton btnAll = new JButton("All");
    private JButton btnCombinable = new JButton("Combinable");
    private JButton btnInteractable = new JButton("Interactable");
    private JPanel generalButtons = new JPanel();
    private JPanel itemButtons = new JPanel();

    GuiEscapeGame escapeGame;

//    MODIFIES: this
//    EFFECTS: sets up the inventory screen by creating a frame and panel, and adding buttons.
    public InventoryScreen(GuiEscapeGame escapeGame) {
        inventoryFrame();
        layoutSetup();
        this.escapeGame = escapeGame;
        initButtons();
        addItemButtons();
    }

//    MODIFIES: this
//    EFFECTS: this sets up the inventory frame
    public void inventoryFrame() {
        frame.setSize(300,300);
        frame.setUndecorated(true);
        frame.setVisible(true);
        frame.setBackground(Color.BLACK);
        frame.add(this);
        frame.getRootPane().setWindowDecorationStyle(JRootPane.NONE);
    }

//    MODIFIES: this
//    EFFECTS: sets up the layout and panels for the game
    public void layoutSetup() {
        setLayout(new BorderLayout());
        itemButtons.setLayout(new FlowLayout());
        add(itemButtons, BorderLayout.CENTER);
        generalButtons.setLayout(new FlowLayout());
        add(generalButtons, BorderLayout.SOUTH);
    }

//    MODIFIES: this
//    EFFECTS: adds in all the buttons that can be put in inventory, and general buttons
    public void initButtons() {
        inventoryButtons.add(btnPiece1);
        inventoryButtons.add(btnPiece2);
        inventoryButtons.add(btnPiece3);
        inventoryButtons.add(btnGreenKey);
        inventoryButtons.add(btnRedKey);
        inventoryButtons.add(btnLockedNotebook);
        inventoryButtons.add(btnUnlockedNotebook);
        inventoryButtons.add(btnSmallSafe);
        inventoryButtons.add(btnAll);
        inventoryButtons.add(btnCombinable);
        inventoryButtons.add(btnInteractable);
        setButtons();
    }

//    MODIFIES: this
//    EFFECTS: adds action listeners to the buttons and adds general/item buttons to their own respective arrayLists
    public void setButtons() {
        for (JButton button : inventoryButtons) {
            button.setActionCommand(button.getText().toLowerCase());
            button.addActionListener(this);
            if (button.getActionCommand().equals("all") || button.getActionCommand().equals("interactable")) {
                generalButtons.add(button);
                button.setVisible(true);
            } else if (button.getActionCommand().equals("combinable")) {
                generalButtons.add(button);
                button.setVisible(true);
            } else {
                itemButtons.add(button);
                button.setVisible(false);
            }
        }
    }

//    EFFECTS: disposes of this screen
    public void hideScreen() {
        frame.dispose();
    }

//    EFFECTS: adds items to inventory
    public void addItemButtons() {
        items = escapeGame.getInventory();
        setButtonsVisible(false, false, false, "");
    }

//    EFFECTS: checks which button of the general buttons were pressed
    public void actionPerformed(ActionEvent e) {
        items = escapeGame.getInventory();
        if (e.getActionCommand().equals("all")) {
            setButtonsVisible(false, false, false, "");
        } else if (e.getActionCommand().equals("combinable")) {
            setButtonsVisible(true, false, false, "");
        } else if (e.getActionCommand().equals("interactable")) {
            setButtonsVisible(false, true, false, "");
        } else {
            setButtonsVisible(false, false, true, e.getActionCommand());
        }
    }

//    MODIFIES: this
//    EFFECTS: filters out items based on their purpose (collectable/interactable/all)
    public void setButtonsVisible(boolean combineFilter, boolean interactFilter, boolean isItem, String command) {
        for (Item item : items) {
            for (JButton button : inventoryButtons) {
                if (item.getName().toLowerCase().equals(button.getActionCommand())) {
                    if (combineFilter) {
                        combineFilter(item, button);
                    } else if (interactFilter) {
                        interactFilter(item, button);
                    } else if (isItem) {
                        if (button.getActionCommand().equals(command)
                                && item.getName().toLowerCase().equals(command)) {
                            button.setVisible(false);
                            escapeGame.removeItem(item);
                        }
                    } else {
                        button.setVisible(true);
                    }
                }
            }
        }
    }

//    EFFECTS: sets button as visible if combinable, invisible if not
    public void combineFilter(Item item, JButton button) {
        if (item.getCombinableStatus()) {
            button.setVisible(true);
        } else {
            button.setVisible(false);
        }
    }

//    EFFECTS: sets button as visible if interactable, invisible if not
    public void interactFilter(Item item, JButton button) {
        if (item.getInteractableStatus()) {
            button.setVisible(true);
        } else {
            button.setVisible(false);
        }
    }
}
