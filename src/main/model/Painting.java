package model;

import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;
import java.util.List;

// The Painting class represents a painting in the game, can be filled if all painting pieces in inventory
public class Painting implements Item {
    private String name = "Painting";
    private boolean isInteractable;
    private boolean isCollectable;
    private boolean isCombinable;
    private boolean isFilled;

    //    EFFECTS: constructs a painting object and assigns appropriate fields to instantiate
    public Painting(boolean isFilled) {
        isInteractable = true;
        isCollectable = false;
        // can combine with the pieces
        isCombinable = true;
        this.isFilled = isFilled;
    }

    @Override
    public boolean getInteractableStatus() {
        return isInteractable; // stub
    }

    @Override
    public boolean getCollectableStatus() {
        return isCollectable; // stub
    }

    @Override
    public boolean getCombinableStatus() {
        return isCombinable; // stub
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
        json.put("isFilledPainting", isFilled);
        return json;
    }

    public boolean getFilledState() {
        return isFilled; // stub
    }

    public void setFilledState(boolean filled) {
        isFilled = filled;
    }
}
