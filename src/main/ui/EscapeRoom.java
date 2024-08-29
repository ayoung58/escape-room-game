package ui;

import model.*;
import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
import persistence.JsonReader;
import persistence.JsonWriter;
import persistence.JsonReaderInventory;
import persistence.JsonWriterInventory;

import java.io.FileNotFoundException;
import java.io.IOException;

import static java.util.Arrays.asList;
import static java.util.Arrays.sort;

// The EscapeRoom class is the main UI class
public class EscapeRoom {
    private static final String JSON_STORE_INVENTORY = "./data/escapeRoomInventory.json";
    private static final String JSON_STORE = "./data/escapeRoom.json";
    private JsonWriterInventory jsonWriterInventory;
    private JsonReaderInventory jsonReaderInventory;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

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

    private Scanner input;
    private Inventory inventory;
    private ExplorableObjects explorableObjects;

//    EFFECTS: creates a new wall to begin at and runs the application
    public EscapeRoom() throws FileNotFoundException {
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
        jsonReaderInventory = new JsonReaderInventory(JSON_STORE_INVENTORY);
        jsonWriterInventory = new JsonWriterInventory(JSON_STORE_INVENTORY);
        init();
        runMenu();
    }

//    EFFECTS: initializes the entire game
    public void init() {
        wall1 = new Wall1(true, true);
        wall2 = new Wall2(true);
        wall3 = new Wall3(true);
        door = new Door(true);
        inventory = new Inventory("inventory");
        input = new Scanner(System.in);
        explorableObjects = new ExplorableObjects("explorableObjects");
        explorableObjects.addItem(wall1);
        explorableObjects.addItem(wall2);
        explorableObjects.addItem(wall3);
        initWall1();
        initWall2();
        initWall3();
        input.useDelimiter("\n");
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
    }

//    EFFECTS: displays the menu of the game
    public void runMenu() {
        System.out.println("Welcome to the Escape Room. Press p to play, and q to quit.");
        boolean keepGoing = true;
        while (keepGoing) {
            String menuInput = input.next();
            if (menuInput.equals("p")) {
                keepGoing = false;
                intro();
            } else if (menuInput.equals("q")) {
                System.out.println("Goodbye!");
                break;
            } else {
                System.out.println("That is not valid...please try again");
            }
        }
    }

//    EFFECTS: shows the intro screen
    public void intro() {
        clearScreen();
        System.out.println("You wake up to find yourself in a triangular room, with your head pounding.");
        System.out.println("The last thing you remember was someone knocking you out with a baseball bat.");
        System.out.println("As you look around, you see a variety of items along each wall. You also see a door.");
        System.out.print("It's the exit, but it's locked by a password. You'll have to solve the puzzles in this ");
        System.out.println("room in order to escape. ");
        System.out.println("Press any letter key to see the instructions.");
        input.next();
        instructions();
    }

//    EFFECTS: shows the instructions to the game
    public void instructions() {
        System.out.println("In this room, you can explore different objects and discover clues.");
        System.out.println("Each time you want to explore, a set of actions will appear with a number beside them");
        System.out.println("Please type the number beside the action, and not the action itself, then press enter.");
        System.out.print("After you finish exploring an item, it may be added to your inventory, in which case ");
        System.out.println("you may want to further interact with them. ");
        System.out.println("Items will be automatically removed from your inventory when it is used.");
        System.out.println("The two main actions are observe and interact.");
        System.out.println("Observe shows the details of an object that you will type in after being prompted.");
        System.out.println("Interact may allow you to search an item or find hidden details.");
        System.out.println("It may also allow you to attempt to solve a puzzle.");
        System.out.print("Alright, it's time to start escaping! We recommend you first observe and see what you can ");
        System.out.print("explore. Good luck and have fun! ");
        System.out.print("Press any letter key to go to actions screen. \n");
        input.next();
        actionScreen();
    }

//    EFFECTS: directs user to different actions based on what they choose
    public void actionScreen() {
        while (true) {
            System.out.println("ACTIONS SCREEN \n[1] Observe \n[2] Interact \n[3] View inventory \n[4] Save game");
            System.out.println("[5] Load game\n[6] Quit game.");
            String actionScreenInput = input.next();
            switch (actionScreenInput) {
                case "1":
                    observe();
                case "2":
                    interact();
                case "3":
                    showInventory();
                case "4":
                    saveEscapeGame();
                case "5":
                    loadEscapeRoom();
                case "6":
                    System.out.println("Thank you for playing :)");
                    System.exit(0);
                default:
                    System.out.println("Invalid input, please try again.");
                    break;
            }
        }
    }

//    EFFECTS: directs user back to actions screen
    public void goBackToActionsScreen() {
        System.out.println("\nPress any letter key to return back to actions screen");
        input.next();
        actionScreen();
    }

