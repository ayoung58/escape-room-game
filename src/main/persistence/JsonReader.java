package persistence;

import model.Inventory;
import model.*;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;
import java.io.FileOutputStream;

import org.json.*;
// Code is referenced from and based off of https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
// This code was provided by course staff

// Represents a reader that reads workroom from JSON data stored in file
public class JsonReader {
    private String source;

    // Code is referenced from and based off of https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
    // This code was provided by course staff

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // Code is referenced from and based off of https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
    // This code was provided by course staff

    // EFFECTS: reads explorable items from file and returns it;
    // throws IOException if an error occurs reading data from file
    public ExplorableObjects read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseExplorable(jsonObject);
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

    // EFFECTS: parses explorable items from JSON object and returns it
    private ExplorableObjects parseExplorable(JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        ExplorableObjects explorableObjects = new ExplorableObjects(name);
        addSomeItems(explorableObjects, jsonObject);
        return explorableObjects;
    }

    // Code is referenced from and based off of https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
    // This code was provided by course staff

    // MODIFIES: explorableObjects
    // EFFECTS: parses item from JSON object and adds them to explorableObjects
    private void addSomeItems(ExplorableObjects explorableObjects, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("explorableStuff");
        for (Object json : jsonArray) {
            JSONObject nextItem = (JSONObject) json;
            addSomeItem(explorableObjects, nextItem);
        }
    }

    // Code is referenced from and based off of https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
    // This code was provided by course staff

    // MODIFIES: explorableObjects
    // EFFECTS: parses item from JSON object and adds it to inventory
    private void addSomeItem(ExplorableObjects explorableObjects, JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        switch (name) {
            case "Green key":
                GreenKey greenKey = new GreenKey(jsonObject.getBoolean("isUsedGreenKey"));
                explorableObjects.addItem(greenKey);
                break;
            case "Notebook (locked)":
                LockedNotebook lockedNotebook = new LockedNotebook(jsonObject.getBoolean("isLockedNotebook"));
                explorableObjects.addItem(lockedNotebook);
                break;
            case "Red key":
                RedKey redKey = new RedKey(jsonObject.getBoolean("isUsedRedKey"));
                explorableObjects.addItem(redKey);
                break;
            case "Phone":
                Phone phone = new Phone(jsonObject.getBoolean("calledPhone"));
                explorableObjects.addItem(phone);
                break;
            default:
                addSomeItemSecondPart(explorableObjects, jsonObject, name);
        }
    }

    // MODIFIES: explorableObjects
    // EFFECTS: parses item from JSON object and adds it to inventory
    private void addSomeItemSecondPart(ExplorableObjects explorableObjects, JSONObject jsonObject, String name) {
        switch (name) {
            case "Piece 1":
                Piece1 piece1 = new Piece1();
                explorableObjects.addItem(piece1);
                break;
            case "Piece 2":
                Piece2 piece2 = new Piece2();
                explorableObjects.addItem(piece2);
                break;
            case "Piece 3":
                Piece3 piece3 = new Piece3();
                explorableObjects.addItem(piece3);
                break;
            case "Small safe":
                SmallSafe smallSafe = new SmallSafe(jsonObject.getBoolean("isLockedSmallSafe"));
                explorableObjects.addItem(smallSafe);
                break;
            default:
                addSomeItemThirdPart(explorableObjects, jsonObject, name);
        }
    }

    // MODIFIES: explorableObjects
    // EFFECTS: parses item from JSON object and adds it to inventory
    public void addSomeItemThirdPart(ExplorableObjects explorableObjects, JSONObject jsonObject, String name) {
        switch (name) {
            case "Desk":
                Desk desk = new Desk();
                explorableObjects.addItem(desk);
                break;
            case "Wall 2":
                Wall2 wall2 = new Wall2(jsonObject.getBoolean("isFirstTimeWall2"));
                explorableObjects.addItem(wall2);
                break;
            case "Wall 3":
                Wall3 wall3 = new Wall3(jsonObject.getBoolean("isFirstTimeWall3"));
                explorableObjects.addItem(wall3);
                break;
            case "Painting":
                Painting painting = new Painting(jsonObject.getBoolean("isFilledPainting"));
                explorableObjects.addItem(painting);
                break;
            default:
                addSomeItemFourthPart(explorableObjects, jsonObject, name);
        }
    }

    // MODIFIES: explorableObjects
    // EFFECTS: parses item from JSON object and adds it to inventory
    public void addSomeItemFourthPart(ExplorableObjects explorableObjects, JSONObject jsonObject, String name) {
        switch (name) {
            case "Notebook (unlocked)":
                UnlockedNotebook unlockedNotebook = new UnlockedNotebook();
                explorableObjects.addItem(unlockedNotebook);
                break;
            case "Wall 1":
                Wall1 wall1 = new Wall1(jsonObject.getBoolean("isLockedWall1"),
                        jsonObject.getBoolean("isFirstTimeWall1"));
                explorableObjects.addItem(wall1);
                break;
            case "Cabinet":
                Cabinet cabinet = new Cabinet(jsonObject.getBoolean("isEmptyCabinet"),
                        jsonObject.getBoolean("isLockedCabinet"), jsonObject.getBoolean("isFirstTimeCabinet"));
                explorableObjects.addItem(cabinet);
                break;
            case "Combination lock":
                CombinationLock combinationLock = new CombinationLock(
                        jsonObject.getBoolean("isLockedCombinationLock"));
                explorableObjects.addItem(combinationLock);
                break;
            default:
                addSomeItemFifthPart(explorableObjects, jsonObject, name);
        }
    }

    // MODIFIES: explorableObjects
    // EFFECTS: parses item from JSON object and adds it to inventory
    public void addSomeItemFifthPart(ExplorableObjects explorableObjects, JSONObject jsonObject, String name) {
        switch (name) {
            case "Door":
                Door door = new Door(jsonObject.getBoolean("isLockedDoor"));
                explorableObjects.addItem(door);
                break;
            default:
                break;
        }
    }
}
