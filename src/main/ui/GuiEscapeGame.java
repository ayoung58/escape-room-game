package ui;

import model.*;
import javax.swing.*;
import java.awt.BorderLayout;
import model.EventLog;

import java.awt.Dimension;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import persistence.JsonReader;
import persistence.JsonWriter;
import persistence.JsonReaderInventory;
import persistence.JsonWriterInventory;

import static java.util.Arrays.asList;

// This is the heart of the escape room game and is in charge of opening/closing inventory
// as well as saving, loading, and playing the game
public class GuiEscapeGame extends JPanel implements ActionListener, WindowListener {

    private static final String JSON_STORE_INVENTORY = "./data/escapeRoomInventory.json";
    private static final String JSON_STORE = "./data/escapeRoom.json";
    private JsonWriterInventory jsonWriterInventory;
    private JsonReaderInventory jsonReaderInventory;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;
    private JFrame frame = new JFrame();

    private InventoryScreen inventoryScreen;
    private boolean inventoryShown;

    private JButton btnInventory;
    private JButton btnSaving;
    private JButton btnLoading;
    private ArrayList<JButton> buttons = new ArrayList<>();
    private JButton btnWall1;
    private JButton btnWall2;
    private JButton btnWall3;
    private JButton btnPiece1;
    private JButton btnPiece2;
    private JButton btnPiece3;
    private JButton btnPhone;
    private JButton btnCabinet;
    private JButton btnCombinationLock;
    private JButton btnDesk;
    private JButton btnDoor;
    private JButton btnGreenKey;
    private JButton btnLockedNotebook;
    private JButton btnPainting;
    private JButton btnRedKey;
    private JButton btnSmallSafe;
    private JButton btnUnlockedNotebook;

    private JPanel generalButtons = new JPanel();
    private JPanel itemButtons = new JPanel();

    private Inventory inventory = new Inventory("inventory");
    private ExplorableObjects explorableObjects;
    private Wall1 wall1;
    private Phone phone;
    private GreenKey greenKey;
    private Piece2 piece2;

    private Wall2 wall2;
    private Desk desk;
    private CombinationLock combinationLock;
    private Cabinet cabinet;
    private SmallSafe smallSafe;
    private RedKey redKey;
    private LockedNotebook lockedNotebook;
    private UnlockedNotebook unlockedNotebook;
    private Piece1 piece1;
    private Piece3 piece3;

    private Wall3 wall3;
    private Painting painting;
    private Door door;

