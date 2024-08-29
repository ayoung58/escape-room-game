package model;

import org.json.JSONObject;
import persistence.Writable;

// The Piece2 class represents one of the painting pieces in the game
public class Piece2 extends Piece {
    private String name = "Piece 2";

    public Piece2() {

    }

    // Code is referenced from and based off of https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
    // This code was provided by course staff

    // EFFECTS: takes the object and puts specific fields to save into JSON format and returns it
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", name);
        return json;
    }

    @Override
    public String getName() {
        return name;
    }
}
