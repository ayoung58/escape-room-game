package model;

import org.json.JSONObject;
import persistence.Writable;

// The SmallSafe class represents small safe that can be collected and unlocked in the game
public class SmallSafe extends LockableItems {
    private String name = "Small safe";
    private int firstColor = 1;
    private int secondColor = 4;
    private int thirdColor = 4;
    private boolean isInteractable;
    private boolean isCollectable;
    private boolean isCombinable;
    private boolean isLocked;

    //    EFFECTS: constructs a smallSafe object and assigns appropriate fields to instantiate
    public SmallSafe(boolean isLocked) {
        // can solve the safe
        isInteractable = true;
        isCollectable = true;
        isCombinable = false;
        this.isLocked = isLocked;
    }

    @Override
    public boolean getInteractableStatus() {
        EventLog.getInstance().logEvent(new Event("Interactable filter filtered to show small safe."));
        return isInteractable; // stub
    }

    @Override
    public boolean getCollectableStatus() {
        return isCollectable; // stub
    }

    @Override
    public boolean getCombinableStatus() {
        EventLog.getInstance().logEvent(new Event("Combinable filter filtered out small safe."));
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
        json.put("isLockedSmallSafe", isLocked);
        return json;
    }

    //    REQUIRES: numbers need to be integers from 1 to 7
//    EFFECTS: will try to unlock the small safe
    public boolean tryUnlocking(int firstColor, int secondColor, int thirdColor) {
        if (this.firstColor == firstColor && this.secondColor == secondColor && this.thirdColor == thirdColor) {
            isLocked = false;
            return true;
        } else {
            return false;
        }
    }

    public int getFirstColor() {
        return firstColor;
    }

    public int getSecondColor() {
        return secondColor;
    }

    public int getThirdColor() {
        return thirdColor;
    }

    @Override
    public boolean getIsLocked() {
        return isLocked;
    }

    public void setIsLocked(boolean locked) {
        isLocked = locked;
    }
}
