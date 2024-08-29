package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ExplorableObjects implements Writable {
    private String name;
    private List<Item> explorable;
    private List<Item> toRemove;

    // EFFECTS: constructs an empty list to house items
    public ExplorableObjects(String name) {
        this.name = name;
        explorable = new ArrayList<Item>();
        toRemove = new ArrayList<>();
    }

    // MODIFIES: this
    // EFFECTS: removes an item from the explorable list if the item is part of explorable list
    public void removeItem(Item item) {
        for (Iterator<Item> iterator = explorable.iterator(); iterator.hasNext();) {
            Item i = iterator.next();
            if (i.getName().equals(item.getName())) {
                toRemove.add(i);
            }
        }
        explorable.removeAll(toRemove);
    }

    //    MODIFIES: this
//    EFFECTS: adds an item to the explorable list
    public void addItem(Item item) {
        if (!(explorable.contains(item))) {
            explorable.add(item);
        }
    }

    public List<Item> getExplorable() {
        return explorable;
    }

    public int getExplorableSize() {
        return explorable.size();
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
        json.put("explorableStuff", explorableToJson());
        return json;
    }

    //    EFFECTS: checks to see if the element is in the list of explorable items
    public boolean containsItem(Item i) {
        for (Item item : explorable) {
            if (item.getName() == i.getName()) {
                return true;
            }
        }
        return false;
    }

    // EFFECTS: get an item from list of explorable items
    public Item getItem(Item i) {
        for (Item item : explorable) {
            if (item.getName() == i.getName()) {
                return item;
            }
        }
        return null;
    }

    // Code is referenced from and based off of https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
    // This code was provided by course staff

    // EFFECTS: returns explorable items as a JSON array
    private JSONArray explorableToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Item t : explorable) {
            jsonArray.put(t.toJson());
        }

        return jsonArray;
    }
}
