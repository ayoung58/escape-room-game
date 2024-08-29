package model;

import org.json.JSONObject;
import persistence.Writable;

// The GreenKey class represents a green key in the game, can be used to unlock a specific object
public class GreenKey extends Keys {
    private String name = "Green key";
    private boolean isUsed;

//    EFFECTS: constructs a greenKey object and assigns appropriate fields
    public GreenKey(boolean isUsed) {
        this.isUsed = isUsed;
    }

    @Override
    public boolean isUsed() {
        return isUsed;
    }

    @Override
    public void setUsedState(boolean isUsed) {
        this.isUsed = isUsed;
    }

    @Override
    public String getName() {
        return name;
    }

    // Code is referenced from and based off of https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
    // This code was provided by course staff

    // EFFECTS: takes the object and puts specific fields to save into JSON format and returns it
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("isUsedGreenKey", isUsed);
        return json;
    }
}
