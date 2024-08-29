package model;

import org.json.JSONObject;
import persistence.Writable;

// The CombinationLock class represents a combination lock in the game, and can be locked
// and unlocked.
public class CombinationLock extends LockableItems {
    private String name = "Combination lock";
    private int passcodeFirstCode = 10;
    private int passcodeSecondCode = 9;
    private int passcodeThirdCode = 13;
    private boolean isInteractable;
    private boolean isCollectable;
    private boolean isCombinable;
    private boolean isLocked;

    // Code is referenced from and based off of https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
    // This code was provided by course staff

    // EFFECTS: takes the object and puts specific fields to save into JSON format and returns it
    public CombinationLock(boolean isLocked) {
        // can try to solve it
        isInteractable = true;
        // cannot add to inventory (when solved, will just disappear)
        isCollectable = false;
        // can't combine with any other items
        isCombinable = false;
        this.isLocked = isLocked;
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

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("isLockedCombinationLock", isLocked);
        return json;
    }

    public int getPasscodeFirstCode() {
        return passcodeFirstCode;
    }

    public int getPasscodeSecondCode() {
        return passcodeSecondCode;
    }

    public int getPasscodeThirdCode() {
        return passcodeThirdCode;
    }

    //  REQUIRES: first, second, and third: int from 1 to 55;
    //  MODIFIES: this
    //  EFFECTS: will try to unlock the combination lock given the code and number of turns between each code
    public boolean tryUnlocking(int first, int second, int third) {
        if (first ==  passcodeFirstCode && second == passcodeSecondCode && third == passcodeThirdCode) {
            isLocked = false;
            return true;
        } else {
            return false;
        }
    }

//    EFFECTS: checks to see if the lock is locked
    @Override
    public boolean getIsLocked() {
        return isLocked; //stub
    }
}
