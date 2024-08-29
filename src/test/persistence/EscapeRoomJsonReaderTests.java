package persistence;
import org.junit.jupiter.api.Test;

import model.*;
import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

// Code is referenced from and based off of https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
// This code was provided by course staff

// This class tests the JsonReader and JsonRaderInventory classes
public class EscapeRoomJsonReaderTests extends EscapeRoomJsonTests {

    // Code is referenced from and based off of https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
    // This code was provided by course staff
    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        JsonReaderInventory readerInventory= new JsonReaderInventory("./data/noSuchFile.json");
        try {
            ExplorableObjects explorableObjects = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
        try {
            Inventory inventory = readerInventory.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    // Code is referenced from and based off of https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
    // This code was provided by course staff
    @Test
    void testReaderEmptyExplorableOrInventory() {
        JsonReader reader = new JsonReader("./data/testReaderEmptyExplorable.json");
        JsonReaderInventory readerInventory = new JsonReaderInventory("./data/testReaderEmptyInventory.json");
        try {
            ExplorableObjects explorableObjects = reader.read();
            assertEquals("Explorable", explorableObjects.getName());
            assertEquals(0, explorableObjects.getExplorableSize());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
        try {
            Inventory inventory = readerInventory.read();
            assertEquals("Inventory", inventory.getName());
            assertEquals(0, inventory.getInventorySize());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    // Code is referenced from and based off of https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
    // This code was provided by course staff
    @Test
    void testReaderGeneralExplorable() {
        JsonReader reader = new JsonReader("./data/testReaderGeneralExplorable.json");
        try {
            ExplorableObjects explorableObjects = reader.read();
            assertEquals("Explorable", explorableObjects.getName());
            List<Item> items = explorableObjects.getExplorable();
            assertEquals(17, items.size());
            checkItems("Green key", items.get(0));
            checkItems("Notebook (locked)", items.get(1));
            checkItems("Red key", items.get(2));
            checkItems("Phone", items.get(3));
            checkItems("Piece 1", items.get(4));
            checkItems("Piece 2", items.get(5));
            checkItems("Piece 3", items.get(6));
            checkItems("Small safe", items.get(7));
            checkItems("Desk", items.get(8));
            checkItems("Wall 2", items.get(9));
            checkItems("Wall 3", items.get(10));
            checkItems("Painting", items.get(11));
            checkItems("Notebook (unlocked)", items.get(12));
            checkItems("Wall 1", items.get(13));
            checkItems("Cabinet", items.get(14));
            checkItems("Combination lock", items.get(15));
            checkItems("Door", items.get(16));
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    // Code is referenced from and based off of https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
    // This code was provided by course staff
    @Test
    void testReaderGeneralInventory() {
        JsonReaderInventory readerInventory = new JsonReaderInventory("./data/testReaderGeneralInventory.json");
        try {
            Inventory inventory = readerInventory.read();
            assertEquals("Inventory", inventory.getName());
            List<Item> items = inventory.getInventory();
            assertEquals(7, items.size());
            checkItems("Notebook (unlocked)", items.get(0));
            checkItems("Red key", items.get(1));
            assertFalse(((RedKey)items.get(1)).isUsed());
            checkItems("Green key", items.get(2));
            assertFalse(((GreenKey)items.get(2)).isUsed());
            checkItems("Notebook (locked)", items.get(3));
            assertTrue(((LockedNotebook)items.get(3)).getIsLocked());
            checkItems("Piece 1", items.get(4));
            checkItems("Piece 2", items.get(5));
            checkItems("Piece 3", items.get(6));
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }
}
