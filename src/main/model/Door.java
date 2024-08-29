package model;

import org.json.JSONObject;
import persistence.Writable;

// The Door class represents a door in the game, and can be unlocked to win the game
public class Door extends LockableItems {
    private String name = "Door";
    private String password;
    private boolean isInteractable;
    private boolean isCollectable;
    private boolean isCombinable;
    private boolean isLocked;

//    EFFECTS: creates a door object with appropriate fields
    public Door(boolean isLocked) {
        // can enter the password to the door
        isInteractable = true;
        isCollectable = false;
        // cannot combine any items with this one
        isCombinable = false;
        this.isLocked = isLocked;
        password = "joan";
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
        json.put("isLockedDoor", isLocked);
        return json;
    }

    public String getPassword() {
        return password;
    }

//    MODIFIES: this
//    EFFECTS: will try to unlock the door with the given password
    public boolean tryUnlocking(String password) {
        if (this.password == password) {
            isLocked = false;
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean getIsLocked() {
        return isLocked;
    }
}
