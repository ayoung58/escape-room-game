package persistence;

import model.*;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.*;

// Code is referenced from and based off of https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
// This code was provided by course staff

// Represents a writer that writes JSON representation of the escape to file
public class JsonWriterInventory {
    private static final int TAB = 4;
    private PrintWriter writer;
    private String destination;

    // Code is referenced from and based off of https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
    // This code was provided by course staff

    // EFFECTS: constructs writer to write to a given destination file
    public JsonWriterInventory(String destination) {
        this.destination = destination;
    }

    // Code is referenced from and based off of https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
    // This code was provided by course staff

    // MODIFIES: this
    // EFFECTS: opens writer; throws FileNotFoundException if destination file cannot
    // be opened for writing
    public void open() throws FileNotFoundException {
        writer = new PrintWriter(new File(destination));
    }

    // Code is referenced from and based off of https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
    // This code was provided by course staff

    // MODIFIES: this
    // EFFECTS: writes JSON representation of escape room inventory and other items to file
    public void write(Inventory inventory, String path) throws FileNotFoundException, IOException {
        new FileOutputStream(path).close();
        JSONObject jsonInventory = inventory.toJson();
        saveToFile(jsonInventory.toString(TAB));
    }

    // Code is referenced from and based off of https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
    // This code was provided by course staff

    // MODIFIES: this
    // EFFECTS: closes writer
    public void close() {
        writer.close();
    }

    // Code is referenced from and based off of https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
    // This code was provided by course staff

    // MODIFIES: this
    // EFFECTS: writes string to file
    private void saveToFile(String json) {
        writer.print(json);
    }
}