    //    EFFECTS: prints out the items in the inventory in a list
    public void showInventory() {
        if (inventory.getInventory().size() != 0) {
            System.out.println("Inventory items:");
            for (Item item : inventory.getInventory()) {
                System.out.println(item.getName());
            }
        } else {
            System.out.println("You have no items in your inventory at the moment.");
        }
        goBackToActionsScreen();
    }

//    MODIFIES: this
//    EFFECTS: allows the observe action to be started up
    public void observe() {
        boolean observing = true;
        boolean found = false;
        while (observing) {
            System.out.println("What object would you like to observe (type name of object): ");
            for (Item i : explorableObjects.getExplorable()) {
                System.out.println(i.getName());
            }
            String observeInput = input.next();
            for (Item p : explorableObjects.getExplorable()) {
                if (p.getName().toLowerCase().equals(observeInput.toLowerCase())) {
                    observing = false;
                    observeItemPart1(observeInput.toLowerCase());
                    found = true;
                }
            }
            if (!found) {
                System.out.println("Invalid object, or the object can't be observed right now. Please try again. \n");
            }
        }
    }

    // EFFECTS: shows the details of that object if observable (part 1)
    public void observeItemPart1(String object) {
        switch (object) {
            case "wall 1":
                observeWall1();
                break;
            case "wall 2":
                observeWall2();
                break;
            case "wall 3":
                observeWall3();
                break;
            case "door":
                observeDoor();
                break;
            default:
                observeItemPart2(object);
        }
    }

    // EFFECTS: shows the details of that object if observable (part 2)
    public void observeItemPart2(String object) {
        switch (object) {
            case "phone":
                observePhone();
                break;
            case "small safe":
                observeSmallSafe();
                break;
            case "cabinet":
                observeCabinet();
                break;
            case "desk":
                observeDesk();
                break;
            case "combination lock":
                observeCombinationLock();
                break;
            case "painting":
                observePainting();
                break;
            default:
                observeItemPart3(object);
        }
    }

//    EFFECTS: shows the details of that object if observable (part 3)
    public void observeItemPart3(String object) {
        if (object.equals("piece 1") || object.equals("piece 2") || object.equals("piece 3")) {
            observePaintingPiece();
        } else if (object.equals("red key") || object.equals("green key")) {
            observeKey();
        } else {
            switch (object) {
                case "notebook (locked)":
                    observeLockedNotebook();
                    break;
                case "notebook (unlocked)":
                    observeUnlockedNotebook();
                    break;
                default:
                    System.out.println("No such object was found.");
                    goBackToActionsScreen();
            }
        }
    }

//    EFFECTS: shows the details of wall 1 when observed
    public void observeWall1() {
        System.out.println("This wall contains a keyhole and a mysterious black phone.");
        System.out.println("A three digit number is also scratched on the upper left corner: 111");
        if (wall1.getFirstTime()) {
            for (Item i : wall1.getItems()) {
                explorableObjects.addItem(i);
            }
            System.out.println("Phone has been added to list of explorable items.");
            explorableObjects.addItem(phone);
            wall1.setFirstTime(false);
        }
        goBackToActionsScreen();
    }

    //    EFFECTS: shows the details of wall 2 when observed
    public void observeWall2() {
        System.out.println("This wall contains a desk. It has a cabinet that is initially locked with ");
        System.out.println("A three digit number is also scratched on the upper right corner: 505");
        System.out.println("a combination lock.");
        if (wall2.getFirstTime()) {
            for (Item i : wall2.getItems()) {
                explorableObjects.addItem(i);
            }
            System.out.println("Desk, cabinet, and combination lock have been added to list of explorable items. ");
            wall2.setFirstTime(false);
        }
        goBackToActionsScreen();
    }

