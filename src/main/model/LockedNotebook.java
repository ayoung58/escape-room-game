package model;

import org.json.JSONObject;
import persistence.Writable;

// The LockedNotebook class represents a notebook in the locked state in the game
public class LockedNotebook extends LockableItems {
    private String name = "Notebook (locked)";
    private boolean isInteractable;
    private boolean isCollectable;
    private boolean isCombinable;
    private boolean isLocked;

//    EFFECTS: constructs a lockedNotebook object and assigns appropriate fields to instantiate
    public LockedNotebook(boolean isLocked) {
        // can unlock it
        isInteractable = true;
        isCollectable = true;
        // needs a key to unlock
        isCombinable = true;
        this.isLocked = isLocked;
    }

    @Override
    public boolean getInteractableStatus() {
        EventLog.getInstance().logEvent(new Event("Interactable filter filtered to show locked notebook."));
        return isInteractable; //stub
    }

    @Override
    public boolean getCollectableStatus() {
        return isCollectable; //stub
    }

    @Override
    public boolean getCombinableStatus() {
        EventLog.getInstance().logEvent(new Event("Combinable filter filtered to show locked notebook."));
        return isCombinable; // stub
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public boolean getIsLocked() {
        return isLocked;
    }

    // Code is referenced from and based off of https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
    // This code was provided by course staff

    // EFFECTS: takes the object and puts specific fields to save into JSON format and returns it
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("isLockedNotebook", isLocked);
        return json;
    }

    public void setIsLocked(boolean locked) {
        isLocked = locked;
    }
}
