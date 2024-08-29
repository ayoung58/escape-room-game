package model;

import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class EscapeRoomToJsonTests {
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
    JSONObject testObject;

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
        testObject = new JSONObject();
    }

    @Test
    public void cabinetToJsonTest() {
        JSONObject modelJson = cabinet.toJson();
        testObject.put("name", "Cabinet");
        testObject.put("isEmptyCabinet", false);
        testObject.put("isLockedCabinet", true);
        testObject.put("isFirstTimeCabinet", true);

        assertEquals(modelJson.get("name"), testObject.get("name"));
        assertEquals(modelJson.get("isEmptyCabinet"), testObject.get("isEmptyCabinet"));
        assertEquals(modelJson.get("isLockedCabinet"), testObject.get("isLockedCabinet"));
        assertEquals(modelJson.get("isFirstTimeCabinet"), testObject.get("isFirstTimeCabinet"));
    }

    @Test
    public void combinationLockToJsonTest() {
        JSONObject modelJson = combinationLock.toJson();
        testObject.put("name", "Combination lock");
        testObject.put("isLockedCombinationLock", true);

        assertEquals(modelJson.get("name"), testObject.get("name"));
        assertEquals(modelJson.get("isLockedCombinationLock"), testObject.get("isLockedCombinationLock"));
    }

    @Test
    public void greenKeyToJsonTest() {
        JSONObject modelJson = greenKey.toJson();
        testObject.put("name", "Green key");
        testObject.put("isUsedGreenKey", false);

        assertEquals(modelJson.get("name"), testObject.get("name"));
        assertEquals(modelJson.get("isUsedGreenKey"), testObject.get("isUsedGreenKey"));
    }
    @Test
    public void lockNotebookToJsonTest() {
        JSONObject modelJson = lockedNotebook.toJson();
        testObject.put("name", "Notebook (locked)");
        testObject.put("isLockedNotebook", true);

        assertEquals(modelJson.get("name"), testObject.get("name"));
        assertEquals(modelJson.get("isLockedNotebook"), testObject.get("isLockedNotebook"));
    }
    @Test
    public void redKeyToJsonTest() {
        JSONObject modelJson = redKey.toJson();
        testObject.put("name", "Red key");
        testObject.put("isUsedRedKey", false);

        assertEquals(modelJson.get("name"), testObject.get("name"));
        assertEquals(modelJson.get("isUsedRedKey"), testObject.get("isUsedRedKey"));
    }
    @Test
    public void phoneToJsonTest() {
        JSONObject modelJson = phone.toJson();
        testObject.put("name", "Phone");
        testObject.put("calledPhone", false);

        assertEquals(modelJson.get("name"), testObject.get("name"));
        assertEquals(modelJson.get("calledPhone"), testObject.get("calledPhone"));
    }
    @Test
    public void piece1ToJsonTest() {
        JSONObject modelJson = piece1.toJson();
        testObject.put("name", "Piece 1");

        assertEquals(modelJson.get("name"), testObject.get("name"));
    }
    @Test
    public void piece2ToJsonTest() {
        JSONObject modelJson = piece2.toJson();
        testObject.put("name", "Piece 2");

        assertEquals(modelJson.get("name"), testObject.get("name"));
    }
    @Test
    public void piece3ToJsonTest() {
        JSONObject modelJson = piece3.toJson();
        testObject.put("name", "Piece 3");

        assertEquals(modelJson.get("name"), testObject.get("name"));
    }
    @Test
    public void smallSafeToJsonTest() {
        JSONObject modelJson = smallSafe.toJson();
        testObject.put("name", "Small safe");
        testObject.put("isLockedSmallSafe", true);

        assertEquals(modelJson.get("name"), testObject.get("name"));
        assertEquals(modelJson.get("isLockedSmallSafe"), testObject.get("isLockedSmallSafe"));
    }
    @Test
    public void deskToJsonTest() {
        JSONObject modelJson = desk.toJson();
        testObject.put("name", "Desk");

        assertEquals(modelJson.get("name"), testObject.get("name"));
    }
    @Test
    public void wall2ToJsonTest() {
        JSONObject modelJson = wall2.toJson();
        testObject.put("name", "Wall 2");
        testObject.put("isFirstTimeWall2", true);

        assertEquals(modelJson.get("name"), testObject.get("name"));
        assertEquals(modelJson.get("isFirstTimeWall2"), testObject.get("isFirstTimeWall2"));
    }
    @Test
    public void wall3ToJsonTest() {
        JSONObject modelJson = wall3.toJson();
        testObject.put("name", "Wall 3");
        testObject.put("isFirstTimeWall3", true);

        assertEquals(modelJson.get("name"), testObject.get("name"));
        assertEquals(modelJson.get("isFirstTimeWall3"), testObject.get("isFirstTimeWall3"));
    }
    @Test
    public void wall1ToJsonTest() {
        JSONObject modelJson = wall1.toJson();
        testObject.put("name", "Wall 1");
        testObject.put("isFirstTimeWall1", true);
        testObject.put("isLockedWall1", true);

        assertEquals(modelJson.get("name"), testObject.get("name"));
        assertEquals(modelJson.get("isFirstTimeWall1"), testObject.get("isFirstTimeWall1"));
        assertEquals(modelJson.get("isLockedWall1"), testObject.get("isLockedWall1"));
    }
    @Test
    public void paintingToJsonTest() {
        JSONObject modelJson = painting.toJson();
        testObject.put("name", "Painting");
        testObject.put("isFilledPainting", false);

        assertEquals(modelJson.get("name"), testObject.get("name"));
        assertEquals(modelJson.get("isFilledPainting"), testObject.get("isFilledPainting"));
    }
    @Test
    public void unlockedNotebookToJsonTest() {
        JSONObject modelJson = unlockedNotebook.toJson();
        testObject.put("name", "Notebook (unlocked)");

        assertEquals(modelJson.get("name"), testObject.get("name"));
    }
    @Test
    public void doorToJsonTest() {
        JSONObject modelJson = door.toJson();
        testObject.put("name", "Door");
        testObject.put("isLockedDoor", true);

        assertEquals(modelJson.get("name"), testObject.get("name"));
        assertEquals(modelJson.get("isLockedDoor"), testObject.get("isLockedDoor"));
    }
}