    private final int screenWidth = 500;
    private final int screenHeight = 500;

//    MODIFIES: this
//    EFFECTS: sets up the escape room game
    public GuiEscapeGame() {
        frameSetup();
        panelLayoutSetup();
        init();
        saveLoadSetup();
        buttonInventorySavingControls();
        addItemButtons();
        inventoryShown = false;
    }

//    MODIFIES: this
//    EFFECTS: sets up the frame for the game
    public void frameSetup() {
        frame.setSize(500,500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.add(this);
        frame.addWindowListener(this);

    }

//    EFFECTS: sets up the panel and also the layout of the game
    public void panelLayoutSetup() {
        setLayout(new BorderLayout());
        itemButtons.setLayout(new FlowLayout());
        add(itemButtons, BorderLayout.CENTER);
        generalButtons.setLayout(new FlowLayout());
        add(generalButtons, BorderLayout.SOUTH);
        setPreferredSize(new Dimension(screenWidth, screenHeight));
    }

//    EFFECTS: sets up the saving and loading features of the game
    public void saveLoadSetup() {
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
        jsonReaderInventory = new JsonReaderInventory(JSON_STORE_INVENTORY);
        jsonWriterInventory = new JsonWriterInventory(JSON_STORE_INVENTORY);
    }

    //    EFFECTS: initializes the entire game
    public void init() {
        wall1 = new Wall1(true, true);
        wall2 = new Wall2(true);
        wall3 = new Wall3(true);
        door = new Door(true);
        explorableObjects = new ExplorableObjects("explorableObjects");
        explorableObjects.addItem(wall1);
        explorableObjects.addItem(wall2);
        explorableObjects.addItem(wall3);
        initWall1();
        initWall2();
        initWall3();
    }

    //    EFFECTS: Initializes items on the first wall
    public void initWall1() {
        // green key and piece 2 are hidden in the wall at the moment
        phone = new Phone(false);
        greenKey = new GreenKey(false);
        piece2 = new Piece2();
        wall1.addItems(phone);
    }

    //    EFFECTS: Initializes items on the second wall
    public void initWall2() {
        // small safe, red key, locked, notebook, and the other two pieces are hidden in cabinet
        desk = new Desk();
        smallSafe = new SmallSafe(true);
        redKey = new RedKey(false);
        lockedNotebook = new LockedNotebook(true);
        unlockedNotebook = new UnlockedNotebook();
        piece3 = new Piece3();
        piece1 = new Piece1();
        combinationLock = new CombinationLock(true);
        cabinet = new Cabinet(false, true, true);
        wall2.addItems(desk);
        wall2.addItems(combinationLock);
        wall2.addItems(cabinet);
    }

    //    EFFECTS: initializes items on the third wall
    public void initWall3() {
        painting = new Painting(false);
        wall3.addItems(door);
        wall3.addItems(painting);
        initExtraVisibleItems();
    }

//    EFFECTS: adds extra items to explorable items (for demonstration/testing purposes)
    public void initExtraVisibleItems() {
        explorableObjects.addItem(cabinet);
        explorableObjects.addItem(smallSafe);
        explorableObjects.addItem(redKey);
        explorableObjects.addItem(piece1);
        explorableObjects.addItem(piece2);
        explorableObjects.addItem(door);
        explorableObjects.addItem(greenKey);
        explorableObjects.addItem(desk);
        explorableObjects.addItem(lockedNotebook);
    }

//    MODIFIES: this
//    EFFECTS: creates buttons for inventory, saving, and loading, adds them to an array for general buttons
    public void buttonInventorySavingControls() {
        btnInventory = new JButton("Open inventory");
        btnInventory.setActionCommand("inventory");
        btnInventory.addActionListener(this); // Sets "this" object as an action listener for btn
        // so that this.actionPerformed(ActionEvent e) will be called when clicked.
        // You could also set a different object, if you wanted a different object to respond to button clicks
        generalButtons.add(btnInventory);
        btnSaving = new JButton("Save");
        btnSaving.setActionCommand("save");
        btnSaving.addActionListener(this);
        generalButtons.add(btnSaving);
        btnLoading = new JButton("Load");
        btnLoading.setActionCommand("load");
        btnLoading.addActionListener(this);
        generalButtons.add(btnLoading);
    }

//    MODIFIES: this
//    EFFECTS: creates buttons for the items and also adds them to an array containing all item buttons
    public void addItemButtons() {
        btnCabinet = new JButton("Cabinet");
        buttons.add(btnCabinet);
        btnDesk = new JButton("Desk");
        buttons.add(btnDesk);
        btnWall1 = new JButton("Wall 1");
        buttons.add(btnWall1);
        btnDoor = new JButton("Door");
        buttons.add(btnDoor);
        btnCombinationLock = new JButton("Combination lock");
        buttons.add(btnCombinationLock);
        btnGreenKey = new JButton("Green key");
        buttons.add(btnGreenKey);
        btnLockedNotebook = new JButton("Notebook (locked)");
        buttons.add(btnLockedNotebook);
        btnPainting = new JButton("Painting");
        buttons.add(btnPainting);
        btnPhone = new JButton("Phone");
        buttons.add(btnPhone);
        addItemButtons2();
    }

//    MODIFIES: this
//    EFFECTS: continues to create buttons for the items and also adds them to an array containing all item buttons
    public void addItemButtons2() {
        btnPiece1 = new JButton("Piece 1");
        buttons.add(btnPiece1);
        btnPiece2 = new JButton("Piece 2");
        buttons.add(btnPiece2);
        btnPiece3 = new JButton("Piece 3");
        buttons.add(btnPiece3);
        btnRedKey = new JButton("Red key");
        buttons.add(btnRedKey);
        btnSmallSafe = new JButton("Small Safe");
        buttons.add(btnSmallSafe);
        btnUnlockedNotebook = new JButton("Notebook (unlocked)");
        buttons.add(btnUnlockedNotebook);
        btnWall2 = new JButton("Wall 2");
        buttons.add(btnWall2);
        btnWall3 = new JButton("Wall 3");
        buttons.add(btnWall3);
        setButtonParameters();
    }

//    EFFECTS: adds action listeners for all buttons and also adds buttons to the screen if they are explorable
    public void setButtonParameters() {
        for (JButton button : buttons) {
            button.setActionCommand(button.getText().toLowerCase());
            button.addActionListener(this);
            for (Item item : explorableObjects.getExplorable()) {
                if ((item.getName().toLowerCase()).equals(button.getText().toLowerCase())) {
                    itemButtons.add(button);
                }
            }
        }
    }

    // EFFECTS: saves escape room class information to file
    private void saveEscapeGame() {
        ArrayList<Item> itemsToUpdate = new ArrayList<>(asList(cabinet, combinationLock, door, greenKey, lockedNotebook,
                painting, phone, redKey, smallSafe, wall1));
        try {
            jsonWriter.open();
            jsonWriterInventory.open();
            jsonWriterInventory.write(inventory, JSON_STORE_INVENTORY);
            jsonWriter.write(explorableObjects, JSON_STORE);
            jsonWriter.close();
            jsonWriterInventory.close();
            System.out.println("Saved escape room state to " + JSON_STORE);
            System.out.println("Saved escape room state to " + JSON_STORE_INVENTORY);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE);
            System.out.println("Or unable to write to file: " + JSON_STORE_INVENTORY);
        } catch (IOException e) {
            System.out.println("Unable to write to file: " + JSON_STORE);
            System.out.println("Or unable to write to file: " + JSON_STORE_INVENTORY);
        }
    }

