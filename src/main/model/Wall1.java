package model;

import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;
import java.util.List;

// The Wall1 class represents one of the walls in the game
public class Wall1 extends LockableItems {
    private String name = "Wall 1";
    private boolean isLocked;
    private boolean isInteractable;
    private boolean isCollectable;
    private boolean isCombinable;
    private boolean isFirstTime;
    private List<Item> items = new ArrayList<Item>();

//    EFFECTS: constructs a new wall
    public Wall1(boolean isLocked, boolean isFirstTime) {
        isInteractable = true;
        isCollectable = false;
        isCombinable = true;
        this.isLocked = isLocked;
        this.isFirstTime = isFirstTime;
    }

    @Override
    public boolean getInteractableStatus() {
        return false;
    }

    @Override
    public boolean getCollectableStatus() {
        return false;
    }

    @Override
    public boolean getCombinableStatus() {
        return false;
    }

    // Code is referenced from and based off of https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
    // This code was provided by course staff

    // EFFECTS: takes the object and puts specific fields to save into JSON format and returns it
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("isLockedWall1", isLocked);
        json.put("isFirstTimeWall1", isFirstTime);
        return json;
    }

    public List<Item> getItems() {
        return items;
    }

//        MODIFIES: this
//    EFFECTS: removes an item from the list of items present in the wall
    public void removeItems(Item i) {
        if (items.contains(i)) {
            items.remove(i);
        }
    }

//        MODIFIES: this
//        EFFECTS: adds an item into the list of items present on this wall
    public void addItems(Item i) {
        if (!(items.contains(i))) {
            items.add(i);
        }
    }

    public int getNumberOfItems() {
        return items.size();
    }

    public boolean getIsLocked() {
        return isLocked;
    }

    public void setLocked(boolean locked) {
        isLocked = locked;
    }

    public String getName() {
        return name;
    }

    public void setFirstTime(boolean firstTime) {
        isFirstTime = firstTime;
    }

    public boolean getFirstTime() {
        return isFirstTime;
    }
}
