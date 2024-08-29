package model;

import org.json.JSONObject;
import persistence.Writable;

// The Desk class represents a desk in the game
public class Desk implements Item {
    private String name = "Desk";
    private boolean isInteractable;
    private boolean isCollectable;
    private boolean isCombinable;

//    EFFECTS: constructs a desk object with appropriate fields
    public Desk() {
        isInteractable = false;
        isCollectable = false;
        // cannot combine any items with this one
        isCombinable = false;
    }

    @Override
    public boolean getInteractableStatus() {
        return isInteractable;
    }

    @Override
    public boolean getCollectableStatus() {
        return isCollectable;
    }

    @Override
    public boolean getCombinableStatus() {
        return isCombinable;
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
        return json;
    }
}
