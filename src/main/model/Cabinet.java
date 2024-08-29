package model;

import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;
import java.util.List;

// The Cabinet class represents a cabinet in the game
public class Cabinet implements Item {
    private String name = "Cabinet";
    private boolean isInteractable;
    private boolean isCollectable;
    private boolean isCombinable;
    private boolean isEmpty;
    private boolean isLocked;
    private boolean isFirstTime;

//    EFFECTS: constructs cabinet object with a full array of items (red key, piece of painting, small safe)
    public Cabinet(boolean isEmpty, boolean isLocked, boolean isFirstTime) {
        isInteractable = false;
        isCollectable = false;
        // cannot combine any items with this one
        isCombinable = false;
        this.isEmpty = isEmpty;
        this.isLocked = isLocked;
        this.isFirstTime = isFirstTime;
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
        json.put("isEmptyCabinet", isEmpty);
        json.put("isLockedCabinet", isLocked);
        json.put("isFirstTimeCabinet", isFirstTime);
        return json;
    }

    public boolean getIsCabinetEmpty() {
        return isEmpty;
    }

    public void setEmptyState(boolean isEmpty) {
        this.isEmpty = isEmpty;
    }

    public boolean getIsLocked() {
        return isLocked;
    }

    public void setLocked(boolean lockState) {
        isLocked = lockState;
    }

    public void setFirstTime(boolean firstTime) {
        isFirstTime = firstTime;
    }

    public boolean getFirstTime() {
        return isFirstTime;
    }
}
