package persistence;

import model.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

// Code is referenced from and based off of https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
// This code was provided by course staff
public class EscapeRoomJsonTests {
    protected void checkItems(String name, Item item) {
        assertEquals(name, item.getName());
    }
}