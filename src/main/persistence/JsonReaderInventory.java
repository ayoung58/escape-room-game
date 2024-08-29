package persistence;

import model.Inventory;
import model.*;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

import org.json.*;
// Code is referenced from and based off of https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
// This code was provided by course staff

// Represents a reader that reads workroom from JSON data stored in file
public class JsonReaderInventory {
    private String source;

    // Code is referenced from and based off of https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
    // This code was provided by course staff

    // EFFECTS: constructs reader to read from source file
    public JsonReaderInventory(String source) {
        this.source = source;
    }

    // Code is referenced from and based off of https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
    // This code was provided by course staff

    // EFFECTS: reads inventory from file and returns it;
    // throws IOException if an error occurs reading data from file
    public Inventory read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseInventory(jsonObject);
    }

    // Code is referenced from and based off of https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
    // This code was provided by course staff

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // Code is referenced from and based off of https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
    // This code was provided by course staff

    // EFFECTS: parses inventory from JSON object and returns it
    private Inventory parseInventory(JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        Inventory inventory = new Inventory(name);
        addSomeItems(inventory, jsonObject);
        return inventory;
    }

    // Code is referenced from and based off of https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
    // This code was provided by course staff

    // MODIFIES: inventory
    // EFFECTS: parses item from JSON object and adds them to inventory
    private void addSomeItems(Inventory inventory, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("inventoryStuff");
        for (Object json : jsonArray) {
            JSONObject nextItem = (JSONObject) json;
            addSomeItem(inventory, nextItem);
        }
    }

    // Code is referenced from and based off of https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
    // This code was provided by course staff

    // MODIFIES: inventory
    // EFFECTS: parses item from JSON object and adds it to inventory
    private void addSomeItem(Inventory inventory, JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        switch (name) {
            case "Green key":
                GreenKey greenKey = new GreenKey(jsonObject.getBoolean("isUsedGreenKey"));
                inventory.addItem(greenKey);
                break;
            case "Notebook (locked)":
                LockedNotebook lockedNotebook = new LockedNotebook(jsonObject.getBoolean("isLockedNotebook"));
                inventory.addItem(lockedNotebook);
                break;
            case "Red key":
                RedKey redKey = new RedKey(jsonObject.getBoolean("isUsedRedKey"));
                inventory.addItem(redKey);
                break;
            case "Piece 1":
                Piece1 piece1 = new Piece1();
                inventory.addItem(piece1);
                break;
            default:
                addSomeItemSecondPart(inventory, jsonObject, name);
        }
    }

    // MODIFIES: inventory
    // EFFECTS: parses item from JSON object and adds it to inventory
    private void addSomeItemSecondPart(Inventory inventory, JSONObject jsonObject, String name) {
        switch (name) {
            case "Piece 2":
                Piece2 piece2 = new Piece2();
                inventory.addItem(piece2);
                break;
            case "Piece 3":
                Piece3 piece3 = new Piece3();
                inventory.addItem(piece3);
                break;
            case "Small safe":
                SmallSafe smallSafe = new SmallSafe(jsonObject.getBoolean("isLockedSmallSafe"));
                inventory.addItem(smallSafe);
                break;
            case "Notebook (unlocked)":
                UnlockedNotebook unlockedNotebook = new UnlockedNotebook();
                inventory.addItem(unlockedNotebook);
                break;
            default:
                break;
        }
    }
}
