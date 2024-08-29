package model;

import org.json.JSONObject;
import persistence.Writable;

// The UnlockedNotebook class represents the notebook in the game when it is unlocked with a key
public class UnlockedNotebook implements Item {
    private String name = "Notebook (unlocked)";
    private boolean isInteractable;
    private boolean isCollectable;
    private boolean isCombinable;

    //    EFFECTS: constructs an unlockedNotebook object and assigns appropriate fields to instantiate
    public UnlockedNotebook() {
        isInteractable = false;
        isCollectable = true;
        isCombinable = false;
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

    @Override
    public boolean getInteractableStatus() {
        return isInteractable; //stub
    }

    @Override
    public boolean getCollectableStatus() {
        return isCollectable; //stub
    }

    @Override
    public boolean getCombinableStatus() {
        return isCombinable; // stub
    }

    @Override
    public String getName() {
        return name;
    }

}
