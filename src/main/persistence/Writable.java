package persistence;

import org.json.JSONObject;

// Code is referenced from and based off of https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
// This code was provided by course staff

// Interface for the Json classes
public interface Writable {
    // EFFECTS: returns this as JSON object
    JSONObject toJson();
}
