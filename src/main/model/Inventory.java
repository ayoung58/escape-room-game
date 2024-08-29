package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;
import ui.GuiEscapeGame;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
// TODO: ADD A CITATION

// The Inventory class represents the inventory in the game, which can store collectable items
public class Inventory implements Writable {
    private String name;
    private List<Item> inventory;
    private List<Item> toRemove;

    // EFFECTS: constructs an empty list to house items
    public Inventory(String name) {
        this.name = name;
        inventory = new ArrayList<Item>();
        toRemove = new ArrayList<>();
    }

    // MODIFIES: this
    // EFFECTS: removes an item from inventory if the item is in the inventory
    public void removeItem(Item item) {
        for (Iterator<Item> iterator = inventory.iterator(); iterator.hasNext();) {
            Item i = iterator.next();
            if (i.getName().equals(item.getName())) {
                toRemove.add(i);
                EventLog.getInstance().logEvent(new Event("Removed " + item.getName() + " from inventory."));
            }
        }
        inventory.removeAll(toRemove);
    }

//    MODIFIES: this
//    EFFECTS: adds an item to the inventory
    public void addItem(Item item) {
        if (!(inventory.contains(item)) && item.getCollectableStatus()) {
            inventory.add(item);
            EventLog.getInstance().logEvent(new Event("Added " + item.getName() + " to inventory."));
        }
    }

//    EFFECTS: checks to see if the element is in the inventory
    public boolean containsItem(Item i) {
        for (Item item : inventory) {
            if (item.getName() == i.getName()) {
                return true;
            }
        }
        return false;
    }

    public List<Item> getInventory() {
        return inventory;
    }

    public int getInventorySize() {
        return inventory.size();
    }

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
        json.put("inventoryStuff", inventoryToJson());
        return json;
    }

    // Code is referenced from and based off of https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
    // This code was provided by course staff

    // EFFECTS: returns inventory items as a JSON array
    private JSONArray inventoryToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Item t : inventory) {
            jsonArray.put(t.toJson());
        }

        return jsonArray;
    }

    public EventLog getEventLogInstance() {
        return EventLog.getInstance();
    }
}