    //    EFFECTS: shows the details of wall 3 when observed
    public void observeWall3() {
        System.out.println("This wall contains the door to freedom and an empty painting frame");
        System.out.println("A four digit number is also scratched on the upper right corner: 9047");
        if (wall3.getFirstTime()) {
            for (Item i : wall3.getItems()) {
                explorableObjects.addItem(i);
            }
            System.out.println("Painting and Door have been added to list of explorable items.");
            wall3.setFirstTime(false);
        }
        goBackToActionsScreen();
    }

    //    EFFECTS: shows the details of the door when observed
    public void observeDoor() {
        if (door.getIsLocked()) {
            System.out.println("The door is locked and requires a password. Interact with it to unlock.");
        } else {
            System.out.println("The door is unlocked.");
        }
        goBackToActionsScreen();
    }

    //    EFFECTS: shows the details of the phone when observed
    public void observePhone() {
        if (phone.getCalled()) {
            System.out.println("You've already called the phone.");
        } else {
            System.out.println("The phone requires a phone number to call. Interact with it to try entering a number.");
        }
        goBackToActionsScreen();
    }

    //    EFFECTS: shows the details of the combination lock when observed
    public void observeCombinationLock() {
        if (combinationLock.getIsLocked()) {
            System.out.println("The combination lock requires a code to unlock the cabinet.");
            System.out.println("It seems to require three different numbers (one for each turn)");
            System.out.println("The combination lock seems to have number between 1 and 55.");
            System.out.println("Interact with it to try entering the code.");
        } else {
            System.out.println("The combination lock is opened.");
        }
        goBackToActionsScreen();
    }

//    MODIFIES: this
    //    EFFECTS: shows the details of the cabinet when observed, and adds objects to inventory/explorable items
    public void observeCabinet() {
        if (cabinet.getIsLocked()) {
            System.out.println("The cabinet is locked right now. Maybe unlock the combination lock?");
        } else {
            if (cabinet.getIsCabinetEmpty()) {
                System.out.println("The cabinet is now empty.");
            } else {
                System.out.print("Inside the cabinet you see a small safe, a red key, a piece of painting, and a ");
                System.out.println("notebook that needs a key. All are added to your inventory and explorable items.");
                if (cabinet.getFirstTime()) {
                    cabinet.setEmptyState(true);
                    cabinet.setFirstTime(false);
                    inventory.addItem(smallSafe);
                    inventory.addItem(piece1);
                    inventory.addItem(lockedNotebook);
                    inventory.addItem(redKey);
                    explorableObjects.addItem(smallSafe);
                    explorableObjects.addItem(piece1);
                    explorableObjects.addItem(lockedNotebook);
                    explorableObjects.addItem(redKey);
                }
            }
        }
        goBackToActionsScreen();
    }

//    EFFECTS: shows the details of the small safe when observed
    public void observeSmallSafe() {
        if (smallSafe.getIsLocked()) {
            System.out.println("The safe has three color wheels, each with 5 colors on it.");
            System.out.println("Seems that you'll need the arrow for each wheel to point to the right color.");
            System.out.println("You notice that above each wheel there's a letter. Respectively, they are: ");
            System.out.println("A J J");
            System.out.println("You can try interacting with the object to try unlocking it.");
        } else {
            System.out.println("You've already collected the item inside the safe.");
        }
        goBackToActionsScreen();
    }

    //    EFFECTS: shows the details of the desk when observed
    public void observeDesk() {
        System.out.println("A desk. You observe it a little more closely. Someone has scratched this onto the desk: ");
        System.out.println("J I M -> # # #");
        goBackToActionsScreen();
    }

    //    EFFECTS: shows the details of the painting when observed
    public void observePainting() {
        if (painting.getFilledState()) {
            System.out.println("You observe the painting. It seems to spell 'JOEY'");
        } else {
            System.out.print("The empty painting frame has outlines etched into it. It seems to outline ");
            System.out.println("three pieces. Try finding all three, then interact with the painting.");
        }
        goBackToActionsScreen();
    }

    //    EFFECTS: shows the details of a painting piece when observed
    public void observePaintingPiece() {
        System.out.println("A piece of a painting. You'll need the other two in your inventory as well.");
        System.out.println("Then, you should interact with the painting.");
        goBackToActionsScreen();
    }

    //    EFFECTS: shows the details of a key when observed
    public void observeKey() {
        System.out.println("A key...wonder where keyholes have been seen...");
        goBackToActionsScreen();
    }

