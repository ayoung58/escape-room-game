package persistence;

import model.*;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

// Code is referenced from and based off of https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
// This code was provided by course staff

// This class tests the JsonWriter and JsonWriterInventory classes
public class EscapeRoomJsonWriterTests extends EscapeRoomJsonTests{

    // Code is referenced from and based off of https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
    // This code was provided by course staff
    @Test
    void testWriterInvalidFile() {
        try {
            ExplorableObjects explorableObjects = new ExplorableObjects("Explorable");
            Inventory inventory = new Inventory("Inventory");
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            JsonWriterInventory writerInventory = new JsonWriterInventory("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    // Code is referenced from and based off of https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
    // This code was provided by course staff
    @Test
    void testWriterEmptyExplorable() {
        try {
            ExplorableObjects explorableObjects = new ExplorableObjects("Explorable");
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyExplorable.json");
            writer.open();
            writer.write(explorableObjects, "./data/testWriterEmptyExplorable.json");
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyExplorable.json");
            explorableObjects = reader.read();
            assertEquals("Explorable", explorableObjects.getName());
            assertEquals(0, explorableObjects.getExplorableSize());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    // Code is referenced from and based off of https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
    // This code was provided by course staff
    @Test
    void testWriterEmptyInventory() {
        try {
            Inventory inventory = new Inventory("Inventory");
            JsonWriterInventory writerInv = new JsonWriterInventory("./data/testWriterEmptyInventory.json");
            writerInv.open();
            writerInv.write(inventory, "./data/testWriterEmptyInventory.json");
            writerInv.close();

            JsonReaderInventory readerInventory = new JsonReaderInventory("./data/testWriterEmptyInventory.json");
            inventory = readerInventory.read();
            assertEquals("Inventory", inventory.getName());
            assertEquals(0, inventory.getInventorySize());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    // Code is referenced from and based off of https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
    // This code was provided by course staff
    @Test
    void testWriterGeneralExplorable() {
        Phone phone;
        Cabinet cabinet;
        try {
            ExplorableObjects explorableObjects = new ExplorableObjects("Explorable");
            explorableObjects.addItem(new Phone( false));
            explorableObjects.addItem(new Cabinet(false, true, true));
            JsonWriter writer = new JsonWriter("./data/testWriterGeneralExplorable.json");
            writer.open();
            writer.write(explorableObjects, "./data/testWriterGeneralExplorable.json");
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterGeneralExplorable.json");
            explorableObjects = reader.read();
            assertEquals("Explorable", explorableObjects.getName());
            List<Item> items = explorableObjects.getExplorable();
            assertEquals(2, items.size());
            checkItems("Phone", items.get(0));
            checkItems("Cabinet", items.get(1));
            assertFalse(((Phone)items.get(0)).getCalled());
            assertTrue(((Cabinet)items.get(1)).getIsLocked());
            assertFalse(((Cabinet)items.get(1)).getIsCabinetEmpty());
            assertTrue(((Cabinet)items.get(1)).getFirstTime());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    // Code is referenced from and based off of https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
    // This code was provided by course staff
    @Test
    void testWriterGeneralInventory() {
        try {
            Inventory inventory = new Inventory("Inventory");
            inventory.addItem(new Piece1());
            inventory.addItem(new SmallSafe(true));
            assertEquals(inventory.getInventorySize(), 2);
            JsonWriterInventory writerInv = new JsonWriterInventory("./data/testWriterGeneralInventory.json");
            writerInv.open();
            writerInv.write(inventory, "./data/testWriterGeneralInventory.json");
            writerInv.close();

            JsonReaderInventory readerInv = new JsonReaderInventory("./data/testWriterGeneralInventory.json");
            inventory = readerInv.read();
            assertEquals("Inventory", inventory.getName());
            List<Item> items = inventory.getInventory();
            assertEquals(2, items.size());
            checkItems("Piece 1", items.get(0));
            checkItems("Small safe", items.get(1));
            assertTrue(((SmallSafe)items.get(1)).getIsLocked());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }
}
