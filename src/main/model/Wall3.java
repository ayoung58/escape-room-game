package model;

import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;
import java.util.List;

// The Wall3 class represents one of the walls in the game
public class Wall3 extends Walls {
    private List<Item> items = new ArrayList<Item>();
    private String name = "Wall 3";
    private boolean isFirstTime;
    private boolean isInteractable;
    private boolean isCollectable;
    private boolean isCombinable;

    //    EFFECTS: constructs a new wall
    public Wall3(boolean isFirstTime) {
        isInteractable = true;
        isCollectable = false;
        isCombinable = false;
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
        json.put("isFirstTimeWall3", isFirstTime);
        return json;
    }

    public List<Item> getItems() {
        return items;
    }

//    MODIFIES: this
//    EFFECTS: removes an item from the list of items present in the wall
    public void removeItems(Item i) {
        if (items.contains(i)) {
            items.remove(i);
        }
    }

    public int getNumberOfItems() {
        return items.size(); // stub
    }

    //    MODIFIES: this
    //    EFFECTS: adds an item into the list of items present on this wall
    public void addItems(Item i) {
        if (!(items.contains(i))) {
            items.add(i);
        }
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