    //    EFFECTS: shows the details of the notebook when it's unlocked and observed
    public void observeUnlockedNotebook() {
        System.out.println("Now that the notebook is unlocked, you flip through it for clues.");
        System.out.println("There's only a short paragraph written in it.");
        System.out.println("July 4, 2020.");
        System.out.println("Today Jim, Joey, and Annie were to choose baseballs outside to play with.");
        System.out.println("Each could pick their own color, but somehow both Jim and Joey liked the red one.");
        System.out.println("While they both fought for it, Annie dropped the green one she chose to help Joey");
        System.out.println("With the two of them, they managed to shove Jim to the ground.");
        System.out.println("But unfortunately, there was a sharp stick lying behind Jim. \n");
        System.out.println("The rest of the text is botched out. Wonder how we could possibly use this...");
        goBackToActionsScreen();
    }

    //    EFFECTS: shows the details of the notebook when it's locked and when observed
    public void observeLockedNotebook() {
        System.out.print("Seems like this notebook needs a key to unlock. You can try interacting with it ");
        System.out.print("to unlock it.");
        goBackToActionsScreen();
    }

    // EFFECTS: prints out what was found after interacting, or that it can't be interacted with
    public void interact() {
        boolean interacting = true;
        boolean found = false;
        while (interacting) {
            System.out.println("What object would you like to interact with (type name of object): ");
            for (Item i : explorableObjects.getExplorable()) {
                System.out.println(i.getName());
            }
            String interactInput = input.next();
            for (Item p : explorableObjects.getExplorable()) {
                if (p.getName().toLowerCase().equals(interactInput.toLowerCase())) {
                    interacting = false;
                    interactItemPart1(interactInput.toLowerCase());
                    found = true;
                }
            }
            if (!found) {
                System.out.println("Invalid object, or the object can't be observed right now. Please try again. \n");
            }
        }
    }

//    EFFECTS: checks if the item is interactable, and if it is, then method will start up helper methods
    public void interactItemPart1(String object) {
        List<String> notInteractable;
        List<String> notInteractableLowered = new ArrayList<>();
        notInteractable = new ArrayList<>(asList(greenKey.getName(), redKey.getName(), unlockedNotebook.getName(),
                piece1.getName(), piece2.getName()));
        notInteractable.add(piece3.getName());
        notInteractable.add(wall3.getName());
        notInteractable.add(wall2.getName());
        notInteractable.add(desk.getName());
        notInteractable.add(cabinet.getName());
        for (String i : notInteractable) {
            notInteractableLowered.add(i.toLowerCase());
        }
        if (notInteractableLowered.contains(object)) {
            System.out.println("No interaction available here. Try observing it or interacting with another object.");
            goBackToActionsScreen();
        } else {
            if (object.equals("wall 1")) {
                interactWall1();
                goBackToActionsScreen();
            } else {
                interactItemPart2(object);
            }
        }
    }

    //    EFFECTS: has helper methods so that interactions can be made to an object
    public void interactItemPart2(String object) {
        switch (object) {
            case "phone":
                interactPhone();
                goBackToActionsScreen();
            case "combination lock":
                interactCombinationLock();
                goBackToActionsScreen();
            case "door":
                interactDoor();
                goBackToActionsScreen();
            case "notebook (locked)":
                interactLockedNotebook();
                goBackToActionsScreen();
            case "small safe":
                interactSmallSafe();
                goBackToActionsScreen();
            case "painting":
                interactPainting();
                goBackToActionsScreen();
            default:
                System.out.println("Object not found...");
                goBackToActionsScreen();
        }
    }

//    MODIFIES: this
    //    EFFECTS: allows user to interact with wall 1
    public void interactWall1() {
        boolean unlocked = false;
        System.out.println("You notice the keyhole on the wall.");
        if (inventory.containsItem(redKey)) {
            unlocked = true;
            tryUnlockingWall1();
        }

        if (!unlocked) {
            System.out.println("Seems like you don't have the key though. Try to find it...");
        }
    }

