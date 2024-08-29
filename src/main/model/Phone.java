package model;

import org.json.JSONObject;
import persistence.Writable;

// The Phone class represents a phone in the game, can be used to call a number and get information
public class Phone implements Item {
    private String name = "Phone";
    private int phoneNumber;
    boolean isInteractable;
    boolean isCollectable;
    boolean isCombinable;
    boolean called;

    //    EFFECTS: constructs a phone object and assigns appropriate fields to instantiate
    public Phone(boolean called) {
        // can enter phone number and also call
        isInteractable = true;
        isCollectable = false;
        // cannot combine any items with this one
        isCombinable = false;
        this.called = called;
        phoneNumber = 1115059047;
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
        json.put("calledPhone", called);
        return json;
    }

    //    REQUIRES: phoneNumber must be a 10-digit number
//    EFFECTS: checks whether the phone number dialed is correct
    public boolean isCorrectPhoneNumber(int phoneNumber) {
        if (this.phoneNumber == phoneNumber) {
            return true;
        }
        return false;
    }

    public int getPhoneNumber() {
        return phoneNumber;
    }

    public boolean getCalled() {
        return called;
    }

    public void setCalled(boolean called) {
        this.called = called;
    }
}
