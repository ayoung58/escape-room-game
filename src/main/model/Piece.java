package model;

import org.json.JSONObject;
import persistence.Writable;

// The Piece abstract class provides methods and elements that all painting pieces in the game should have
public abstract class Piece implements Item {
    boolean isInteractable = false;
    boolean isCollectable = true;
    boolean isCombinable = true;

    @Override
    public boolean getInteractableStatus() {
        EventLog.getInstance().logEvent(new Event("Interactable filter filtered out a piece."));
        return isInteractable;
    }

    @Override
    public boolean getCollectableStatus() {
        return isCollectable;
    }

    @Override
    public boolean getCombinableStatus() {
        EventLog.getInstance().logEvent(new Event("Combinable filter filtered to show a piece."));
        return isCombinable;
    }
}