    public void tryUnlockingWall1() {
        System.out.println("You seem to have a key. Do you want to try using it? \n[1] yes \n[2] no");
        boolean trying = true;
        while (trying) {
            String use = input.next();
            if (use.equals("1")) {
                successfulInteractWall1();
                trying = false;
            } else if (use.equals("2")) {
                System.out.println("Ok...");
                trying = false;
            } else {
                System.out.println("Invalid input, try again.");
            }
        }
    }

//    MODIFIES: this
//    EFFECTS: will add green key to inventory and show text to user
    public void successfulInteractWall1() {
        System.out.println("A part of the wall swings open and reveals a small hole.");
        System.out.println("Inside is a small green key, and a piece of painting with black paint on it.");
        System.out.println("You tuck them into your pocket.");
        System.out.println("Green key and Piece 2 have been added to your inventory and explorable items.");
        System.out.println("Red key has been removed from inventory and explorable items.");
        inventory.addItem(greenKey);
        inventory.addItem(piece2);
        redKey.setUsedState(true);
        inventory.removeItem(redKey);
        explorableObjects.addItem(greenKey);
        explorableObjects.addItem(piece2);
        explorableObjects.removeItem(redKey);
    }

//    MODIFIES: this
//    EFFECTS: allows user to interact with the phone and try to get correct phone number
    public void interactPhone() {
        System.out.println("Please type a ten digit phone number without dashes to call: ");
        boolean incorrectInput = true;
        int phoneNumber = 0;
        while (incorrectInput) {
            String number = input.next();
            if (number.length() != 10) {
                System.out.println("Invalid input, the number must be 10 digits long.");
            } else {
                try {
                    phoneNumber = Integer.parseInt(number);
                    incorrectInput = false;
                } catch (Exception e) {
                    System.out.println("The input must be a numerical input!");
                }
            }
        }
        if (phone.isCorrectPhoneNumber(phoneNumber)) {
            System.out.print("'BEEP: The painting tells only half the story. Take the first two letters there and ");
            System.out.println("the first two of their accomplice if you wish to escape.'\nThe phone then dies out.");
            phone.setCalled(true);
        } else {
            System.out.println("Nothing happens. Turns out that wasn't the right code.");
        }
    }

//    MODIFIES: this
//    EFFECTS: allows user to interact with the door and try to unlock
    public void interactDoor() {
        System.out.println("Please enter the password for the door: ");
        String password = input.next();
        if (door.tryUnlocking(password.toLowerCase())) {
            System.out.println("You type in the password...and the door creaks open!");
            System.out.println("As you peak outside, a bright light blinds you.");
            System.out.println("After your eyes adjust, you step outside onto...sand?!");
            System.out.println("It turns out you're in the middle of a desert...and this dry situation just became ");
            System.out.println("drier. TO BE CONTINUED.");
            System.out.println("Thank you for playing, and congratulations on escaping! For now. :) ");
            System.exit(0);
        } else {
            System.out.println("Nope, better keep trying. Can't be that easy, right?");
        }
    }

//    EFFECTS: allows user to interact with the combination lock and try to unlock
    public void interactCombinationLock() {
        System.out.println("You decide to give this combination lock a go.");
        System.out.println("Enter what number you want to first turn to. It should be between 1 and 55.");
        String firstNum = input.next();
        int firstNumber = Integer.parseInt(firstNum);
        System.out.println("Enter what number you want to turn to next. It should be between 1 and 55.");
        String secondNum = input.next();
        int secondNumber = Integer.parseInt(secondNum);
        System.out.println("Enter what the last number you want to turn to is. It should be between 1 and 55.");
        String thirdNum = input.next();
        int thirdNumber = Integer.parseInt(thirdNum);
        if (combinationLock.tryUnlocking(firstNumber, secondNumber, thirdNumber)) {
            System.out.println("The combination lock unlocks with a happy click. You can now observe the cabinet!");
            cabinet.setLocked(false);
        } else {
            System.out.println("Nice try, but that ain't it.");
        }
    }

//    MODIFIES: this
//    EFFECTS: allows user to interact with the small safe
    public void interactSmallSafe() {
        System.out.println("Let's see if this safe opens...type the number next to the color you want when prompted.");
        System.out.println("Color legend: \n[1] Green \n[2] Blue \n[3] Purple \n[4] Red \n[5] White");
        System.out.println("What should the first color wheel be turned to?");
        String firstCol = input.next();
        int firstColor = Integer.parseInt(firstCol);
        System.out.println("What should the second color wheel be turned to?");
        String secondCol = input.next();
        int secondColor = Integer.parseInt(secondCol);
        System.out.println("What should the third color wheel be turned to?");
        String thirdCol = input.next();
        int thirdColor = Integer.parseInt(thirdCol);
        if (smallSafe.tryUnlocking(firstColor, secondColor, thirdColor)) {
            System.out.println("The small safe door swings open! Inside is a piece of the painting.");
            System.out.println("Piece 3 has been added to your inventory and explorable items.");
            explorableObjects.addItem(piece3);
            inventory.addItem(piece3);
        } else {
            System.out.println("Nothing happened... maybe there are clue written somewhere?");
        }
    }

//    EFFECTS: allows the user to interact with the painting and see if it is all filled
    public void interactPainting() {
        System.out.println("Let's see if you have all the pieces...");
        boolean containsPiece1 = false;
        boolean containsPiece2 = false;
        boolean containsPiece3 = false;
        for (Item i : inventory.getInventory()) {
            switch (i.getName()) {
                case "Piece 1":
                    containsPiece1 = true;
                    break;
                case "Piece 2":
                    containsPiece2 = true;
                    break;
                case "Piece 3":
                    containsPiece3 = true;
                    break;
                default:
                    break;
            }
        }
        checkForAllPieces(containsPiece3, containsPiece1, containsPiece2);
    }