    // MODIFIES: this
    // EFFECTS: loads inventory from file
    private void loadEscapeRoom() {
        try {
            inventory = jsonReaderInventory.read();
            explorableObjects = jsonReader.read();
            System.out.println("Loaded escape room state from " + JSON_STORE);
            System.out.println("Loaded escape room state from " + JSON_STORE_INVENTORY);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
            System.out.println("Or unable to read from file: " + JSON_STORE_INVENTORY);
        }
    }

//    EFFECTS: checks to see what button was pressed and directs to according methods.
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("inventory")) {
            inventoryManagement();
        } else if (e.getActionCommand().equals("save")) {
            saveEscapeGame();
        } else if (e.getActionCommand().equals("load")) {
            loadEscapeRoom();
            if (inventoryScreen != null) {
                inventoryScreen.addItemButtons();
            }
        } else {
            checkButtonPressed(e);
        }
    }

//    EFFECTS: adjusts the button text based on whether the inventory is shown or not, and shows/hides inventory
    public void inventoryManagement() {
        if (inventoryShown) {
            inventoryShown = false;
            btnInventory.setText("Open inventory");
            inventoryScreen.hideScreen();
        } else {
            inventoryShown = true;
            btnInventory.setText("Close inventory");
            inventoryScreen = new InventoryScreen(this);
            inventoryScreen.setPreferredSize(new Dimension(300, 300));
        }
    }

//    MODIFIES: this
//    EFFECTS: checks to see if button pressed is able to be added to inventory, and adds if it is
    public void checkButtonPressed(ActionEvent e) {
        for (Item item : explorableObjects.getExplorable()) {
            if (item.getName().toLowerCase().equals(e.getActionCommand().toLowerCase())) {
                if (item.getCollectableStatus()) {
                    inventory.addItem(item);
                    if (inventoryScreen != null) {
                        inventoryScreen.addItemButtons();
                    }
                }
            }
        }
    }

//    EFFECTS: gets the contents of the inventory and returns as arraylist
    public ArrayList<Item> getInventory() {
        ArrayList<Item> inventoryItems = new ArrayList<>();
        for (Item item : inventory.getInventory()) {
            inventoryItems.add(item);
        }
        return inventoryItems;
    }

//    MODIFIES: this
//    EFFECTS: removes something from inventory
    public void removeItem(Item item) {
        inventory.removeItem(item);
    }

    public void printLog(EventLog el) {
        for (model.Event next : el) {
            System.out.println(next.toString() + "\n");
        }
    }

    @Override
    public void windowClosing(WindowEvent e) {
        printLog(inventory.getEventLogInstance());
    }

    @Override
    public void windowOpened(WindowEvent e) {

    }

    @Override
    public void windowClosed(WindowEvent e) {

    }

    @Override
    public void windowIconified(WindowEvent e) {

    }

    @Override
    public void windowDeiconified(WindowEvent e) {

    }

    @Override
    public void windowActivated(WindowEvent e) {

    }

    @Override
    public void windowDeactivated(WindowEvent e) {

    }
}
