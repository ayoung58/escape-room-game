package model;

import org.json.JSONObject;
import model.Phone;
import persistence.Writable;

// The Item interface provides methods and elements that all items in the game should have
public interface Item {
    // interactable means that you can interact with it (i.e. solve, search, etc.)
    // collectable means it can be added to inventory
    // combinable means it can be combined with other items
    boolean isInteractable = false;
    boolean isCollectable = false;
    boolean isCombinable = false;

    boolean getInteractableStatus();

    boolean getCollectableStatus();

    boolean getCombinableStatus();

    JSONObject toJson();

    String getName();
}