    public void checkForAllPieces(boolean containsPiece3, boolean containsPiece1, boolean containsPiece2) {
        if (containsPiece3 && containsPiece1 && containsPiece2) {
            System.out.println("You do! You piece all of them together and see that it shows the name 'Joey'.");
            System.out.println("Maybe that's the password?");
        } else {
            System.out.println("Sadly, you don't. Try to find all of them!");
        }
    }

//    EFFECTS: allows the user to interact with the locked notebook and try to unlock
    public void interactLockedNotebook() {
        boolean unlocked = false;
        System.out.println("This notebook needs a key. Do you have that key?");
        if (inventory.containsItem(greenKey)) {
            unlocked = true;
            tryUnlockingNotebook();
        }
        if (!unlocked) {
            System.out.println("Seems like you don't have the key though. Try to find it...");
        }
    }

//    EFFECTS: user can try to unlock the notebook.
    public void tryUnlockingNotebook() {
        System.out.println("You seem to have a key. Do you want to try using it? \n[1] yes \n[2] no");
        boolean trying = true;
        while (trying) {
            String use = input.next();
            if (use.equals("1")) {
                successfulInteractLockedNotebook();
                trying = false;
            } else if (use.equals("2")) {
                System.out.println("Ok...");
                trying = false;
            } else {
                System.out.println("Invalid input, try again.");
            }
        }
    }

//    MODIFIES: this
//    EFFECTS: adds new items to inventory and explorable objects upon successful unlocking
    public void successfulInteractLockedNotebook() {
        System.out.println("The notebook is now unlocked! Observe the new unlocked notebook object to read it!");
        System.out.println("Locked notebook and green key have been removed from inventory and explorable items.");
        System.out.println("Unlocked notebook has been added to inventory and explorable items.");
        inventory.addItem(unlockedNotebook);
        inventory.removeItem(greenKey);
        inventory.removeItem(lockedNotebook);
        explorableObjects.addItem(unlockedNotebook);
        explorableObjects.removeItem(greenKey);
        explorableObjects.removeItem(lockedNotebook);
    }

//    EFFECTS: clears the screen
    public static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
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
        goBackToActionsScreen();
    }

    // MODIFIES: this
    // EFFECTS: loads inventory from file
    private void loadEscapeRoom() {
        try {
            inventory = jsonReaderInventory.read();
            explorableObjects = jsonReader.read();
            System.out.println("Loaded escape room state from " + JSON_STORE);
            System.out.println("Loaded escape room state from " + JSON_STORE_INVENTORY);
            reassignVariablesPart1();
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
            System.out.println("Or unable to read from file: " + JSON_STORE_INVENTORY);
        }
        goBackToActionsScreen();
    }

    public void reassignVariablesPart1() {
        if (explorableObjects.containsItem(phone)) {
            phone = ((Phone)explorableObjects.getItem(phone));
        } else {
            phone = new Phone(false);
        }
        wall1 = ((Wall1)explorableObjects.getItem(wall1));
        wall2 = ((Wall2)explorableObjects.getItem(wall2));
        wall3 = ((Wall3)explorableObjects.getItem(wall3));
        if (explorableObjects.containsItem(cabinet)) {
            cabinet = ((Cabinet)explorableObjects.getItem(cabinet));
        } else {
            cabinet = new Cabinet(false, true, true);
        }
    }
}
