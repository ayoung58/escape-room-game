package model;

import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class EscapeRoomModelTests {
    JSONObject testObject;
    Cabinet cabinet;
    CombinationLock combinationLock;
    Desk desk;
    Door door;
    GreenKey greenKey;
    Inventory inventory;
    LockedNotebook lockedNotebook;
    Painting painting;
    Phone phone;
    RedKey redKey;
    SmallSafe smallSafe;
    Wall1 wall1;
    Wall2 wall2;
    Wall3 wall3;
    Piece1 piece1;
    Piece2 piece2;
    Piece3 piece3;
    UnlockedNotebook unlockedNotebook;
    ExplorableObjects explorableObjects;

    @BeforeEach
    public void setup() {
        cabinet = new Cabinet(false, true, true);
        combinationLock = new CombinationLock(true);
        desk = new Desk();
        door = new Door(true);
        greenKey = new GreenKey(false);
        inventory = new Inventory("inventory");
        lockedNotebook = new LockedNotebook(true);
        painting = new Painting(false);
        phone = new Phone(false);
        redKey = new RedKey(false);
        smallSafe = new SmallSafe(true);
        wall1 = new Wall1(true, true);
        wall2 = new Wall2(true);
        wall3 = new Wall3(true);
        piece1 = new Piece1();
        piece2 = new Piece2();
        piece3 = new Piece3();
        unlockedNotebook = new UnlockedNotebook();
        explorableObjects = new ExplorableObjects("Explorable");
        testObject = new JSONObject();
    }

    @Test
    public void constructorTests(){
        assertTrue(cabinet.getIsLocked());
        assertFalse(cabinet.getIsCabinetEmpty());
        assertTrue(combinationLock.getIsLocked());
        assertTrue(door.getIsLocked());
        assertFalse(greenKey.isUsed());
        assertTrue(lockedNotebook.getIsLocked());
        assertFalse(painting.getFilledState());
        assertFalse(phone.getCalled());
        assertFalse(redKey.isUsed());
        assertTrue(smallSafe.getIsLocked());
        assertTrue(wall1.getIsLocked());
        assertEquals(inventory.getName(), "inventory");
    }

    @Test
    public void tryUnlockingCombinationLockTest() {
        int firstCode = combinationLock.getPasscodeFirstCode();
        int secondCode = combinationLock.getPasscodeSecondCode();
        int thirdCode = combinationLock.getPasscodeThirdCode();
        assertFalse(combinationLock.tryUnlocking(firstCode, firstCode, firstCode));
        assertFalse(combinationLock.tryUnlocking(firstCode, secondCode, 10));
        assertFalse(combinationLock.tryUnlocking(firstCode + 1, secondCode - 1, thirdCode + 1));
        assertFalse(combinationLock.tryUnlocking(secondCode, thirdCode, firstCode));
        assertFalse(combinationLock.tryUnlocking(secondCode, secondCode - 1, thirdCode));
        assertTrue(combinationLock.tryUnlocking(firstCode, secondCode, thirdCode));
    }

    @Test
    public void tryUnlockingDoorTest() {
        String password = door.getPassword();
        assertFalse(door.tryUnlocking("random"));
        assertTrue(door.tryUnlocking(password));
    }

    @Test
    public void addAndRemoveInventoryTest() {
        List<Item> inventoryCorrect = new ArrayList<>();
        assertEquals(inventory.getInventorySize(), 0);
        inventory.addItem(greenKey);
        inventoryCorrect.add(greenKey);
        assertEquals(inventory.getInventorySize(), 1);
        assertEquals(inventory.getInventory(), inventoryCorrect);
        inventory.addItem(piece2);
        inventoryCorrect.add(piece2);
        assertEquals(inventory.getInventorySize(), 2);
        inventory.addItem(piece2);
        assertEquals(inventory.getInventorySize(), 2);
        assertEquals(inventory.getInventory(), inventoryCorrect);
        inventory.removeItem(greenKey);
        inventoryCorrect.remove(greenKey);
        inventory.removeItem(piece1);
        assertEquals(inventory.getInventory(), inventoryCorrect);
        assertEquals(inventory.getInventorySize(), 1);
        inventory.removeItem(greenKey);
        assertEquals(inventory.getInventory(), inventoryCorrect);
        assertEquals(inventory.getInventorySize(), 1);
        inventory.addItem(desk);
        assertEquals(inventory.getInventory(), inventoryCorrect);
        assertEquals(inventory.getInventorySize(), 1);
    }

    @Test
    public void checkPhoneNumberTest() {
        int correctPhoneNumber = phone.getPhoneNumber();
        assertFalse(phone.isCorrectPhoneNumber(1029304951));
        assertFalse(phone.isCorrectPhoneNumber(correctPhoneNumber - 1));
        assertTrue(phone.isCorrectPhoneNumber(correctPhoneNumber));
    }


    @Test
    public void tryUnlockingSmallSafe() {
        // correct combination: 1 4 4
        // all wrong
        assertFalse(smallSafe.tryUnlocking(1, 3,3));
        // 1 right
        assertFalse(smallSafe.tryUnlocking(1, 1,1));
        assertFalse(smallSafe.tryUnlocking(4, 3,4));
        assertFalse(smallSafe.tryUnlocking(4, 4,2));
        // 2 right
        assertFalse(smallSafe.tryUnlocking(1, 3,4));
        assertFalse(smallSafe.tryUnlocking(1, 4,2));
        assertFalse(smallSafe.tryUnlocking(2, 4,4));
        // all correct
        assertTrue(smallSafe.tryUnlocking(1, 4,4));
    }

    @Test
    public void addAndRemoveItemsWallsTest() {
        assertEquals(wall1.getNumberOfItems(), 0);
        assertEquals(wall2.getNumberOfItems(), 0);
        assertEquals(wall3.getNumberOfItems(), 0);
        wall1.addItems(cabinet);
        assertEquals(wall1.getNumberOfItems(), 1);
        assertEquals(wall2.getNumberOfItems(), 0);
        wall2.addItems(door);
        wall2.addItems(smallSafe);
        assertEquals(wall1.getNumberOfItems(), 1);
        assertEquals(wall2.getNumberOfItems(), 2);
        assertEquals(wall3.getNumberOfItems(), 0);
        wall3.addItems(lockedNotebook);
        assertEquals(wall3.getNumberOfItems(), 1);
        wall1.removeItems(cabinet);
        // should not work since it's not in the array
        wall2.removeItems(cabinet);
        wall2.removeItems(smallSafe);
        wall3.removeItems(lockedNotebook);
        assertEquals(wall3.getNumberOfItems(), 0);
        assertEquals(wall1.getNumberOfItems(), 0);
        assertEquals(wall2.getNumberOfItems(), 1);
    }

    @Test
    public void addRemoveDupWall3Tests() {
        wall3.addItems(cabinet);
        assertEquals(wall3.getNumberOfItems(), 1);
        wall3.addItems(cabinet);
        assertEquals(wall3.getNumberOfItems(), 1);
        wall3.addItems(redKey);
        assertEquals(wall3.getNumberOfItems(), 2);
        wall3.removeItems(cabinet);
        assertEquals(wall3.getNumberOfItems(), 1);
        wall3.removeItems(cabinet);
        assertEquals(wall3.getNumberOfItems(), 1);
    }

    @Test
    public void addRemoveDupWall1Tests() {
        wall1.addItems(cabinet);
        assertEquals(wall1.getNumberOfItems(), 1);
        wall1.addItems(cabinet);
        assertEquals(wall1.getNumberOfItems(), 1);
        wall1.addItems(redKey);
        assertEquals(wall1.getNumberOfItems(), 2);
        wall1.removeItems(cabinet);
        assertEquals(wall1.getNumberOfItems(), 1);
        wall1.removeItems(cabinet);
        assertEquals(wall1.getNumberOfItems(), 1);
    }

    @Test
    public void addRemoveDupWall2Tests() {
        wall2.addItems(cabinet);
        assertEquals(wall2.getNumberOfItems(), 1);
        wall2.addItems(cabinet);
        assertEquals(wall2.getNumberOfItems(), 1);
        wall2.addItems(redKey);
        assertEquals(wall2.getNumberOfItems(), 2);
        wall2.removeItems(cabinet);
        assertEquals(wall2.getNumberOfItems(), 1);
        wall2.removeItems(cabinet);
        assertEquals(wall2.getNumberOfItems(), 1);
    }

    @Test
    public void isInteractableTests() {
        assertFalse(cabinet.getInteractableStatus());
        assertTrue(combinationLock.getInteractableStatus());
        assertFalse(desk.getInteractableStatus());
        assertTrue(door.getInteractableStatus());
        assertFalse(greenKey.getInteractableStatus());
        assertTrue(lockedNotebook.getInteractableStatus());
        assertTrue(painting.getInteractableStatus());
        assertTrue(phone.getInteractableStatus());
        assertFalse(piece1.getInteractableStatus());
        assertFalse(piece2.getInteractableStatus());
        assertFalse(piece3.getInteractableStatus());
        assertFalse(redKey.getInteractableStatus());
        assertTrue(smallSafe.getInteractableStatus());
        assertFalse(unlockedNotebook.getInteractableStatus());
    }

    @Test
    public void isCollectableTests() {
        assertFalse(cabinet.getCollectableStatus());
        assertFalse(combinationLock.getCollectableStatus());
        assertFalse(desk.getCollectableStatus());
        assertFalse(door.getCollectableStatus());
        assertTrue(greenKey.getCollectableStatus());
        assertTrue(lockedNotebook.getCollectableStatus());
        assertFalse(painting.getCollectableStatus());
        assertFalse(phone.getCollectableStatus());
        assertTrue(piece1.getCollectableStatus());
        assertTrue(piece2.getCollectableStatus());
        assertTrue(piece3.getCollectableStatus());
        assertTrue(redKey.getCollectableStatus());
        assertTrue(smallSafe.getCollectableStatus());
        assertTrue(unlockedNotebook.getCollectableStatus());
    }

    @Test
    public void isCombinableTests() {
        assertFalse(cabinet.getCombinableStatus());
        assertFalse(combinationLock.getCombinableStatus());
        assertFalse(desk.getCombinableStatus());
        assertFalse(door.getCombinableStatus());
        assertTrue(greenKey.getCombinableStatus());
        assertTrue(lockedNotebook.getCombinableStatus());
        assertTrue(painting.getCombinableStatus());
        assertFalse(phone.getCombinableStatus());
        assertTrue(piece1.getCombinableStatus());
        assertTrue(piece2.getCombinableStatus());
        assertTrue(piece3.getCombinableStatus());
        assertTrue(redKey.getCombinableStatus());
        assertFalse(smallSafe.getCombinableStatus());
        assertFalse(unlockedNotebook.getCombinableStatus());
    }

    @Test
    public void getNameTests() {
        assertEquals(cabinet.getName(), "Cabinet");
        assertEquals(combinationLock.getName(), "Combination lock");
        assertEquals(desk.getName(), "Desk");
        assertEquals(door.getName(), "Door");
        assertEquals(greenKey.getName(), "Green key");
        assertEquals(lockedNotebook.getName(), "Notebook (locked)");
        assertEquals(painting.getName(), "Painting");
        assertEquals(phone.getName(), "Phone");
        assertEquals(piece3.getName(), "Piece 3");
        assertEquals(piece2.getName(), "Piece 2");
        assertEquals(piece1.getName(), "Piece 1");
        assertEquals(redKey.getName(), "Red key");
        assertEquals(smallSafe.getName(), "Small safe");
        assertEquals(unlockedNotebook.getName(), "Notebook (unlocked)");
        assertEquals(wall3.getName(), "Wall 3");
        assertEquals(wall2.getName(), "Wall 2");
        assertEquals(wall1.getName(), "Wall 1");
    }

    @Test
    public void cabinetEmptyAndLockedTests() {
        assertFalse(cabinet.getIsCabinetEmpty());
        cabinet.setEmptyState(true);
        assertTrue(cabinet.getIsCabinetEmpty());
        cabinet.setEmptyState(false);
        assertFalse(cabinet.getIsCabinetEmpty());
        cabinet.setLocked(false);
        assertFalse(cabinet.getIsLocked());
        cabinet.setLocked(true);
        assertTrue(cabinet.getIsLocked());
    }

    @Test
    public void combinationLockTest() {
        assertEquals(combinationLock.getPasscodeFirstCode(), 10);
        assertEquals(combinationLock.getPasscodeSecondCode(), 9);
        assertEquals(combinationLock.getPasscodeThirdCode(), 13);
        assertTrue(combinationLock.getIsLocked());
        combinationLock.tryUnlocking(10, 9, 13);
        assertFalse(combinationLock.getIsLocked());
    }

    @Test
    public void doorAdditionalTests() {
        assertEquals(door.getPassword(), "joan");
        assertTrue(door.getIsLocked());
        door.tryUnlocking("joan");
        assertFalse(door.getIsLocked());
    }

    @Test
    public void keysAdditionalTests() {
        assertFalse(redKey.isUsed());
        redKey.setUsedState(true);
        assertTrue(redKey.isUsed());
        assertFalse(greenKey.isUsed());
        greenKey.setUsedState(true);
        assertTrue(greenKey.isUsed());
    }

    @Test
    public void lockedNotebookAdditionalTests() {
        assertTrue(lockedNotebook.getIsLocked());
        lockedNotebook.setIsLocked(false);
        assertFalse(lockedNotebook.getIsLocked());
    }

    @Test
    public void paintingAdditionalTests() {
        assertFalse(painting.getFilledState());
        painting.setFilledState(true);
        assertTrue(painting.getFilledState());
    }

    @Test
    public void phoneNumberCheckTest() {
        assertEquals(phone.getPhoneNumber(), 1115059047);
        assertFalse(phone.getCalled());
        phone.setCalled(true);
        assertTrue(phone.getCalled());
    }

    @Test
    public void getColorsSmallSafeTests() {
        assertEquals(smallSafe.getFirstColor(), 1);
        assertEquals(smallSafe.getSecondColor(), 4);
        assertEquals(smallSafe.getThirdColor(), 4);
        assertTrue(smallSafe.getIsLocked());
        smallSafe.setIsLocked(false);
        assertFalse(smallSafe.getIsLocked());
    }

    @Test
    public void lockWall1Tests() {
        assertTrue(wall1.getIsLocked());
        wall1.setLocked(false);
        assertFalse(wall1.getIsLocked());
    }

    @Test
    public void getListOfItemsWall1Tests() {
        List<Item> wall1Items = new ArrayList<>();
        assertEquals(wall1.getItems(), wall1Items);
        wall1Items.add(greenKey);
        wall1.addItems(greenKey);
        assertEquals(wall1.getItems(), wall1Items);
        wall1Items.add(piece1);
        wall1.addItems(piece1);
        assertEquals(wall1.getItems(), wall1Items);
        wall1Items.remove(piece1);
        wall1.removeItems(piece1);
        assertEquals(wall1.getItems(), wall1Items);
    }

    @Test
    public void getListOfItemsWall2Tests() {
        List<Item> wall2Items = new ArrayList<>();
        assertEquals(wall2.getItems(), wall2Items);
        wall2Items.add(greenKey);
        wall2.addItems(greenKey);
        assertEquals(wall2.getItems(), wall2Items);
        wall2Items.add(piece1);
        wall2.addItems(piece1);
        assertEquals(wall2.getItems(), wall2Items);
        wall2Items.remove(piece1);
        wall2.removeItems(piece1);
        assertEquals(wall2.getItems(), wall2Items);
    }

    @Test
    public void getListOfItemsWall3Tests() {
        List<Item> wall3Items = new ArrayList<>();
        assertEquals(wall3.getItems(), wall3Items);
        wall3Items.add(greenKey);
        wall3.addItems(greenKey);
        assertEquals(wall3.getItems(), wall3Items);
        wall3Items.add(piece1);
        wall3.addItems(piece1);
        assertEquals(wall3.getItems(), wall3Items);
        wall3Items.remove(piece1);
        wall3.removeItems(piece1);
        assertEquals(wall3.getItems(), wall3Items);
    }

    // NEW TESTS ADDED FOR PHASE 2

    @Test
    public void setFirstTimeCabinetTest() {
        assertTrue(cabinet.getFirstTime());
        cabinet.setFirstTime(false);
        assertFalse(cabinet.getFirstTime());
        cabinet.setFirstTime(true);
        assertTrue(cabinet.getFirstTime());
    }

    @Test
    public void containsItemInventoryTest() {
        inventory.addItem(piece1);
        inventory.addItem(piece2);
        inventory.addItem(piece3);
        inventory.addItem(redKey);
        assertTrue(inventory.containsItem(piece1));
        assertTrue(inventory.containsItem(piece2));
        assertTrue(inventory.containsItem(piece3));
        assertTrue(inventory.containsItem(redKey));
        assertFalse(inventory.containsItem(smallSafe));
        assertFalse(inventory.containsItem(lockedNotebook));
    }

    @Test
    public void wall3AdditionalStatusTests() {
        // testing the setting and getting of first time status
        assertTrue(wall3.getFirstTime());
        wall3.setFirstTime(false);
        assertFalse(wall3.getFirstTime());
        wall3.setFirstTime(true);
        assertTrue(wall3.getFirstTime());

        assertFalse(wall3.getCollectableStatus());
        assertFalse(wall3.getInteractableStatus());
        assertFalse(wall3.getCombinableStatus());
    }

    @Test
    public void wall2AdditionalStatusTests() {
        // testing the setting and getting of first time status
        assertTrue(wall2.getFirstTime());
        wall2.setFirstTime(false);
        assertFalse(wall2.getFirstTime());
        wall2.setFirstTime(true);
        assertTrue(wall2.getFirstTime());

        assertFalse(wall2.getCollectableStatus());
        assertFalse(wall2.getInteractableStatus());
        assertFalse(wall2.getCombinableStatus());
    }

    @Test
    public void wall1AdditionalStatusTests() {
        // testing the setting and getting of first time status
        assertTrue(wall1.getFirstTime());
        wall1.setFirstTime(false);
        assertFalse(wall1.getFirstTime());
        wall1.setFirstTime(true);
        assertTrue(wall1.getFirstTime());

        assertFalse(wall1.getCollectableStatus());
        assertFalse(wall1.getInteractableStatus());
        assertFalse(wall1.getCombinableStatus());
    }

    // Testing new class that was added
    @Test
    public void explorableObjectsTests() {
        // adding items
        explorableObjects.addItem(piece1);
        explorableObjects.addItem(piece2);
        explorableObjects.addItem(desk);
        explorableObjects.addItem(cabinet);
        explorableObjects.addItem(greenKey);
        assertEquals(explorableObjects.getExplorableSize(), 5);
        explorableObjects.addItem(greenKey);
        assertEquals(explorableObjects.getExplorableSize(), 5);

        // removing items and checking containment
        assertTrue(explorableObjects.containsItem(piece1));
        explorableObjects.removeItem(piece1);
        assertFalse(explorableObjects.containsItem(piece1));
        assertEquals(explorableObjects.getExplorableSize(), 4);
        explorableObjects.removeItem(piece1);
        assertEquals(explorableObjects.getExplorableSize(), 4);
        explorableObjects.removeItem(redKey);
        assertEquals(explorableObjects.getExplorableSize(), 4);
        assertTrue(explorableObjects.containsItem(desk));

        // getting an item
        assertEquals(explorableObjects.getItem(desk), desk);
        assertEquals(explorableObjects.getItem(cabinet), cabinet);
        assertEquals(null, explorableObjects.getItem(redKey));
    }
}